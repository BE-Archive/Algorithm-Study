import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        //
        long[] arr = new long[6];

        long min = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            min = Math.min(min, arr[i]);
        }
        /**
         * 총 N * 5개의 수를 선택해야 함
         * 꼭지점은 3면의 합이 최소인 경우를 골라야함
         * 경우의 수 (4개 : 맨 위 꼭지점 4개)
         * A, B, D (0, 1, 3)
         * A, B, C (0, 1, 2)
         * A, E, C (0, 4, 2)
         * A, E, D (0, 4, 3)
         * B, D, F (1, 3, 5)
         * B, C, F (1, 2, 5)
         * F, E, D (5, 4, 3)
         * F, E, C (5, 4, 2)
         */
        /**
         * 꼭지 점 제외 두 면을 사용하는 끝은 2개의 면의 합이 최소인 것을 골라야함
         * N - 2개 * 8 (모든 모서리 - 꼭지점 : 4)
         * A, (B, C, D, E) 0, (1,2,3,4)
         * B, (C, D, F) 1, (2, 3, 5)
         * C, (E, F) 2, (4, 5)
         * D, (E, F) 3, (4, 5)
         * E, F (4, 5)
         */
        long res = getMin(arr, min, N);
        System.out.println(res);
    }

    private static long getMin(long[] arr, long min, int N) {
        if(N == 1){
            long sum = 0;
            long max = 0;
            for (int i = 0; i < 6; i++) {
                sum += arr[i];
                max = Math.max(max, arr[i]);
            }
            return sum - max;
        }
        //3개의 면의 합이 최소인 것 : 꼭지점은 3개의 면이 필요함.
        long[] cases1 = {
                arr[0] + arr[1] + arr[2],
                arr[0] + arr[1] + arr[3],
                arr[0] + arr[2] + arr[4],
                arr[0] + arr[3] + arr[4],
                arr[1] + arr[2] + arr[5],
                arr[1] + arr[3] + arr[5],
                arr[2] + arr[4] + arr[5],
                arr[3] + arr[4] + arr[5]
        };
        Arrays.sort(cases1);

        //2개의 면의 합이 최소인 것 : 모서리는 두개의 면이 필요함.
        long[] cases2 = {
                arr[0] + arr[1],
                arr[0] + arr[2],
                arr[0] + arr[3],
                arr[0] + arr[4],
                arr[1] + arr[2],
                arr[1] + arr[3],
                arr[1] + arr[5],
                arr[2] + arr[4],
                arr[2] + arr[5],
                arr[3] + arr[4],
                arr[3] + arr[5],
                arr[4] + arr[5],
        };
        Arrays.sort(cases2);
        long res = min * (N - 2) * (N - 2); // 윗면의 모서리, 꼭지점 제외 최소로 채운 값 (정사각형)
        res += min * 4 * (N - 1) * (N - 2); // 옆면 4개의 모서리, 꼭지점 제외 최소로 채운 값 (직사각형)
        res += cases1[0] * 4; // 꼭지점
        res += cases2[0] * (N - 1) * 4; // 세로 모서리
        res += cases2[0] * (N - 2) * 4; // 윗면 모서리
        return res;
    }
}