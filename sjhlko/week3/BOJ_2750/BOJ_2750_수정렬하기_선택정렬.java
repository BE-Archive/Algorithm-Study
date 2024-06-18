package sjhlko.week3.BOJ_2750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2750_수정렬하기_선택정렬 {
    static int N;
    static int[] arr;

    static void selectionSort() {
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i; j < N; j++) {
                if (arr[minIndex] > arr[j])
                    minIndex = j;
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        selectionSort();
        printAns();
    }
}
