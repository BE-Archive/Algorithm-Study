import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> weights = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < M; i++) {
            int weight = Integer.parseInt(st.nextToken());
            max = Math.max(max, weight);
            weights.add(weight);
        }

        Collections.sort(weights);

        int res = 0;
        if (max > arr[N - 1]) {
            res = -1;
        } else {
            res = simulation(weights);
        }

        System.out.println(res);
    }

    private static int simulation(List<Integer> weights) {
        int cnt = 0;
        while (!weights.isEmpty()) {
            cnt++;
            for (int i = N - 1; i >= 0; i--) {
                int right = weights.size() - 1;
                int idx = upperBound(weights, 0, right, arr[i]);
                if(idx == -1) break;
                weights.remove(Integer.valueOf(weights.get(idx)));
                if(weights.isEmpty()) return cnt;
            }
        }

        return cnt;
    }

    private static int upperBound(List<Integer> list, int l, int r, int target) {
        while (l < r) {
            int mid = (l + r) / 2;

            if (list.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return list.get(l) <= target ? l : l - 1;
    }
}