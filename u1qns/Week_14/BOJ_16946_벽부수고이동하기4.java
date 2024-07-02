import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final static int WALL = 1;
    final static int EMPTY = 0;

    final static int dx[] = {1, -1, 0, 0};
    final static int dy[] = {0, 0, 1, -1};

    static int N, M;
    static int[][] map, answer;
    static int group_idx = 1;

    static void show()
    {
        System.out.println("-------------------------");
        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int bfs(final int _x, final int _y)
    {
        int size = 1;
        map[_x][_y] = -group_idx;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{_x, _y});

        int[] front;
        int x, y;

        while(!q.isEmpty())
        {
            front = q.poll();
            for(int d=0; d<4; ++d)
            {
                x = front[0] + dx[d];
                y = front[1] + dy[d];

                if(x<0 || x>=N || y<0 || y>=M || map[x][y] != EMPTY) continue;

                map[x][y] = -group_idx;
                q.add(new int[]{x, y});
                ++size;
            }
        }

        return size;
    }

    public static void main(String[] args) throws IOException {

        String line;
        List<int[]> walls = new ArrayList<>();
        List<Integer> group = new ArrayList<>(); // EMPTY의 그룹 사이즈를 저장한다.

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = new int[N][M];

        for(int i=0; i<N; ++i)
        {
            line = br.readLine();
            for (int j = 0; j < M; ++j)
            {
                map[i][j] = (line.charAt(j) == '1' ? WALL : EMPTY);
                if(map[i][j] == WALL) walls.add(new int[]{i, j});
            }
        }

        for(int i=0; i<N; ++i)
        {
            for(int j=0; j<M; ++j)
            {
                if(map[i][j] != EMPTY) continue;
                group.add(bfs(i, j));
                group_idx++;
                //show();
            }
        }

        for(final int[] e : walls)
        {
            int cnt = 1;
            boolean isSelected[] = new boolean[group_idx];
            for(int d=0; d<4; ++d)
            {
                int x = e[0] + dx[d];
                int y = e[1] + dy[d];

                if(x<0 || y<0 || x>=N || y>=M) continue;

                if(map[x][y] < 0 && !isSelected[-map[x][y]])
                {
                    isSelected[-map[x][y]] = true;
                    cnt += group.get(-map[x][y] - 1);
                }
            }
            answer[e[0]][e[1]] = (cnt%10);
        }

        for(int i=0; i<N; ++i)
        {
            for(int j=0; j<M; ++j)
                sb.append(answer[i][j]);
            sb.append("\n");
        }

        System.out.print(sb);

    } // main
}
