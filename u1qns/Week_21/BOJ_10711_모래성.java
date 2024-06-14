import java.io.*;
import java.util.*;

public class BOJ_10711_모래성 {

    public static void main(String[] args) throws IOException {

        final int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
        final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] grid = new int[H][W];
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0; i<H; ++i)
        {
            String line = br.readLine();
            for(int j=0; j<W; ++j)
            {
                char ch = line.charAt(j);
                if (ch == '.') q.offer(new int[]{i, j});
                else grid[i][j] = Character.getNumericValue(ch);
            }
        }

        int answer = 0;
        int[] front;
        while(!q.isEmpty())
        {
            int qSize = q.size();
            while(qSize-- > 0)
            {
                front = q.poll();

                for(int d=0; d<8; ++d)
                {
                    int x = front[0] + dx[d];
                    int y = front[1] + dy[d];

                    if((x<0 || y<0 || x>=H || y>=W)|| grid[x][y] == 0) continue;

                    if(--grid[x][y] == 0)
                        q.offer(new int[]{x, y});
                }
            }
            ++answer;
        }

        System.out.print(answer-1);
    }
}
