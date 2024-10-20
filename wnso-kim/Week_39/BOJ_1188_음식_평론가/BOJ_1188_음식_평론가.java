package BOJ_1188_음식_평론가;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1188_음식_평론가 {

    static int N,M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        
        N -= N/M*M; // 온전한 소세지 분배 가능한 경우 제거

        int mul = N*M;

        int answer = 0;
        for(int i=N; i<mul; i+=N){
            if(i%M != 0) answer++;
        }

        System.out.println(answer);
    }
}
