import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B1253 {

    static int N;
    static int[] Ai;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());

        Ai = new int[N];
        for(int i=0; i<N; i++){
            Ai[i] = Integer.parseInt(stk.nextToken());
            map.put(Ai[i] ,map.getOrDefault(Ai[i], 0)+1);
        }

        int answer = 0;
        for(int i=0; i<N; i++){
            if(Ai[i] == 0) continue;

            for(int j=0; j<N; j++) {
                if (i == j) continue;

                int sum = Ai[i] + Ai[j];
                if(!map.containsKey(sum)) continue;
                if(sum == Ai[i] && map.get(sum)==1) continue;

                answer += map.get(sum);
                map.remove(sum);
            }
        }

        int countZero = map.getOrDefault(0, 0);
        if(countZero >=3) answer += countZero;

        System.out.println(answer);
    }
}
