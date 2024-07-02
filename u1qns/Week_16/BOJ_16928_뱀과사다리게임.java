import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static List<int[]> info = new ArrayList<>();

    static int move(final int move)
    {
        for(final int[] pos : info)
        {
            if(move == pos[0])
                return pos[1];
        }
        return move;
    }
    static int solve()
    {
        int result = 0;
        boolean visited[] = new boolean[101];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while(!q.isEmpty())
        {
            int qSize = q.size();
            while(qSize-- > 0)
            {
                int cur = q.poll();
                for (int i = 1; i < 7; ++i)
                {
                    int next = cur + i;

                    if(next == 100)
                        return result+1;

                    if (next > 100 || visited[next]) continue;

                    next = move(next);
                    visited[next] = true;
                    q.add(next);
                }

            }
            ++result;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ladder = Integer.parseInt(st.nextToken());
        int snake= Integer.parseInt(st.nextToken());

        for(int i=0; i<ladder+snake; ++i)
        {
            st = new StringTokenizer(br.readLine());
            info.add(new int[]{ Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()) });
        }

//
//
//        solve();

        System.out.print(solve());

    } // main
}
