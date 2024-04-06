package esquizofrenia;

import java.util.Scanner;

public class piDecimalesDouble {
    public static void main(String[] args) {

        Scanner kbd = new Scanner(System.in);

        System.out.print("Ene porfi: ");
        int n = kbd.nextInt();
        double sum = 0;

        for(int i = 0; i <= n; i++) {
            sum += Math.pow(-1, i)/(2*i+1)*(
                12/Math.pow(18,2*i+1) + 8/Math.pow(57, 2*i+1) - 5/Math.pow(239, 2*i+1)
                );
        }

        sum *= 4;
        System.out.println(sum);
        System.out.println("Error respecto Math.PI: " + (Math.PI - sum));
        //System.out.println("Error respecto Math.PI: " + Math.abs((Math.PI - sum)));
        System.out.println("Numero de iteraciones: " + (n+1));
    }
}
