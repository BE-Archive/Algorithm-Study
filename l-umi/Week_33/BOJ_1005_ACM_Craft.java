import java.io.*;
import java.util.*;


public class Main {

    static Map<Integer, List<Integer>> graph;
    static int[] inDegree;
    static int[] times;
    static int[] result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        ////////////////////////////////////////////
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 개수
            int K = Integer.parseInt(st.nextToken()); // 건물순서 규칙의 개수
            ////////////////////////////////////////////
            graph = new HashMap<>();
            times = new int[N + 1]; // 각 건물당 건설에 걸리는 시간
            inDegree = new int[N + 1]; // 잔입차수
            result = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                graph.put(n, new ArrayList<>());
                times[n] = Integer.parseInt(st.nextToken());
            }
            ////////////////////////////////////////////
            for (int k = 1; k <= K; k++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph.get(X).add(Y);
                inDegree[Y]++;
            }
            int W = Integer.parseInt(br.readLine());

            topologicalSort(N);

            System.out.println(result[W]);
        }

    }
    
    // 위상정렬
    static void topologicalSort(int N) {
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수 0인 노트부터 시작하여 그래프를 순회
        for (int n = 1; n <= N; n++) {
            if (inDegree[n] == 0) {
                queue.offer(n);
                result[n] = times[n];
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                inDegree[next]--;
                result[next] = Math.max(result[next], result[now] + times[next]);
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

}
