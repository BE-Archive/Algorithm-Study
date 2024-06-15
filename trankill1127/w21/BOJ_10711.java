import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10711 {

    public static int[][] land;
    public static int[][] direction = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1}, {0,1},
            {1,-1}, {1,0}, {1,1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        land = new int[h][w];

        for (int i=0; i<h; i++){
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<w; j++){
                if (input[j]=='.') land[i][j]=0;
                else land[i][j]= input[j]-'0';
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i=1; i<h-1; i++){
            for (int j=1; j<w-1; j++){
                if (countEmpty(i,j)>=land[i][j]) {
                    q.add(new int[]{i,j});
                }

            }
        }

        boolean[][] visited = new boolean[h][w];
        int waveCnt=0;
        while (!q.isEmpty()){

            int size=q.size();
            while (size>0){

                int[] now = q.poll();
                land[now[0]][now[1]]=0;

                for (int d=0; d<8; d++){
                    int[] next = new int[]{
                            now[0]+direction[d][0],
                            now[1]+direction[d][1]
                    };
                    if (land[next[0]][next[1]]==0) continue;
                    if (visited[next[0]][next[1]]) continue;

                    if (countEmpty(next[0],next[1])>=land[next[0]][next[1]]) {
                        visited[next[0]][next[1]]=true;
                        q.add(next);
                    }
                }

                size--;
            }

            waveCnt++;
        }

        System.out.println(waveCnt);
    }

    public static int countEmpty(int x, int y){
        int emptyCnt=0;
        for (int d=0; d<8; d++){
            int[] next = new int[]{
                    x+direction[d][0],
                    y+direction[d][1]
            };
            if (land[next[0]][next[1]]==0) emptyCnt++;
        }
        return emptyCnt;
    }

}

