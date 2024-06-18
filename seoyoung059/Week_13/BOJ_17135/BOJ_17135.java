import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17135 {

    static int n, m;
    static void swap(int[] p, int i, int j){
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    static boolean nextPerm(int[] p){
        int n = p.length;
        int i = n-1;
        while(i>0 && p[i-1]>= p[i])i--;
        if(i==0) return false;

        int j = n-1;
        while(j>i && p[i-1] >= p[j]) j--;
        swap(p, i-1, j);

        int k = n-1;
        while(k>i) swap(p, i++, k--);
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] p = new int[m];
        for (int i = 1; i <= 3; i++) {
            p[m-i] = 1;
        }

        boolean[][] visited = new boolean[n][m];
        int cnt, ky, kx, idx, tmp;
        int answer = 0;
        int[][] kill = new int[3][2];
        do {
            cnt = 0;
            visited = new boolean[n][m];
            for (int i = n-1; i >= 0 ; i--) {
                idx = 0;
                loop: for (int j = 0; j < m; j++) {
                    tmp = Integer.MAX_VALUE;
                    if(p[j]==1){
                        ky = -1; kx=m;
                        for (int k = 0; k < d; k++) {
                            if(i-k<0) continue;
                            for (int l = Math.max(0, j-(d-k-1)); l <= Math.min(m-1, j+(d-1-k)); l++) {
                                if(arr[i-k][l]==1 && !visited[i-k][l]){
                                    if((Math.abs(j-l)+k < tmp) || (Math.abs(j-l)+k==tmp && l < kx)) {
                                        ky = i - k;
                                        kx = l;
                                        tmp = Math.abs(j-l)+k;
                                    }
                                }
                            }
                        }
                        if(kx!=m){
                            kill[idx][0] = ky;
                            kill[idx++][1] = kx;
                        }
                    }
                }
                for (int j = 0; j < idx; j++) {
                    if(!visited[kill[j][0]][kill[j][1]]){
                        visited[kill[j][0]][kill[j][1]]=true;
                        cnt++;
                    }
                }
            }
            answer = Math.max(cnt, answer);
        } while(nextPerm(p));

        System.out.println(answer);
    }
}
