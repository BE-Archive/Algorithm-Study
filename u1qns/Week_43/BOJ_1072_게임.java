import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
      
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        int Z = (int) ((Y * 100) / X);

        if(Z >= 99)
            System.out.print(-1);
        else {
            long left = 0;
            long right = 1_000_000_000;

            while (left <= right) {
                long mid = (left + right) / 2;
                int now = (int) (((Y + mid) * 100) / (X + mid));
                if (now <= Z) {
                    left = mid + 1;
                } else if (now > Z) {
                    right = mid - 1;
                }
            }
            System.out.println(left);
        }
    } // main
}
