import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Pair
{
    int x;
    int y;
 
    Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
};
 

public class Main {
    
    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
 
    static int N, M, answer = 0;
    static int[][] map;
 
    static boolean isValid(int x, int y)
    {
    	if(x<0 || y<0 || x>=N || y>=M)
    		return true;
    	return false;
    }
    
    static void copyArray(int[][] inp, int[][] out)
    {
    	for(int i=0; i<N; ++i)
    	{
    		out[i] = new int[M+1];
    		for(int j=0; j<M; ++j)
    		{
    			out[i][j] = inp[i][j];
    		}
    	}
    }
    
    static int counting()
    {
        boolean[][] visited = new boolean[N+1][M+1];
 
        int cnt = 0;
        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < M; ++j)
            {
                if (!isValid(i, j) || map[i][j] == 0 || visited[i][j])
                	continue;
                
                visited[i][j] = true;
            	DFS(i, j, visited);
            	++cnt;
            }
        }
        
        return cnt;
    }
    
    
    static void melt()
    {
        Queue<Pair> q = new LinkedList<>();
        int[][] new_map = new int[N+1][];
        
        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < M; ++j)
            {
                if (map[i][j] != 0)
                {
                    q.offer(new Pair(i, j));
                }
            }
        }
 
        while (!q.isEmpty())
        {
            Pair ice = q.poll();
            int cnt = 0; // 주변 빈 공간 개수
            
            for (int d = 0; d < 4; d++)
            {
                int x = ice.x + dx[d];
                int y = ice.y + dy[d];
 
                if(!isValid(x, y) || map[x][y] !=0)
                	continue;
                
                ++cnt;
            }
 
            if (map[ice.x][ice.y] < cnt)
            {
                new_map[ice.x][ice.y] = 0;
            } 
            else
            {
                new_map[ice.x][ice.y] -= cnt;
            }
        }
        
        map = new_map;
    }
    
    static void DFS(int _x, int _y, boolean[][] visited)
    {
        visited[_x][_y] = true;
 
        for (int d = 0; d < 4; ++d)
        {
            int x = _x + dx[d];
            int y = _y + dy[d];
 
            if(!isValid(x, y) || map[x][y] == 0 || visited[x][y])
            	continue;
            DFS(x, y, visited);
        }
    }
    
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        map = new int[N+1][M+1];
        for (int i = 0; i < N; ++i)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int cnt = 0;
        while ((cnt = counting()) < 2)
        {
            if (cnt == 0)
            {
                answer = 0;
                break;
            }
 
            melt();
            ++answer;
        }
 
        System.out.print(answer);
    } // main
}
