import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112 {

    public static int d;
    public static int w;
    public static int k;
    public static int[][] cells;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            d = Integer.parseInt(st.nextToken()); //두께(3-13)
            w = Integer.parseInt(st.nextToken()); //가로 크기(1-20)
            k = Integer.parseInt(st.nextToken()); //합격 기준(1-d)
            cells = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < w; j++) {
                    cells[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            minCnt=d+1;
            process(0,0);
            sb.append("#").append(tc).append(" ").append(minCnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int minCnt;

    public static void process(int nowIdx, int changeCnt){
        if (minCnt<=changeCnt) return;
        if (nowIdx==d) {
            if (check()) {
                minCnt=Math.min(minCnt, changeCnt);
            }
            return;
        }

        int[] original = new int[w];
        for (int i=0; i<w; i++) original[i]=cells[nowIdx][i];

        for (int i=0; i<w; i++) cells[nowIdx][i]=0;
        process(nowIdx+1, changeCnt+1);

        for (int i=0; i<w; i++) cells[nowIdx][i]=1;
        process(nowIdx+1, changeCnt+1);

        for (int i=0; i<w; i++) cells[nowIdx][i]=original[i];
        process(nowIdx+1, changeCnt);

    }

    public static boolean check(){
        for (int i=0; i<w; i++){
            boolean isPossible=false;
            int sameCnt=1;
            for (int j=0; j<d-1; j++){
                if (cells[j][i]==cells[j+1][i]) sameCnt++;
                else {
                    sameCnt=1;
                }

                if (sameCnt>=k) {
                    isPossible=true;
                    break;
                }
            }
            if (!isPossible) return false;
        }
        return true;
    }
}

