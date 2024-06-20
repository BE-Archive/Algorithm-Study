import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); //학생 수
        int m = Integer.parseInt(st.nextToken()); //키 순서 정보 개수

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

        Queue<Integer> q= new LinkedList<>();
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
