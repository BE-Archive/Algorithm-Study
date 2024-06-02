import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1461_도서관 {

    static int N,M;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 선언| 양수: 내림차순, 음수: 내림차순(양수로 바꾼 뒤)
        Queue<Integer> positive = new PriorityQueue<>();
        Queue<Integer> negative = new PriorityQueue<>();

        // 입력| N,M
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        // 입력| 책 위치
        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int number = Integer.parseInt(stk.nextToken());
            if(number>0) positive.add(number);
            else negative.add(Math.abs(number));

            answer = Math.max(answer, Math.abs(number));
        }

        answer = -answer;

        // 오른쪽 구하기
        oneStepTwoStepPollJjacPollJjac(positive);

        // 왼쪽 구하기
        oneStepTwoStepPollJjacPollJjac(negative);

        // 출력
        System.out.println(answer);
    }
    
    static void oneStepTwoStepPollJjacPollJjac(Queue<Integer> queue){
        int mod = queue.size()%M;
        int top = 0;

        while(mod-- > 0){
            top = Math.max(top, queue.poll());
        }

        answer += 2*top;

        while(!queue.isEmpty()){
            int size = Math.min(M, queue.size());

            while(size-- > 0){
                top = Math.max(top, queue.poll());
            }

            answer += 2*top;
        }
    }

    // -39 -37 -29 -28 -6 0 2 11
}
