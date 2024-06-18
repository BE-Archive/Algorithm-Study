import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
    static final int MAX = 1000 + 1;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static int N, M, answer = 0;
    static int[][] map = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static Queue<Pair> q = new LinkedList<>();

    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M)
            return false;
        return true;
    }

    static boolean isDone() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (map[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    

    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; ++i) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    q.add(new Pair(i, j));
            }
        }
      
        while (!q.isEmpty()) {
        	
            int qSize = q.size();
            
            while (qSize-- > 0) {
                Pair now = q.poll();
                int nowX = now.first;
                int nowY = now.second;
                
                for (int d = 0; d < 4; ++d) {
                    int x = nowX + dx[d];
                    int y = nowY + dy[d];
                    if (!isValid(x, y) || map[x][y] != 0 || visited[x][y])
                        continue;
                    map[x][y] = 1;
                    visited[x][y] = true;
                    q.add(new Pair(x, y));
                }
            }
            ++answer;
        }
        
        answer = isDone() ? --answer : -1;
        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    } // main
}
