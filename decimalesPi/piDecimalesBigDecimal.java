/*
    no se como narices funciona la clase mathcontext pero desde luego que
    no lo hace como yo esperaba, ademas algun termino se me escapa porque
    aplicando la machin de gauss en mathematica tengo decimales distintos para
    distintas iteraciones, ya vere

    reminder: para la proxima vez que quiera 100k decimales primero calcula su error,
    porque hice 100k iteraciones que tomaron 7.5h cuando solo necesitaba 34k ._.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class piDecimalesBigDecimal implements Serializable {
    public static void main(String[] args) throws Exception{
        long ti, tf, tt;
        Scanner kbd = new Scanner(System.in);
        String anim= "|/-\\";
        
        System.out.print("Ene porfi: ");
        int n = kbd.nextInt();

        System.out.print("Número de decimales: ");
        int k = kbd.nextInt();

        kbd.close();

        BigDecimal sum = new BigDecimal("0");
        MathContext mc = new MathContext(k, RoundingMode.HALF_UP);

        

        ti = System.currentTimeMillis();
        for(int i = 0; i <= n; i++) {
            
            String data = "\r" + anim.charAt(i % anim.length()) + " " + i + " (" + (System.currentTimeMillis()-ti)/1000d + " s)";
            System.out.write(data.getBytes());
            //Thread.sleep(1);

            /*
            BigDecimal menosUnoN = new BigDecimal("-1").pow(i, mc);
            BigDecimal dosNmas1 = new BigDecimal(2*i+1, mc);
            BigDecimal docediocho = new BigDecimal("18").pow(2*i+1, mc);
            docediocho = new BigDecimal("12").divide(docediocho, mc);
            BigDecimal ochocincuset = new BigDecimal("57").pow(2*i+1, mc);
            docediocho = new BigDecimal("8").divide(ochocincuset, mc);
            BigDecimal cincodosc = new BigDecimal("57").pow(2*i+1, mc);
            docediocho = new BigDecimal("-5").divide(cincodosc, mc);
            
            sum = sum.add(menosUnoN.divide(dosNmas1,mc).multiply(docediocho.add(ochocincuset.add(cincodosc, mc), mc), mc), mc);
            */

            BigDecimal term = BigDecimal.valueOf(Math.pow(-1, i))
                                      .divide(BigDecimal.valueOf(2 * i + 1), mc)
                                      .multiply(BigDecimal.valueOf(12)
                                                            .divide(BigDecimal.valueOf(18).pow(2 * i + 1, mc), mc)
                                                            .add(BigDecimal.valueOf(8)
                                                                             .divide(BigDecimal.valueOf(57).pow(2 * i + 1, mc), mc))
                                                            .subtract(BigDecimal.valueOf(5)
                                                                             .divide(BigDecimal.valueOf(239).pow(2 * i + 1, mc), mc)));
            sum = sum.add(term);

            }
            sum = sum.multiply(new BigDecimal("4"));
            tf = System.currentTimeMillis();
            tt = tf-ti;

            //System.out.println("\n \n" + sum);
            //System.out.println("Error respecto Math.PI: " + Math.abs((Math.PI - sum)));
            System.out.println("\n"+ "Numero de iteraciones: " + (n+1));
            System.out.println("Tiempo de calculo iterativo (ms) : " + tt + "ms");
            System.out.println("Tiempo de calculo iterativo (s) : " + tt/1000d + "s");

            try {
                FileWriter fw = new FileWriter("//workspaces//matecosas//decimalesPi//pi.txt");
                fw.write(sum.toString());
                fw.close();
                System.out.println("Resultado guardado");
                System.out.println("Caracteres: " + sum.toString().length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    //antiguo codigo lo conservo por amor al pasado
    public static void main2(String[] args) {

        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        
        //Double a = Math.pow(10, -Math.pow(10,6)-1);
        BigDecimal a = BigDecimal.TEN;
        for(int i = 0; i <=Math.pow(10,6)+1; i++) {
            a = a.divide(BigDecimal.TEN, mc);
        }
        BigDecimal b = new BigDecimal(0, mc);
        int n = 39830;
        while(a.compareTo(b)>0) {

            BigDecimal temp1 = new BigDecimal(2*n+1, mc);
            temp1 = BigDecimal.ONE.divide(temp1, mc); // parte izquierda 1/2n+1
            
            BigDecimal temp2 = new BigDecimal(18, mc); //calculo primer divisor potente18 del parentesis
            temp2 = temp2.pow(2*n+1, mc);
            temp2 = new BigDecimal(12).divide(temp2, mc); // 12 entre divisor potente18 parentesis

            BigDecimal temp3 = new BigDecimal(57, mc); //calculo segundo divisor potente57 del parentesis
            temp3 = temp3.pow(2*n+1, mc);
            temp3 = new BigDecimal(8).divide(temp3, mc); // 8 entre divisor potente57 parentesis

            BigDecimal temp4 = new BigDecimal(239, mc); //calculo tercer divisor potente239 del parentesis
            temp4 = temp4.pow(2*n+1, mc);
            temp4 = new BigDecimal(5).divide(temp4, mc); // 5 entre divisor239 potente parentesis

            temp2 = temp2.add(temp3.add(temp4, mc)); //calculo temp2 + temp3 + temp4 y lo guardo en temp2

            temp1=temp1.multiply(temp2, mc); // multiplico temp1 1/2n+1 por suma de divisores potentes

            b = temp1;

            System.out.println(b);
            //System.out.println(n);
            n++;
        }
        //System.out.println(a);
    }
}
