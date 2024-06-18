package Week_18.BOJ_17219_비밀번호찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219_비밀번호찾기 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        Map<String, String> map = new HashMap<>();

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            map.put(stk.nextToken(), stk.nextToken());
        }

        StringBuilder result = new StringBuilder();
        
        for(int i=0; i<M; i++){
            String input = br.readLine();
            result.append(map.get(input)).append("\n");
        }

        System.out.println(result);
    }
}