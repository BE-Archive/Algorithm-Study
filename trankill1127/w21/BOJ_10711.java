import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10711 {

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
        int[][] land = new int[h][w];

        Queue<int[]> all = new LinkedList<>();
        for (int i=0; i<h; i++){
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<w; j++){

                if (input[j]=='.') land[i][j]=0;
                else {
                    land[i][j]= input[j]-'0';
                    all.add(new int[]{i,j});
                }
            }
        }

        int waveCnt=0;
        Queue<int[]> bye = new LinkedList<>();
        while (true){
            int size=all.size();
            while (size>0){

                int[] now = all.poll();
                int emptyCnt=0;

                for (int i=0; i<8; i++){
                    int[] next = new int[]{
                            now[0]+direction[i][0],
                            now[1]+direction[i][1]
                    };
                    if (land[next[0]][next[1]]==0) emptyCnt++;
                }
                if (emptyCnt>=land[now[0]][now[1]]) {
                    bye.add(now);
                }
                else {
                    all.add(now);
                }

                size--;
            }
            if (bye.isEmpty()){
                break;
            }
            else {
                waveCnt++;
                while (!bye.isEmpty()){
                    int[] now = bye.poll();
                    land[now[0]][now[1]]=0;
                }
            }
        }

        System.out.println(waveCnt);
    }
}
