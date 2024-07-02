import java.io.*;
import java.util.*;

public class Main {

    static int[] root;
    static int find(final int x)
    {
        if(root[x] < 0) return x;
        return root[x] = find(root[x]);
    }

    static boolean merge(final int x, final int y)
    {
        int r1 = find(x);
        int r2 = find(y);

        if(r1==r2) return false;

        if(root[r1] < root[r2])
        {
            root[r2] += root[r1];
            root[r1] = r2;
        }else{
            root[r1] += root[r2];
            root[r2] = r1;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        root = new int[N+1];
        Arrays.fill(root, -1);

        char info[] = new char[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i)
        {
            info[i] = st.nextToken().charAt(0);
        }
        int from, to, weight;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for(int i=0; i<M; ++i)
        {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            //도로의 조건 : 두 정점의 성별이 달라야 한다. 
            if(info[from-1] == info[to-1])
                continue;

            pq.add(new int[] {from, to, weight});
           // pq.add(new int[] {to, from, weight});
        }

        int cnt = 0;
        int[] top;
        while(!pq.isEmpty())
        {
            top = pq.poll();
            if(merge(top[0], top[1]))
            {
                //System.out.println(Arrays.toString(top));
                answer += top[2];
                if(++cnt == N-1)
                    break;
            }
        }

        System.out.print(cnt==N-1 ? answer : -1);
    } // main
}
