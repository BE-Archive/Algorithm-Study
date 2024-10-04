import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13414_수강신청 {

    static int K,L;
    static Queue<String> queue = new ArrayDeque<>();
    static Map<String, Integer> index = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        K = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());

        for(int i=0; i<L; i++){
            String id = br.readLine();
            queue.add(id);
            index.put(id, i);
        }

        StringBuilder answer = new StringBuilder();
        int count = 0;

        for(int i=0; i<L; i++){
            if(count == K) break;

            String id = queue.poll();
            if(index.get(id) ==  i){
                answer.append(id).append("\n");
                count++;
            }
        }

        System.out.println(answer);
    }
}
