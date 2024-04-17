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

        try (FileWriter file = new FileWriter("//workspaces//matecosas//equisAlaX//puntos.dat")) {

            while (-dom <= x && x <= dom) {
                i++;
                x += dom/divs;
                Re = Math.cos(Math.PI * x);
                Im = Math.sin(Math.PI * x);
                //salida += x + " " + Re + " " + Im + "\n";

                if(x>0) {
                    salida += x + " " + Math.abs(Math.pow(x,x)); 
                } else if(x<0){
                    salida += x + " ";
                }

            }

            file.write(salida);
            System.out.println(i + " puntos generados");
            System.out.println("Escrito socioooooooooo");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("error de la hostia: " + e.getMessage());
        }
    }
}