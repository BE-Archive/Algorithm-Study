import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_5904_Moo_게임 {

    static int N;
    static Stack<Integer> moos = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        moos.add(3);

        for(int k=1, n= moos.peek(); n<N; k++, n=moos.peek())
            moos.add(moos.peek() + (k + 3) + moos.peek());

        while(moos.size() != 1){
            int now = moos.pop(); // 사용 안함
            int pre = moos.peek();
            int k = moos.size();

            if(pre < N && N < pre+(k+3)){
                System.out.println(pre+1==N? 'm': 'o');
                return;
            }

            if(pre+(k+3) < N)   N = N - (k+3) - pre;
        }

        System.out.println(N==1? 'm': 'o');
    }
}
