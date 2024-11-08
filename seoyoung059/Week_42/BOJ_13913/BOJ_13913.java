package Week_42.BOJ_13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_13913 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(n, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.add(n);
        Integer curr = n;
        while(!queue.isEmpty()){
            curr = queue.poll();
            if(curr==k) break;
            if(!map.containsKey(curr+1) && curr+1<=2*k) {
                map.put(curr+1, curr);
                queue.add(curr+1);
            }
            if(!map.containsKey(curr-1) && curr-1>=0){
                map.put(curr-1, curr);
                queue.add(curr-1);
            }
            if(!map.containsKey(curr*2)&&curr*2<=2*k){
                map.put(curr*2, curr);
                queue.add(curr*2);
            }
        }

        ArrayDeque<Integer> answer = new ArrayDeque<>();
        answer.offerFirst(curr);
        while(map.get(curr)!=-1){
            answer.offerFirst(map.get(curr));
            curr = map.get(curr);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()-1).append('\n');
        while(!answer.isEmpty()){
            sb.append(answer.pollFirst()).append(' ');
        }
        System.out.println(sb);
    }
}
