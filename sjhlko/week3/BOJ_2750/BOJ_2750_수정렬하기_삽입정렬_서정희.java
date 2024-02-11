package sjhlko.week3.BOJ_2750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2750_수정렬하기_삽입정렬_서정희 {
    //https://www.acmicpc.net/problem/2750
    //수 정렬하기
    static int N;
    static int[] arr;

    static void insertionSort() {
        for (int i = 0; i < N; i++) {
            int now = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (now < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = now;
                    continue;
                }
                break;
            }
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
        insertionSort();
        printAns();
    }
}
