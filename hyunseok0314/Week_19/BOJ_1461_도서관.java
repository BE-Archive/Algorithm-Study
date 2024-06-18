import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1461_도서관 {

    static int N, M;
    static ArrayList<Integer> p, n;
    static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new ArrayList<>();
        n = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (book > 0) {
                p.add(book);
            } else {
                n.add(-book);
            }
        }

        Collections.sort(p, Collections.reverseOrder());
        Collections.sort(n, Collections.reverseOrder());

        total = 0;

        for (int i = 0; i < p.size(); i++) {
            if (i % M == 0) {
                total += p.get(i) << 1;
            }
        }

        for (int i = 0; i < n.size(); i++) {
            if (i % M == 0) {
                total += n.get(i) << 1;
            }
        }

        if (p.isEmpty()) {
            total -= n.get(0);
        } else if (n.isEmpty()) {
            total -= p.get(0);
        } else {
            total -= Math.max(p.get(0), n.get(0));
        }

        System.out.println(total);
    }
}
