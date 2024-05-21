import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2638_치즈{
    static int N, M;
    static int map[][];
    static int direct[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int count, time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if(map[i][j] == 1) count++; // 치즈 총 개수 세기
                
            }   
        }
        
        
        solve();

        System.out.println(time);
    
    }

    public static void solve() {
        while(count > 0){
            time++;
            Queue<int[]> queue = new ArrayDeque<>();
            Queue<int[]> cheese = new ArrayDeque<>();
            boolean visited[][] = new boolean[N][M];

            visited[0][0] = true;
            queue.offer(new int[]{0, 0});

            // 외부 공기 탐색
            while(!queue.isEmpty()){
                int[] now = queue.poll();

                for(int[] d : direct){
                    int nx = now[0] + d[0];
                    int ny = now[1] + d[1];

                    if(!isMap(nx, ny)) continue;
                    if(visited[nx][ny]) continue;

                    // 공기
                    if(map[nx][ny] == 0){
                        queue.offer(new int[]{nx, ny});
                    } else if(map[nx][ny] == 1){
                        cheese.offer(new int[]{nx, ny});
                    }

                    visited[nx][ny] = true;

                }
            }

            melt(cheese);
            
        }

    }

    public static void melt(Queue<int[]> cheese) {
        while (!cheese.isEmpty()) {
            int[] c = cheese.poll();
            int air = 0;

            for (int[] d : direct) {
                int nx = c[0] + d[0];
                int ny = c[1] + d[1];

                if (isMap(nx, ny) && map[nx][ny] == 0) air++;
            }

            if (air >= 2) {
                map[c[0]][c[1]] = 0;
                count--;
            } 
        }
    }


    public static boolean isMap(int x, int y) {
        if(x<0 || y<0 || x>= N || y>= M) return false;
        else return true;
    }

    
}
