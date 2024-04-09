package distSumaDigX;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class sacarpuntos implements Serializable {

    public static void main(String[] args) {

        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Dominio [0,n]: ");
        final double dom = kbd.nextInt();

        String salida = "";
        String anim= "|/-\\";

        int i = 0;
        long ti, tf;

        kbd.close();

        try (FileWriter file = new FileWriter("//workspaces//matecosas//distSumaDigX//puntos.dat")) {

            ti = System.currentTimeMillis();
            while(i<=dom) {
                String data = "\r" + anim.charAt(i % anim.length()) + " " + i + " (" + (System.currentTimeMillis()-ti)/1000d + " s)";
                System.out.write(data.getBytes());
                //Thread.sleep(1);

                salida += i + " " + sumaD(i) + "\n";
                i++;
            }
            tf = System.currentTimeMillis();

            file.write(salida);

            System.out.println("\nTiempo de calculo y escritura: " + (tf-ti)/1000d + " ms.");
            System.out.println(i + " puntos generados.");
            System.out.println("Puntos escritos correctamente.");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Por las barbas de Merlin, un error ha sucedido: " + e.getMessage());
        }
    }

    static int sumaD(int n){
        if (n <= 9) {
            return n;
        }
        else
        {
            return ((n % 10) + sumaD(n/10));
        }
    }
}