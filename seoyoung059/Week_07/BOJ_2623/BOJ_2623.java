package Week_07.BOJ_2623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_2623 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[] indegree = new int[n];
        ArrayDeque<Integer>[] arr = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayDeque<>();
        }

        int tmp, a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            tmp = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken())-1;
            for (int j = 1; j < tmp; j++) {
                b = Integer.parseInt(st.nextToken())-1;
                indegree[b]++;
                arr[a].offer(b);
                a = b;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if(indegree[i]==0) q.offer(i);
        }

        int curr, idx = 0;
        int[] answer = new int[n];
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            curr = q.poll();

            answer[idx++] = curr;
            sb.append(curr+1).append("\n");
            while(!arr[curr].isEmpty()){
                tmp = arr[curr].poll();
                if(--indegree[tmp]==0) q.offer(tmp);
            }
        }

        if(idx<n) System.out.println(0);
        else System.out.print(sb);
    }
}