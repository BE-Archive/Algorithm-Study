import java.io.*;
import java.util.*;

public class Main { // BFS 방식 

    final static int WALL = 1;
    final static int EMPTY = 0;
    final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int N, M;
    static int[][] arr;

    static class Coin
    {
        public Coin(int x, int y, int type)
        {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        int x, y, type;
    }
    
    static class cPair
    {
        cPair(Coin c1, Coin c2)
        {
            this.c1 = c1;
            this.c2 = c2;
        }

        Coin c1;
        Coin c2;
    }
    static boolean isValid(final int x, final int y)
    {
        if(x<0 || y<0 || x>=N || y>=M)
            return false;
        return true;
    }

    static int getAnswer(final List<int[]> coin)
    {
        int time = 0;

        Queue<cPair> q = new ArrayDeque<>();

        cPair front;
        Coin c1, c2;

        int x, x2, y, y2, type, type2;

        q.add(new cPair(
                new Coin (coin.get(0)[0], coin.get(0)[1], 0),
                new Coin (coin.get(1)[0], coin.get(1)[1], 1)
        ));

        while(!q.isEmpty())
        {
            if(time++ == 10)
                return -1;

            int qSize = q.size();
            while(qSize-- > 0)
            {
                front = q.poll();
                c1 = front.c1; type = c1.type;
                c2 = front.c2; type2 = c2.type;

                for(int[] d : direction)
                {
                    x = c1.x + d[0]; y = c1.y + d[1];
                    x2 = c2.x + d[0]; y2 = c2.y + d[1];

                    boolean r1 = isValid(x, y);
                    boolean r2 = isValid(x2, y2);

                    if(r1 != r2) return time;
                    if(!r1 && !r2) continue;

                    if(r1 && r2)
                    {
                        if(arr[x][y] == WALL)
                        {
                            x = c1.x; y = c1.y;
                        }
                        if(arr[x2][y2] == WALL)
                        {
                            x2 = c2.x; y2 = c2.y;
                        }
                        q.offer(new cPair(new Coin(x, y, type), new Coin(x2, y2, type2)));
                    }
                } // d
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input;
        List<int[]> coin = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++)
        {
            input = br.readLine();
            for(int j=0; j<M; j++)
            {
                switch(input.charAt(j))
                {
                    case 'o':
                        arr[i][j] = EMPTY;
                        coin.add(new int[]{i, j});
                        break;
                    case '.':
                        arr[i][j] = EMPTY;
                        break;
                    case '#':
                        arr[i][j] = WALL;
                        break;
                }
            }
        }

        System.out.print(getAnswer(coin));
    } // main
}
