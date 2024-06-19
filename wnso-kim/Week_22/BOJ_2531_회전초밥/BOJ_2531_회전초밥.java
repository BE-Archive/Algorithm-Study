import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531_회전초밥 {

    static int N,D,K,C;
    static int[] sushis;
    static int[] check;
    static int count = 0;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        D = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        sushis = new int[2*N];  // 그냥 메모리를 더 쓰겠습니다.

        for(int i=0; i<N; i++){
            sushis[i] = sushis[i+N] = Integer.parseInt(br.readLine());
        }

        check = new int[D+1];
        check[C]++;
        count++;

        for(int i=0; i<K; i++){
            if(++check[sushis[i]] == 1) count++;
        }

        for(int i=K; i<=N+K; i++){
            if(--check[sushis[i-K]] == 0) count--;
            if(++check[sushis[i]] == 1) count++;
            
            answer = Math.max(answer, count);  
        }

        System.out.println(answer);
    }
}