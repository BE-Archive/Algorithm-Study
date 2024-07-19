package Week_26.BOJ_15553;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15553 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer;

        if(n==1) {
            answer = 1;
        } else {
            int[] arr = new int[n - 1];
            int start = Integer.parseInt(br.readLine());
            int prev = start, curr = start + 1;
            for (int i = 0; i < n - 1; i++) {
                curr = Integer.parseInt(br.readLine());
                arr[i] = curr - prev - 1;
                prev = curr;
            }

            Arrays.sort(arr);
            answer = curr - start + 1;
            for (int i = 0; i < k - 1; i++) {
                answer -= arr[n - 2 - i];
            }
        }
        System.out.println(answer);
    }
}
