import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3101_토끼의이동 {

    static long N,K;
    static long r=0,c=0;
    static long answer = 1;
    static char[] commands;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Long.parseLong(stk.nextToken());
        K = Long.parseLong(stk.nextToken());

        commands = br.readLine().toCharArray();

        for(char cmd: commands){
            if(cmd == 'U') r--;
            else if(cmd == 'D') r++;
            else if(cmd == 'L') c--;
            else c++;
            
            move();    
        }
        
        System.out.println(answer);
    }

    static void move(){
        long rc = r+c;

        if(rc < N){
            answer += rc * (rc+1) / 2;
            answer += rc%2==0? c+1: r+1;
        }else{
            long R = N - 1 - c;
            long C = N - 1 - r;
            rc = R+C;
            answer += N*N - (rc+1)*(rc+2)/2;
            answer += rc%2==0? C+1: R+1;
        }   
    }
}
