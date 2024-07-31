import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int min = Integer.MAX_VALUE;
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < N - 1; i++) {
            int idx = lowerBound(arr, i+1, arr.length - 1, -arr[i]);
            if (idx != i && Math.abs(arr[i] + arr[idx]) < Math.abs(min)) {
                min = arr[i] + arr[idx];
                num1 = arr[i];
                num2 = arr[idx];
            }
            if (idx >= 1 && idx - 1 != i && Math.abs(arr[i] + arr[idx - 1]) < Math.abs(min)) {
                min = arr[i] + arr[idx - 1];
                num1 = arr[i];
                num2 = arr[idx - 1];
            }
        }
        sb.append(num1).append(" ").append(num2);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int lowerBound(int[] arr, int start, int end, int value) {

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < value) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}