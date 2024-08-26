package Week_31.BOJ_2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    static int[] arr;
    static int n;

    static int count(int minVal) {
        int cnt = 1, prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - prev >= minVal) {
                cnt++;
                prev = arr[i];
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 0, end = arr[n-1]-arr[0], mid;
        while(start <= end) {
            mid = (start + end)/2;

            // mid로 둘 때 c보다 많거나 같은 공유기 설치 => mid를 키워 더 적은 공유기 사용
            if(count(mid) >= c) start = mid + 1;
            // mid로 둘 때 c보다 적은 공유기 설치
            else end = mid - 1;
        }

//        System.out.println(start);
        int prev = arr[0];
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - prev >= start - 1) {
                answer = Math.min(answer, arr[i] - prev);
                prev = arr[i];
            }
        }

        System.out.println(answer);
    }
}
