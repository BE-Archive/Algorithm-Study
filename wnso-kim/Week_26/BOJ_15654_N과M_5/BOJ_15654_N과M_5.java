import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654_N과M_5 {

    static int N,M;
    static int[] arr, ans;
    static boolean[] visited;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        arr = new int[N];
        ans = new int[N];
        visited = new boolean[N];

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(stk.nextToken());

        Arrays.sort(arr);

        dfs(0);

        System.out.println(result);
    }

    static void dfs(int depth){
        if (depth == M){
            for (int m = 0; m < M; m++){
                result.append(ans[m] + " ");
            }

            result.append("\n");
            return;
        }

        for (int i = 0; i < N; i++){
            if (!visited[i]){
                visited[i] = true;
                ans[depth] = arr[i];

                dfs(depth + 1);
                
                visited[i] = false;
            }
        }
    }
}