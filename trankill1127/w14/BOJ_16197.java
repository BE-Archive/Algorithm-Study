import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16197 {

    public static class Record {
        int[][] coins;
        int time;

        public Record(int[][] coins, int time) {
            this.coins = coins;
            this.time = time;
        }
    }

    public static int n;
    public static int m;
    public static char[][] map;
    public static int [][] direction = {
            {-1,0}, {1,0}, {0,-1}, {0,1}
    };
    public static ArrayList<int[]> coins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i=0; i<n; i++){
            map[i]=br.readLine().toCharArray();
            for (int j=0; j<m; j++){
                if (map[i][j]=='o') coins.add(new int[]{i,j});
            }
        }
        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<Record> q = new LinkedList<>();
        Record now = new Record(new int[][]{coins.get(0), coins.get(1)}, 0);
        q.add(now);

        while (!q.isEmpty()){
            now = q.poll();
            if (now.time+1>10) break;

            for (int i=0; i<4; i++){ //4가지 방향
                Record next = new Record(new int[2][2], now.time+1);
                //2개의 동전의 다음 위치를 확인한다.
                for (int j=0; j<2; j++){
                    next.coins[j][0]=now.coins[j][0]+direction[i][0];
                    next.coins[j][1]=now.coins[j][1]+direction[i][1];
                }

                //벽이 있는 곳이라면 가만히 있는다.
                int stuckCnt=0;
                for (int j=0; j<2; j++){
                    if (isAvailable(next.coins[j]) &&
                            map[next.coins[j][0]][next.coins[j][1]] == '#') {
                        next.coins[j][0]=now.coins[j][0];
                        next.coins[j][1]=now.coins[j][1];
                        stuckCnt++;
                    }
                }
                if (stuckCnt==2) //만약 두 동전 모두 가만히 있게 된다면 가지 않는다.
                    continue;

                //영역 밖으로 빠져나간 동전의 개수를 센다.
                int outCnt=0;
                for (int j=0; j<2; j++){
                    if ( ! isAvailable(next.coins[j]) ) outCnt++;
                }
                if (outCnt==0) { //영역 밖으로 빠져나간 게 없다면
                    q.add(next);
                }
                else if (outCnt==1) { //영역 밖으로 빠져나간 동전이 1개라면
                    return next.time;
                }
                //영역 밖으로 빠져나간 동전이 2개라면 아무것도 하지 않는다.
            }
        }

        return -1;
    }

    public static boolean isAvailable(int[] p){
        if (p[0]<0 || p[0]>=n || p[1]<0 || p[1]>=m) return false;
        return true;
    }
}
