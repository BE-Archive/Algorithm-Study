package Week_33.BOJ_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        int[] cnt = new int[n+1];
        int[] time = new int[n+1];
        ArrayDeque[] deques = new ArrayDeque[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            deques[i] = new ArrayDeque<Integer>();
        }

        int a, b;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cnt[b]++;
            deques[a].offerLast(b);
        }
        int w = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n+1; i++) {
            if(cnt[i]==0) q.offerLast(i);
        }

        int curr; int next;
        while(!q.isEmpty()) {
            curr = q.pollFirst();
            time[curr] += arr[curr];
            if(curr == w) break;
            while(!deques[curr].isEmpty()) {
                next = (int) deques[curr].pollFirst();
                cnt[next]--;
                time[next] = Math.max(time[next], time[curr]);
                if(cnt[next]==0) {
                    if(next==w) return time[next]+arr[next];
                    q.offerLast(next);
                }
            }
        }

        return time[w];
    }
    public static void main(String[] args) throws Exception{

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            System.out.println(solve());
        }
    }

}
