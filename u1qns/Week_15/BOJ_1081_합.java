import java.io.*;
import java.util.*;

public class Main {

    // 서영이 코드 참고 
    static long solve(long num)
    {
        long answer = 0;
        int length = Long.toString(num).length() - 1;
        long tmp = (long) Math.pow(10, length);

        long ltmp = 0;

        while (length >= 0)
        {
            ltmp = num / tmp;
            
            answer += ltmp * (ltmp - 1) / 2 * tmp;
            answer += (num % tmp + 1) * ltmp;
            answer += ltmp * 45 * tmp / 10 * length;

            num = num % tmp;
            tmp = tmp / 10;
            length--;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long L = Long.parseLong(st.nextToken());
        long U = Long.parseLong(st.nextToken());

        System.out.print(solve(U) - solve(L - 1));
    }
}
