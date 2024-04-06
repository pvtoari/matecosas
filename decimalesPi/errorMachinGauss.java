/*
    esto esta work in progress
    tengo que usar la clase bigdecimal tambien para sacar el error
    no funciona bien por ahora
    quiero llorar

*/

//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.util.Scanner;

public class errorMachinGauss {
    public static void main(String[] args) {

        Scanner kbd = new Scanner(System.in);

        System.out.print("Número de decimales correctos (p): ");
        int p = kbd.nextInt(), n = 0;

        kbd.close();

        while(true) {
            double aNmas1= 1/(2*n+3)*(12/Math.pow(18, 2*n+3)+8/Math.pow(57, 2*n+3)+5/Math.pow(239, 2*n+3));
            if(aNmas1 < Math.pow(10, -p-1)) {
                break;
            } else {
                n++;
                System.out.println("Iteración numero " + n);
            }
        }
        
        /*
        while (true) {
        BigDecimal uno = new BigDecimal(1);
        BigDecimal dosNmasTres = new BigDecimal(2 * n + 3);
        BigDecimal doce = new BigDecimal(12);
        BigDecimal ocho = new BigDecimal(8);
        BigDecimal cinco = new BigDecimal(5);
        BigDecimal dieciocho = new BigDecimal(18);
        BigDecimal cincuentaysiete = new BigDecimal(57);
        BigDecimal doscientosTreintaynueve = new BigDecimal(239);

        BigDecimal aNmas1 = uno.divide(dosNmasTres, 10, RoundingMode.HALF_UP)
                .multiply(doce.divide(dieciocho.pow(dosNmasTres.intValue()), 10, RoundingMode.HALF_UP)
                        .add(ocho.divide(cincuentaisiete.pow(dosNmasTres.intValue()), 10, RoundingMode.HALF_UP)
                                .add(cinco.divide(doscientosTreintainueve.pow(dosNmasTres.intValue()), 10, RoundingMode.HALF_UP))));

        if (aNmas1.compareTo(BigDecimal.ONE.divide((new BigDecimal(10).pow(p + 1)))) < 0) {
            break;
        } else {
            n++;
            System.out.println("Iteración número " + n);
            }
        }

        */
        System.out.println("Iteraciones necesarias para " + p + " decimales:");
        System.out.println(n);
    }
}
