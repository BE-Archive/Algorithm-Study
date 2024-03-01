import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        selectionSort(arr);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    //버블정렬
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (arr[j - 1] > arr[j]) swap(arr, j - 1, j);
            }
        }
    }

    //선택정렬
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < N; i++) {
            int idx = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[idx] > arr[j]) idx = j;
            }
            swap(arr, i, idx);
        }
    }
    
    //삽입정렬
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < N; i++) {
            int idx = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[idx] < arr[j]) {
                    swap(arr, idx, j);
                    idx = j;
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}