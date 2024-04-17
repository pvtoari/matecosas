import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class sacarpuntos implements Serializable {

    public static void main(String[] args) {

        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Dominio [-x,x]: ");
        final double dom = kbd.nextInt();
        System.out.println("Número de subdivisiones ");
        final double divs = kbd.nextInt();
        double x = -dom, Re, Im;
        int i = 0;
        String salida = "";

        kbd.close();

        long ti, tf, tt;
        String anim= "|/-\\";

        try (FileWriter file = new FileWriter("//workspaces//matecosas//equisAlaX//puntos.dat")) {

            ti = System.currentTimeMillis();
            while (-dom <= x && x <= dom) {


                i++;
                x += dom/divs;
                Re = Math.cos(Math.PI * x);
                Im = Math.sin(Math.PI * x);
                //salida += x + " " + Re + " " + Im + "\n";
                double absX= Math.abs(x);
                if(x>0) {
                    salida += x + " " + Math.pow(absX, absX) + " " + "0.0" + "\n"; 
                } else if(x<0) {
                    salida += x + " " + Math.pow(absX, x)*Re + " " + Math.pow(absX, x)*Im + "\n";
                }

                String data = "\r" + anim.charAt(i % anim.length()) + " " + i + " (" + (System.currentTimeMillis()-ti)/1000d + " s)";
                System.out.write(data.getBytes());
            }
            tf = System.currentTimeMillis();

            tt=tf-ti;

            file.write(salida);
            System.out.println("\n" + i + " puntos generados");
            System.out.println("Puntos guardados y formateados en /puntos.dat");
            System.out.println("Tiempo de calculo iterativo (ms) : " + tt + "ms");
            System.out.println("Tiempo de calculo iterativo (s) : " + tt/1000d + "s");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("error de la hostia: " + e.getMessage());
        }
    }
}