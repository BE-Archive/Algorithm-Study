import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683 {

    static int n;
    static int m;
    public static int[][] area;

    public static class Pos{
        int x;
        int y;

        Pos(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<m; j++) {
                area[i][j]=Integer.parseInt(st.nextToken());
                if (0<area[i][j] && area[i][j]<6) s.add(new Pos(i,j));
                if (area[i][j]==0) blindSpot++;
            }
        }

        fill(0);
        System.out.println(blindSpot);
    }

    public static int blindSpot=0;
    public static ArrayList<Pos> s = new ArrayList<>();


    public static void fill(int idx) { //재귀 DFS

        if (idx==s.size()) {
            int cnt=0;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    //System.out.print(area[i][j]+" ");
                    if (area[i][j]==0) cnt++;
                }
                //System.out.println();
            }
            if (cnt<blindSpot) blindSpot=cnt;

            //System.out.println();
            //System.out.println();

            return;
        }

        Pos now = s.get(idx);
        int cam = area[now.x][now.y];

        if (cam==1) {
            see(now.x, now.y, 0);
            fill(idx+1);
            notSee(now.x, now.y, 0);

            see(now.x, now.y, 1);
            fill(idx+1);
            notSee(now.x, now.y, 1);

            see(now.x, now.y, 2);
            fill(idx+1);
            notSee(now.x, now.y, 2);

            see(now.x, now.y, 3);
            fill(idx+1);
            notSee(now.x, now.y, 3);
        }
        else if (cam==2) {
            see(now.x, now.y, 1); see(now.x, now.y, 3);
            fill(idx+1);
            notSee(now.x, now.y, 1); notSee(now.x, now.y, 3);

            see(now.x, now.y, 0); see(now.x, now.y, 2);
            fill(idx+1);
            notSee(now.x, now.y, 0); notSee(now.x, now.y, 2);
        }
        else if (cam==3) {
            see(now.x, now.y, 0); see(now.x, now.y, 1);
            fill(idx+1);
            notSee(now.x, now.y, 0); notSee(now.x, now.y, 1);

            see(now.x, now.y, 1); see(now.x, now.y, 2);
            fill(idx+1);
            notSee(now.x, now.y, 1); notSee(now.x, now.y, 2);

            see(now.x, now.y, 2); see(now.x, now.y, 3);
            fill(idx+1);
            notSee(now.x, now.y, 2); notSee(now.x, now.y, 3);

            see(now.x, now.y, 3); see(now.x, now.y, 0);
            fill(idx+1);
            notSee(now.x, now.y, 3); notSee(now.x, now.y, 0);
        }
        else if (cam==4) {
            see(now.x, now.y, 0); see(now.x, now.y, 1); see(now.x, now.y, 2);
            fill(idx+1);
            notSee(now.x, now.y, 0); notSee(now.x, now.y, 1); notSee(now.x, now.y, 2);

            see(now.x, now.y, 1); see(now.x, now.y, 2); see(now.x, now.y, 3);
            fill(idx+1);
            notSee(now.x, now.y, 1); notSee(now.x, now.y, 2); notSee(now.x, now.y, 3);

            see(now.x, now.y, 2); see(now.x, now.y, 3); see(now.x, now.y, 0);
            fill(idx+1);
            notSee(now.x, now.y, 2); notSee(now.x, now.y, 3); notSee(now.x, now.y, 0);

            see(now.x, now.y, 3); see(now.x, now.y, 0); see(now.x, now.y, 1);
            fill(idx+1);
            notSee(now.x, now.y, 3); notSee(now.x, now.y, 0); notSee(now.x, now.y, 1);
        }
        else { //5번 캠
            see(now.x, now.y, 0); see(now.x, now.y, 1); see(now.x, now.y, 2); see(now.x, now.y, 3);
            fill(idx+1);
            notSee(now.x, now.y, 0); notSee(now.x, now.y, 1); notSee(now.x, now.y, 2); notSee(now.x, now.y, 3);
        }

        return;
    }

    public static int[][] direction = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void see(int x, int y, int dirIdx) {
        while ( 0<=x+direction[dirIdx][0] && x+direction[dirIdx][0]<n
                && 0<=y+direction[dirIdx][1] && y+direction[dirIdx][1]<m ) {

            if (area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]==6) {
                break;
            }

            if (area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]<=0) {
                area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]--; //보임
            }

            x+=direction[dirIdx][0];
            y+=direction[dirIdx][1];
        }
    }

    public static void notSee(int x, int y, int dirIdx) {
        while ( 0<=x+direction[dirIdx][0] && x+direction[dirIdx][0]<n
                && 0<=y+direction[dirIdx][1] && y+direction[dirIdx][1]<m ) {

            if (area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]==6) {
                break;
            }

            if (area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]<=-1) {
                area[x+direction[dirIdx][0]][y+direction[dirIdx][1]]++; //보임
            }

            x+=direction[dirIdx][0];
            y+=direction[dirIdx][1];
        }
    }

}
