import java.io.*;
import java.util.*;

public class BOJ_2637_장난감조립 {

    static List<Queue<int[]>> adj = new ArrayList<>();
    static int N;
    static boolean isC[];
    static int answer[][];

    static void getCount(final int idx)
    {
       Queue<int[]> q = adj.get(idx);
       int[] target;
       while(!q.isEmpty())
       {
           target = q.poll();
           int need = target[0];
           int cnt = target[1];

           if(isC[need])
           {
                getCount(need);
               for(int i=1; i<=N; ++i)
               {
                   answer[idx][i] += (answer[need][i] * cnt);
               }
           }
           else
           {
                answer[idx][need] += cnt;
           }


       }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        answer = new int[N+1][N+1];
        isC = new boolean[N+1];
        for(int i=0; i<=N; ++i)
        {
            adj.add(new ArrayDeque<int[]>());
        }

        int T = Integer.parseInt(br.readLine());
        int target, need, cnt = 0;
        while(T-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            target = Integer.parseInt(st.nextToken());
            need = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());

            isC[target] = true;
            adj.get(target).add(new int[]{need, cnt});
        }

        getCount(N);

        //getAnswer
        for(int i=1; i<=N; ++i)
        {
            if(isC[i]) continue;
            System.out.printf("%d %d\n", i, answer[N][i]);
        }
    }
}
