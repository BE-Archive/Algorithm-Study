import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_5002 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int n = str.length();
        int answer = 0, cnt = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            q.offerLast((str.charAt(i)=='M')?1:-1);
        }

        int tmp;
        while(!q.isEmpty()) {
            tmp = q.pollFirst();
            if(Math.abs(cnt+tmp) <= x){
                cnt+=tmp;
                answer++;
            }
            else if(!q.isEmpty() && Math.abs(cnt+q.peekFirst())<=x) {
                cnt+=q.pollFirst();
                q.offerFirst(tmp);
                answer++;
            }
            else break;
        }

        System.out.println(answer);
    }
}
