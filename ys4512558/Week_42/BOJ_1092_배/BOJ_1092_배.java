import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list, Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        List<Integer> weights = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < M; i++) {
            int weight = Integer.parseInt(st.nextToken());
            max = Math.max(max, weight);
            weights.add(weight);
        }

        Collections.sort(weights, Collections.reverseOrder());

        System.out.println(solve(list, weights));
    }

    private static int solve(List<Integer> list, List<Integer> weights) {
        if (weights.get(0) > list.get(0)) return -1;

        int cnt = 0;
        while (!weights.isEmpty()) {
            cnt++;
            int idx = 0;
            for (int i = 0; i < N; ) {
                if(idx >= weights.size()) break;
                if (list.get(i) >= weights.get(idx)) {
                    weights.remove(idx);
                    i++;
                } else {
                    idx++;
                }
            }
        }
        return cnt;
    }
}