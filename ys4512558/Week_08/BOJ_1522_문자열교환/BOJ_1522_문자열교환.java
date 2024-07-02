import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int size = input.length();
        int countA = 0;
        for (int i = 0; i < size; i++) {
            if(input.charAt(i) == 'a') countA++;
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int countB = 0;
            for (int j = 0; j < countA; j++) {
                if(input.charAt((i + j) % size) == 'b') countB++;
            }
            min = Math.min(min, countB);
        }
        sb.append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}