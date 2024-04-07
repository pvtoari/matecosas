import java.io.*;
import java.util.*;

public class distribucionPi {
    public static void main(String[] args) {
        String input = "";
        int[] nums = new int[10];

        try {
            Scanner kbd = new Scanner(new File("//workspaces//matecosas//decimalesPi//pi.txt"));
            input = kbd.nextLine();
            kbd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < input.length(); i++) { //algo anda mal aqui porque la suma del recuento != longitud del input-2
            switch(input.charAt(i)) {
                case '0': nums[0]++;
                case '1': nums[1]++;
                case '2': nums[2]++;
                case '3': nums[3]++;
                case '4': nums[4]++;
                case '5': nums[5]++;
                case '6': nums[6]++;
                case '7': nums[7]++;
                case '8': nums[8]++;
                case '9': nums[9]++;
            }
        }

        nums[3]--; //como estoy evaluando el "3.14...." descuento un tres de las apariciones del tres en los decimales

        String res = "";
        for(int i = 0; i < nums.length; i++) {
            res += "Cantidad de " + i + "s" + " -> " + nums[i] + "\n";
        }

        System.out.println(res);
    }
}
