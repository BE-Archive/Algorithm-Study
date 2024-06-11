import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1756_피자_굽기 {

    static int D, N;
    static int[] oven, dough;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        oven = new int[D];
        dough = new int[N];

        int min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            min = Math.min(min, Integer.parseInt(st.nextToken()));
            oven[i] = min;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dough[i] = Integer.parseInt(st.nextToken());
        }

        end: for (int d : dough) {
            while (oven[D--] < d) {
                if (D < 0) {
                    break end;
                }
            }
        }

        System.out.println(D + 1);
    }
}
