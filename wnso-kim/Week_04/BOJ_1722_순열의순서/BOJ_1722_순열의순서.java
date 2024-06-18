import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// N!에서 가지치기 하기
// (N-1)!이 몇개 들어가는지 확인
// (N-2)!이 몇개 들어가는지 확인
// 어,,? 그리디?
public class Main {
    static int N;
    static StringTokenizer stk;
    static long[] factorial;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stk = new StringTokenizer(br.readLine());
        int problem = Integer.parseInt(stk.nextToken());

        factorial();

        if(problem==1) problem1();
        else problem2();
    }

    static void factorial(){
        factorial = new long[N];
        factorial[N-1] = 1;
        if(N==1) return;

        factorial[N-2] = 1;
        for(int i=N-3,mul=2; i>=0; i--, mul++){
            factorial[i] = factorial[i+1]*mul;
        }
    }

    static void problem1(){
        long k = Long.parseLong(stk.nextToken());

        Stack<Integer> right = new Stack<>();
        Stack<Integer> left = new Stack<>();

        for(int i=N; i>=1; i--)
            right.push(i);

        for(int i=0; i<N; i++){
            long moc = k/factorial[i];
            if(k%factorial[i] == 0){
                moc--;    
            }

            k -= moc * factorial[i];

            while(moc-- > 0){
                left.push(right.pop());
            }

            result.append(right.pop() + " ");

            while(!left.isEmpty())
                right.push(left.pop());
        }

        System.out.println(result);
    }


    static void problem2(){
        long answer = 1;

        Stack<Integer> right = new Stack<>();
        Stack<Integer> left = new Stack<>();

        for(int i=N; i>=1; i--)
            right.push(i);

        for(int i=0; i<N; i++){
            int target = Integer.parseInt(stk.nextToken());

            while(right.peek() != target){
                answer += factorial[i];
                left.push(right.pop());
            }

            right.pop();

            while(!left.isEmpty())
                right.push(left.pop());
        }

        System.out.println(answer);
    }
}
