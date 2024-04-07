import java.io.*;
import java.util.*;

public class distribucionPi {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("//workspaces//matecosas//menosUnoX//pi.txt");
            int read = fr.read();
            System.out.println(read);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static class FastReader { 
            BufferedReader br; 
            StringTokenizer st; 
      
            public FastReader() 
            { 
                br = new BufferedReader( 
                    new InputStreamReader(System.in)); 
            } 
      
            String next() 
            { 
                while (st == null || !st.hasMoreElements()) { 
                    try { 
                        st = new StringTokenizer(br.readLine()); 
                    } 
                    catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                } 
                return st.nextToken(); 
            } 
      
            int nextInt() { return Integer.parseInt(next()); } 
      
            long nextLong() { return Long.parseLong(next()); } 
      
            double nextDouble() 
            { 
                return Double.parseDouble(next()); 
            } 
      
            String nextLine() 
            { 
                String str = ""; 
                try { 
                    if(st.hasMoreTokens()){ 
                        str = st.nextToken("\n"); 
                    } 
                    else{ 
                        str = br.readLine(); 
                    } 
                } 
                catch (IOException e) { 
                    e.printStackTrace(); 
                } 
                return str; 
            } 
        }
}
