import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(stk.nextToken());
        int U = Integer.parseInt(stk.nextToken());

        int[] zero2nine = {0,1,3,6,10,15,21,28,36,45};
        long answer = 0l;

        for(int i=L; i<=U;i++){
            int tmp = i;
            int sum = 0;
            
            while(tmp >0){
                sum += tmp%10;
                tmp /= 10;
            }

            int mod = i%10;
            int min = U-i>=10? 9: U%10;
            answer += sum * (min-mod+1);
            answer += (zero2nine[min-mod]);

            i = (i/10)*10+9;
        }

        System.out.println(answer);
    }
}