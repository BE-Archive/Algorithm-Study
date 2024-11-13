import java.io.*;
import java.util.*;
public class Main {

    static int N, L, W, H;

    static boolean check(final double mid) {
        // 숫자 범위가 크기 때문에 세 변을 곱했을 때 long 
        long cnt = (long)(L/mid) * (long)(W/mid) * (long)(H/mid);
        return (cnt >= N);
    }

    static double solve() {
        double answer = 0;
        double right = Math.min(L, Math.min(W, H));
        double left = 0;

        while(left <= right) {
            double mid = (left + right) / 2.0;
            if(check(mid)) {
                if(left == mid)
                    return left;
              
                left = mid;
                answer = Math.max(answer, mid);
            } else {
                if(right== mid)
                    return mid;
              
                right = mid;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        System.out.print(solve());
    } // main
}
