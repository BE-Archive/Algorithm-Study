import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] arr;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ans = new long[N + 1];

        arr = new ArrayList[N + 1];
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(arr[parent] == null) arr[parent] = new ArrayList<Integer>();
            arr[parent].add(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int current = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            ans[current] += value;
        }
        dfs(1, ans[1]);

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int current, long value) {
        ans[current] = value;
        if(arr[current] == null) return;
        for (int i = 0; i < arr[current].size(); i++) {
            int child = arr[current].get(i);
            dfs(child, value + ans[child]);
        }
    }
}