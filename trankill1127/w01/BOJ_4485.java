import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4485 {

    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
    static Integer n;
    static Integer[][] arr;
    static boolean [][] visited;
    static int minRupee;
    static int rupee;

    public static void DFS(int x, int y){
        if (x==n-1 && y==n-1){
            if (rupee<minRupee) {
                minRupee=rupee;
            }
            return;
        }

        //System.out.println("processing...");

        for (int i=0; i<4; i++) {
            if (x + dx[i] < 0 || x + dx[i] > n - 1 || y + dy[i] < 0 || y + dy[i] > n - 1)
                continue;

            if (!visited[x + dx[i]][y + dy[i]] ) {
                visited[x + dx[i]][y + dy[i]]=true;
                rupee += arr[x + dx[i]][y + dy[i]];
                if (rupee<minRupee){
                    DFS(x + dx[i], y + dy[i]);
                }
                visited[x + dx[i]][y + dy[i]]=false;
                rupee -= arr[x + dx[i]][y + dy[i]];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(bf.readLine());

        int trial = 1;
        while (n!=0){

            arr = new Integer[n][n];
            for (int i=0; i<n; i++){
                String[] s = bf.readLine().split(" ");

                for (int j=0; j<n; j++){
                    arr[i][j]=Integer.parseInt(s[j]);
                }
            }

            minRupee = Integer.MAX_VALUE;
            rupee=arr[0][0];
            visited = new boolean[n][n];
            DFS(0, 0);

            System.out.println("Problem "+trial+": "+minRupee);

            n=Integer.parseInt(bf.readLine());
            trial++;
        }
    }
}