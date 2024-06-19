import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_30805_사전_순_최대_공통_부분_수열 {

    static int N,M;
    static List<List<Integer>> first = new ArrayList<List<Integer>>();
    static List<List<Integer>> second = new ArrayList<List<Integer>>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<=100; i++){
            first.add(new ArrayList<>());
            second.add(new ArrayList<>());
        }

        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int number = Integer.parseInt(stk.nextToken());
            first.get(number).add(i);
        }

        M = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int number = Integer.parseInt(stk.nextToken());
            second.get(number).add(i);
        }

        f(-1, -1);

        System.out.println(result.size());

        for(int number: result){
            System.out.print(number + " ");
        }

        System.out.println();
    }  
    
    static void f(int left, int right){
        int a = -1, b = -1;

        for(int i=100; i>0; i--){
            a = -1; b = -1;

            for(int index: first.get(i)) {
                if(index > left){
                    a = index;
                    break;
                }
            }

            for(int index: second.get(i)) {
                if(index > right){
                    b = index;  
                    break;
                }
            }

            if(a != -1 && b != -1){
                result.add(i);
                f(a,b);
                return;
            }
        }
    }
}
