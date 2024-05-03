import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1516 {

    public static int n;
    public static int[] time;
    public static int[] in;
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[] minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i=0; i<n+1; i++) graph.add(new ArrayList<>());
        time = new int[n+1];
        in = new int[n+1];
        minTime = new int[n+1];

        for (int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            time[i]=Integer.parseInt(st.nextToken());
            in[i]=st.countTokens()-1;
            while (true){
                int input = Integer.parseInt(st.nextToken());
                if (input==-1) break;
                graph.get(input).add(i);
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<n+1; i++) sb.append(minTime[i]).append("\n");
        System.out.print(sb.toString());
    }

    public static void bfs(){
        Queue<Integer> q =new LinkedList<>();
        for (int i=1; i<=n; i++){
            if (in[i]==0) {
                q.add(i);
                minTime[i]=time[i];
            }
        }

        while (!q.isEmpty()){
            int now = q.poll();
            for (int next: graph.get(now)) {
                minTime[next]=Math.max(minTime[next], minTime[now]+time[next]);
                in[next]--;
                if (in[next]==0){
                    q.add(next);
                }
            }
        }
    }
}
