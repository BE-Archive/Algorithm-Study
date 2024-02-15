import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        Arrays.sort(arr);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sel = Integer.parseInt(st.nextToken());

        if (sel == 1) {
            int cnt = Integer.parseInt(st.nextToken());
            solve1(arr, cnt);
        } else {
            int[] perm = new int[N];
            for (int i = 0; i < N; i++) {
                perm[i] = Integer.parseInt(st.nextToken());
            }
            solve2(arr, perm);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve2(int[] arr, int[] perm) {
        boolean isEqaul = false;
        long cnt = 0L;
        do {
            cnt++;
            for (int i = 0; i < N; i++) {
                if (arr[i] != perm[i]) {
                    isEqaul = false;
                    break;
                }
                isEqaul = true;
            }
        } while (nextPermutation(arr) && !isEqaul);

        sb.append(cnt);
    }

    private static void solve1(int[] arr, long cnt){
        do {cnt--;} while (nextPermutation(arr) && cnt != 1);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }
    }

    private static boolean nextPermutation(int[] arr) {
        int i = N - 1;

        while (i > 0 && arr[i - 1] >= arr[i]) i--;

        if(i == 0) return false;

        int j = N - 1;

        while (arr[i - 1] >= arr[j]) j--;
        swap(arr, i - 1, j);

        int k = N - 1;

        while (i < k) swap(arr, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}