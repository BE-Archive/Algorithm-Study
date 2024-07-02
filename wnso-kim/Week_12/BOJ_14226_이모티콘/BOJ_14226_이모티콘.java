import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_14226_이모티콘 {

    static int S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        
        System.out.println(twoThousandYearsLater());
    }

    static int twoThousandYearsLater(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1,0,0});  // count, time, clipboard

        boolean[][] visit = new boolean[1001][1001];
        visit[0][1] = true;

        while(!queue.isEmpty()){
            int[] state = queue.poll();
            int count = state[0];
            int time = state[1];
            int clipboard = state[2];

            if(count == S) return time;

            // 1. 복사
            queue.offer(new int[]{count, time+1, count});

            // 2. 붙여넣기
            if(clipboard!=0 && count+clipboard<=S && !visit[clipboard][count+clipboard]){
                queue.offer(new int[]{count+clipboard, time+1, clipboard});
                visit[clipboard][count+clipboard] = true;
            }

            // 3. 삭제
            if(count>0 && !visit[clipboard][count-1]){
                queue.offer(new int[]{count-1, time+1, clipboard});
                visit[clipboard][count-1] = true;
            }
        }

        return 0;
    }
}