import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> negatives = new ArrayList<>();
        List<Integer> positives = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num < 0) {
                negatives.add(-num);
            } else {
                positives.add(num);
            }
        }

        //음수, 양수 기준으로 절대값이 큰 값 기준으로 정렬
        Collections.sort(negatives, Collections.reverseOrder());
        Collections.sort(positives, Collections.reverseOrder());


        int sum = getSum(positives) + getSum(negatives);
        int end = 0;
        if (!positives.isEmpty() && !negatives.isEmpty()) {
            end = Math.max(positives.get(0), negatives.get(0));
        } else if (positives.isEmpty()) {
            end = negatives.get(0);
        } else {
            end = positives.get(0);
        }
        System.out.println(sum - end);
    }

    private static int getSum(List<Integer> numbers) {
        int cnt = 0;
        int sum = 0;
        for (Integer number : numbers) {
            if (cnt == 0) sum += (number * 2); //왕복 거리
            if (++cnt == M) cnt = 0;
        }
        return sum;
    }
}