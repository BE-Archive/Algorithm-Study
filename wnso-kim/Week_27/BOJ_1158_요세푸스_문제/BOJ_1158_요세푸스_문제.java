import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스_문제 {

    static int N,K;
    static int index = -1;
    static List<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        for(int i=1; i<=N; i++) list.add(i);

        result.append("<");
        while(list.size() != 1){
            index += K;
            index %= list.size();

            result.append(list.get(index)+", ");

            list.remove(index);
            index--;
        }

        result.append(list.get(0) + ">");

        System.out.println(result);
    }
}