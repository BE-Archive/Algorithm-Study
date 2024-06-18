import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1034_램프 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력| N, M
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        // 입력| lines, K
        String[] lines = new String[N];
        for(int i=0; i<N; i++){
            lines[i] = br.readLine();
        }
        int K = Integer.parseInt(br.readLine());

        // 각 라인의 0을 모두 뒤집을 수 있는지 판단하고, 가능한 경우 map에 저장
        Map<Long, Integer> map = new HashMap<>();
        int answer = 0;
        for(String line: lines){
            long shift = 0;
            int count = 0;

            for(int j=0; j<M; j++){
                if(line.charAt(j)=='0'){
                    shift |= 1L<<j;
                    count++;
                }
            }
            if(count > K || (K-count)%2==1) continue;

            map.put(shift, map.getOrDefault(shift, 0)+1);
            answer = Math.max(answer, map.get(shift));
        }
        
        // 출력
        System.out.println(answer);
    }
}
