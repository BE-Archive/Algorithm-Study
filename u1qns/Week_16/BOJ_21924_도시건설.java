import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;
    static boolean merge(final int a, final int b)
    {
        int p1 = find(a); int p2 = find(b);
        if(p1 == p2) return false;
        if(parent[p1] > parent[p2])
        {
            parent[p2] += parent[p1];
            parent[p1] = p2;
        }
        else
        {
            parent[p1] += parent[p2];
            parent[p2] = p1;
        }
        return true;
    }

    static int find(final int x)
    {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a, b, c;
        long total = 0, answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        Arrays.fill(parent, -1);

        while(M-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            total += (long)c;

            pq.offer(new int[]{a,b,c});
        }

        int cnt = 0;
        while(!pq.isEmpty())
        {
            int[] top = pq.poll();

            if(merge(top[0], top[1]))
            {
                answer += (long)top[2];
                if(++cnt == N- 1) break;
            }
        }

        System.out.print((cnt == N-1 ) ? (long)(total - answer) : -1);
    }
}
