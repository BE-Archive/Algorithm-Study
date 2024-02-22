package seoyoung059.Week_05.BOJ_1043;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1043{
        static int n, m;
        static int[] p;
        static int find(int x){
            if(p[x]==x) return x;
            return p[x] = find(p[x]);
        }
        static void union(int x, int y) {
            int tmp = find(x);
            int tmp2 = find(y);
            if(tmp==tmp2) return;
            if(tmp<tmp2){
                p[tmp2] = tmp;
            }
            else {
                p[tmp] = tmp2;
            }
        }
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int answer = 0; int cnt = 0;
            int[][] group = new int[m][];

            p = new int[n+1];
            for (int i = 1; i < n+1; i++) {
                p[i] = i;
            }

            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int i = 0; i < k; i++) {
                p[Integer.parseInt(st.nextToken())] = 0;
            }

            int g, a, b;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                g = Integer.parseInt(st.nextToken());
                group[i] = new int[g];
                a = Integer.parseInt(st.nextToken());
                group[i][0] = a;
                for (int j = 1; j < g; j++) {
                    b = Integer.parseInt(st.nextToken());
                    union(a,b);
                    group[i][j] = b;
                }
            }


            group: for (int i = 0; i < m; i++) {
                for (int j = 0; j < group[i].length; j++) {
                    if (find(group[i][j])==0){
                        continue group;
                    }
                }
                answer++;
            }
            System.out.println(answer);
        }
}