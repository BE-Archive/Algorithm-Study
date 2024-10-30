import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_20437_문자열_게임2 {

    static int LENGTH = 26;
    static int T;
    static int K;
    static char[] input;
    static Queue<Integer>[] queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int tset_case=0; tset_case<T; tset_case++){
            input = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());
            int length = input.length;

            // K가 1인 경우
            if(K==1){
                System.out.println(1 + " " + 1);
                continue;
            }

            // queue 테이블 초기화
            queue = new Queue[LENGTH];
            for(int i=0; i<LENGTH; i++){
                queue[i] = new ArrayDeque<>();
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int i=0; i<length; i++){
                int alp = input[i] - 'a';
                queue[alp].add(i);

                if(queue[alp].size() == K){    // 문자열에서 alphabet 이 K만큼 있는 경우
                    int start = queue[alp].peek();
                    int end = i;

                    min = Math.min(min, end-start+1);
                    max = Math.max(max, end-start+1);

                    queue[alp].poll();
                }
            }

            if(min!=Integer.MAX_VALUE){
                System.out.println(min + " " + max);
            }else{
                System.out.println(-1);
            }
        }
    }
}