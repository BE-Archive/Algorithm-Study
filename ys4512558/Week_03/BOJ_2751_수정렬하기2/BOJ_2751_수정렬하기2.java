import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] arr, temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        temp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0, N - 1);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void mergeSort(int left, int right) {
        if(left < right){
            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private static void merge(int left, int mid, int right) {
        int idx = left;
        int l = left;
        int r = mid + 1;

        while (l <= mid && r <= right) {
            temp[idx++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
        }
        for (int i = l; i <= mid; i++) {
            temp[idx++] = arr[i];
        }
        for (int i = r; i <= right; i++) {
            temp[idx++] = arr[i];
        }

        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    private static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}