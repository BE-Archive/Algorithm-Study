import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17135 {

    public static int n;
    public static int m;
    public static int d;
    public static int[][] map;
    public static int[][] copyMap;
    public static int[] archers = new int[3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        copyMap = new int[n][m];

        solution(0,0);
        System.out.println(maxCnt);
    }

    public static int maxCnt=0;

    public static void solution(int idx,int start){
        if (idx==3){
            System.out.println(Arrays.toString(archers));

            for (int i=0; i<n; i++)
                for (int j=0; j<m; j++)
                    copyMap[i][j]=map[i][j];

            maxCnt=Math.max(maxCnt, getScore());
            return;
        }

        for (int i=start; i<m; i++){
            archers[idx]=i;
            solution(idx+1, i+1);
        }
    }

    public static int getScore(){
        int totScore=0;
        int leftEnemy = 0;

        do {
            totScore += attack();

            System.out.println("현재 누적 점수는 "+totScore);

            move();

            leftEnemy=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 1) leftEnemy++;
                }
            }
        } while (leftEnemy != 0);

        return totScore;
    }

    public static void move(){
        for (int i=0; i<m; i++){
            for (int j=n-1; j>0; j--){
                copyMap[j][i]=copyMap[j-1][i];
            }
            copyMap[0][i]=0;
        }
    }

    public static int attack(){
        int score=0;

        outer:
        for (int a=0; a<3; a++){ //궁수가 공격한 대상을 찾는다.

            for (int i=0; i<m; i++){
                for (int j=1; j<=d; j++){
                    int x = n-j;

                    if (copyMap[x][i]==0) continue;

                    int distance = (n-x)+Math.abs(archers[a]-i);

                    System.out.println(n+" "+archers[a]+"~"+x+" "+i+" : "+distance);

                    if (distance<=d) {
                        if (copyMap[x][i]==1) { //공격받은 적이 없는 적이라면
                            score++;
                            copyMap[x][i]=-1;
                        }
                        else { //-1일 때, 즉 공격받은 적이 있는 적이라면

                        }
                        continue outer;
                    }
                }
            }

        }

        return score;
    }

}
