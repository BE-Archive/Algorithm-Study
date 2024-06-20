import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); //문제 개수
        int m = Integer.parseInt(st.nextToken()); //먼저 푸는 것이 좋은 문제 정보 개수

        int[] in = new int[n+1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i=0; i<=n; i++){
            list.add(new ArrayList<>());
        }
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            list.get(prev).add(next);
            in[next]++;
        }

        Queue<Integer> q= new PriorityQueue<>();
        for (int i=1; i<=n; i++){
            if (in[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now+" ");
            for (int i=0; i<list.get(now).size(); i++){
                int next = list.get(now).get(i);
                in[next]--;
                if (in[next]==0) q.add(next);
            }
        }

        System.out.println(sb.toString());
    }
}