import java.io.*;
import java.util.*;

public class BOJ_1516_게임개발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int time[] = new int[N];
        int degree[] = new int[N];
        int answer[] = new int[N];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1)
                    break;

                list.get(x-1).add(i);
                degree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // 즉시 지을 수 있는 건물들(진입 차수 0)
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                answer[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for(int num : list.get(now)){
                degree[num]--;
                answer[num] = Math.max(answer[num], answer[now] + time[num]);

                if(degree[num] == 0){
                    queue.offer(num);
                }
            }

        }

        for(int num : answer){
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }
}