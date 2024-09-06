import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1005 {
    static ArrayList<Integer>[] adjList;
    static int[] arr;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            adjList = new ArrayList[N + 1];
            times = new int[N + 1];
            Arrays.fill(times, -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= N; j++) {
                adjList[j] = new ArrayList<Integer>();
                if(j == 0) continue;
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adjList[Y].add(X); //Y를 만들기 위해서 X가 필요하다는 의미
            }
            int dest = Integer.parseInt(br.readLine());
            sb.append(solve(dest)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve(int dest) {
        if (adjList[dest].isEmpty()) {
            times[dest] = arr[dest];
            return times[dest];
        }
        if(times[dest] != -1) return times[dest];

        int max = -1;
        for (int i = 0; i < adjList[dest].size(); i++) {
            max = Math.max(max, solve(adjList[dest].get(i)));
        }
        return times[dest] = max + arr[dest];
    }
}