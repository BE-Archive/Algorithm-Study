import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10711 {

    public static int[][] land;
    public static int[][] emptyCnt;
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
        emptyCnt = new int[h][w];

        Queue<int[]> q = new LinkedList<>();
        for (int i=0; i<h; i++){
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<w; j++){
                if (input[j]=='.') {
                    land[i][j]=0;
                    q.add(new int[]{i,j});
                }
                else land[i][j]= input[j]-'0';
            }
        }

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
                    if (next[0]<0||next[0]>=h||next[1]<0||next[1]>=w) continue;
                    if (land[next[0]][next[1]]==0) continue;
                    if (emptyCnt[next[0]][next[1]]==land[next[0]][next[1]]) continue;

                    emptyCnt[next[0]][next[1]]++;
                    if (emptyCnt[next[0]][next[1]]==land[next[0]][next[1]]) {
                        q.add(next);
                    }
                }

                size--;
            }

            waveCnt++;
        }

        System.out.println(waveCnt-1);
    }
}

