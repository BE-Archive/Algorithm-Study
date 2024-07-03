package Week_19.BOJ_14621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621 {

    static int find(int[] arr, int x) {
        if (arr[x] == x) return x;
        return  arr[x] = find(arr, arr[x]);
    }

    static boolean union(int[] arr, int x, int y) {
        int xx = find(arr, x);
        int yy = find(arr, y);

        if(xx == yy) return true;

        if(xx > yy)
            arr[xx] = yy;
        else arr[yy] = xx;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] uni = new boolean[n + 1];
        String str = br.readLine();
        for (int i = 1; i < n + 1; i++) {
            uni[i] = str.charAt((2*(i-1)))=='M';
        }

        int u, v, d;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            if (uni[u] != uni[v]) {
                pq.add(new int[]{u, v, d});
            }
        }


        int cnt = 0, sum = 0;
        int[] check = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            check[i] = i;
        }
        int[] curr;
        while(!pq.isEmpty() && cnt < n-1){
            curr = pq.poll();
            if(!union(check, curr[0], curr[1])) {
                cnt++;
                sum += curr[2];
            }
        }

        if (cnt == n - 1) {
            System.out.println(sum);
        } else System.out.println(-1);
    }
}
