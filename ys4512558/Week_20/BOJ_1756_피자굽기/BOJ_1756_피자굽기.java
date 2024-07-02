import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1756 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] oven = new int[D + 1];
        int[] maxWidth = new int[D + 1]; //해당 위치에 들어올 수 있는 가장 큰 너비 저장
        st = new StringTokenizer(br.readLine());
        int max = Integer.MAX_VALUE;
        for (int i = 1; i <= D; i++) {
            oven[i] = Integer.parseInt(st.nextToken());
            max = Math.min(max, oven[i]);
            maxWidth[i] = max;
        }

        int[] pizza = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int end = D;
        for (int i = 0; i < N; i++) {
            for (int j = end; j > 1; j--) {
                if(maxWidth[j] >= pizza[i]){
                    end = j - 1;
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt == N ? end + 1 : 0);
    }
}