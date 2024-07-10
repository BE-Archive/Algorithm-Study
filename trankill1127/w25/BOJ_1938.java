import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1938 {

    public static int[][] dir = {
            {0,-1}, //왼쪽
            {0,1}, //오른쪽 //왼쪽+오른쪽=0도
            {-1,0}, //위쪽
            {1,0}, //아래쪽 //위쪽+아래쪽=90도
            {0, 0} //회전
    };
    public static char[][] land = null;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        land = new char[n][n];
        for (int i=0; i<n; i++){
            String s = br.readLine().trim();
            for (int j=0; j<n; j++){
                land[i][j]=s.charAt(j);
            }
        }

        int[] logPos = {-1,-1}; int logDir = -1;
        int[] endPos = {-1,-1}; int endDir = -1;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (logPos[0]==-1 && land[i][j]=='B'){ //logPos이 결정되지 않은 경우
                    for (int k=0; k<4; k+=2){
                        if ( !isInLand(i+dir[k][0], j+dir[k][1]) || !isInLand(i+dir[k+1][0], j+dir[k+1][1]))
                            continue;

                        if (land[i+dir[k][0]][j+dir[k][1]]=='B' &&
                                land[i+dir[k+1][0]][j+dir[k+1][1]]=='B') {
                            logPos[0]=i;
                            logPos[1]=j;
                            logDir=k;
                            break;
                        }
                    }
                }
                else if (endPos[0]==-1 && land[i][j]=='E') { //endPos이 결정되지 않은 경우
                    for (int k=0; k<4; k+=2){
                        if ( !isInLand(i+dir[k][0], j+dir[k][1]) || !isInLand(i+dir[k+1][0], j+dir[k+1][1]))
                            continue;

                        if (land[i+dir[k][0]][j+dir[k][1]]=='E' &&
                                land[i+dir[k+1][0]][j+dir[k+1][1]]=='E') {
                            endPos[0]=i;
                            endPos[1]=j;
                            endDir=k;
                            break;
                        }
                    }
                }

            }
        }

        boolean[][][] visited  = new boolean[n][n][4];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{logPos[0], logPos[1], logDir});
        visited[logPos[0]][logPos[1]][logDir]=true;

        int number=-1;
        while (!q.isEmpty()) {

            int size = q.size();
            number++;

            while (size>0) {
                int[] nowLog = q.poll();
                if (nowLog[0] == endPos[0] && nowLog[1] == endPos[1] && nowLog[2]==endDir ) {
                    System.out.println(number);
                    return;
                }

                for (int way=0; way<5; way++) {
                    if (way==4){ //회전
                        int nextX=nowLog[0];
                        int nextY=nowLog[1];
                        int nextD=(nowLog[2]==2) ? 0 : 2;

                        if ( !isInLand(nextX, nextY) || !isRotatable(nextX, nextY) || visited[nextX][nextY][nextD]) continue;
                        q.add(new int[]{nextX, nextY, nextD});
                        visited[nextX][nextY][nextD]=true;
                    }
                    else { //이동
                        int nextX=nowLog[0]+dir[way][0];
                        int nextY=nowLog[1]+dir[way][1];
                        int nextD=nowLog[2];

                        if (!isInLand(nextX, nextY) || !isMovable(nextX, nextY, nextD) || visited[nextX][nextY][nextD]) continue;
                        q.add(new int[]{nextX, nextY, nextD});
                        visited[nextX][nextY][nextD]=true;
                    }
                }

                size--;
            }
        }

        System.out.println(0);
        return;
    }

    public static boolean isInLand(int x, int y){
        if (x < 0 || x>=n || y<0 || y>=n) return false;
        return true;
    }

    public static boolean isRotatable(int x, int y){
        for (int i=-1; i<2; i++){
            for (int j=-1; j<2; j++){
                if ( !isInLand(x+i, y+j) ) return false;
                if ( land[x+i][y+j]=='1' ) return false;
            }
        }
        return true;
    }
    public static boolean isMovable(int x, int y, int d){
        if ( !isInLand(x+dir[d][0], y+dir[d][1]) || !isInLand(x+dir[d+1][0], y+dir[d+1][1]) ) return false;
        if (land[x][y]=='1' || land[x+dir[d][0]][y+dir[d][1]]=='1' || land[x+dir[d+1][0]][y+dir[d+1][1]]=='1') return false;
        return true;
    }
}

