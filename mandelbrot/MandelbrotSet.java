package mandelbrot;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MandelbrotSet extends JComponent {

    public static final int WIDTH = 400, HEIGHT = 400;
    public static final double FAST_ZOOM = 0.899, REGULAR_ZOOM = 0.995, SLOW_ZOOM = 0.999;
    public static final int INITIAL_MAX_ITERATIONS = 1;
    public static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    private double centerX = -0.743643887037151;
    private double centerY = 0.131825904205330;

    private double zoomSpeed = REGULAR_ZOOM;

    private double width = 4, height = 4;

    private double dx = width / (WIDTH - 1), dy = height / (HEIGHT - 1);

    private final BufferedImage buffer;
    private int currentIteration = 0;
    private int maxIterations = INITIAL_MAX_ITERATIONS;

    private long lastTime = System.nanoTime();
    private double fps = 0.0;
    private double maxFps = Double.MIN_VALUE;
    private double minFps = Double.MAX_VALUE;

    public MandelbrotSet() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.getContentPane().add(this);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    System.exit(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    zoomSpeed = FAST_ZOOM;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    zoomSpeed = REGULAR_ZOOM;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    zoomSpeed = SLOW_ZOOM;
                }
            }
        });

        frame.setVisible(true);

        runMouseCenterThread();

        Timer timer = new Timer(16, e -> {
            currentIteration++;
            if (currentIteration <= maxIterations) {
                render();
                revalidate();
                repaint();
                zoom();
                updateFPS();
            } else ((Timer) e.getSource()).stop();
        });

        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
        g.setColor(Color.WHITE);
        g.drawString(String.format("FPS: %.2f", fps), 10, 20);
        g.drawString(String.format("Max FPS: %.2f", maxFps), 10, 40);
        g.drawString(String.format("Min FPS: %.2f", minFps), 10, 60);

        g.drawString(String.format("Current Iteration: %d", currentIteration), WIDTH-130, 20);
        g.drawString(String.format("Max Iterations: %d", maxIterations), WIDTH-130, 40);
        g.drawString(String.format("Zoom Speed: %.3f", zoomSpeed), WIDTH-130, 60);
        g.drawString(String.format("Center: (%.15f, %.15f)", centerX, centerY), WIDTH-305, 80);

        g.drawLine(WIDTH/2-5, HEIGHT/2, WIDTH/2+5, HEIGHT/2);
        g.drawLine(WIDTH/2, HEIGHT/2-5, WIDTH/2, HEIGHT/2+5);
    }

    public void runMouseCenterThread() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mousePos, this);
                centerX += (mousePos.x- (double) WIDTH /2)*dx*0.00001;
                centerY -= (mousePos.y- (double) HEIGHT /2)*dy*0.00001;
            }
        }).start();
    }

    public void render() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int chunkSize = HEIGHT/NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            final int startY = i * chunkSize;
            final int endY = (i == NUM_THREADS-1) ? HEIGHT : startY+chunkSize;

            executor.submit(() -> {
                double localCenterX = centerX;
                double localCenterY = centerY;
                double localDx = dx;
                double localDy = dy;
                int localMaxIterations = maxIterations;
                int localCurrentIteration = currentIteration;

                for (int x = 0; x < WIDTH; x++) {
                    for (int y = startY; y < endY; y++) {
                        double zx = 0, zy = 0, cX = localCenterX - (width/2) + x*localDx, cY = localCenterY + (height/2) - y*localDy;
                        int iter = 0;
                        while (zx*zx + zy*zy < 4 && iter<localCurrentIteration) {
                            double tmp = zx * zx - zy * zy + cX;
                            zy = 2.0 * zx * zy + cY;
                            zx = tmp;
                            iter++;
                        }

                        int color;
                        if (iter == localCurrentIteration) color = 0x00000000;
                        else color = Color.HSBtoRGB((float) iter/localMaxIterations, 1.0f, 1.0f);
                    
                        buffer.setRGB(x, y, color);
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void zoom() {
        width *= zoomSpeed;
        height *= zoomSpeed;

        maxIterations += 2;

        dx = width/(WIDTH-1);
        dy = height/(HEIGHT-1);
    }

    private void updateFPS() {
        long currentTime = System.nanoTime(), elapsedTime = currentTime-lastTime;
        lastTime = currentTime;
        fps = 1_000_000_000.0/elapsedTime;

        maxFps = Math.max(maxFps, fps);
        minFps = Math.min(minFps, fps);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        SwingUtilities.invokeLater(MandelbrotSet::new);

        if(args.length > 0 && args[0].equals("genReachabilityMetadata")) {
            System.out.println("Generating metadata for reachability testing...");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Metadata generated successfully.");
            System.exit(0);
        }
    }
}