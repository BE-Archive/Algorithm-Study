import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int MAX = 100_000;
    static int N,K;
    static int[] times;
    static int[] chains;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        times = new int[MAX+1];
        chains = new int[MAX+1];

        Arrays.fill(times, Integer.MAX_VALUE);

        bfs();

        StringBuffer sb = new StringBuffer();
        sb.append(times[K]).append("\n");
        
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        while(chains[K] != K){
            stack.push(chains[K]);
            K = chains[K];
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
    
    static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{N, 0});
        times[N] = 0;
        chains[N] = N;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int time = cur[1];

            for(int i=0; i<3; i++){
                int nextIndex = oper(x, i);
                int nextTime = time+1;
                
                if(nextIndex<0 || nextIndex>MAX) continue;
                if(times[nextIndex] <= nextTime) continue;

                times[nextIndex] = nextTime;
                chains[nextIndex] = x;

                queue.add(new int[]{nextIndex, nextTime});
            }
        }
    }

    static int oper(int x, int index){
        switch (index) {
            case 0: return x-1;
            case 1: return x+1;
            default: return x*2;
        }
    }
}