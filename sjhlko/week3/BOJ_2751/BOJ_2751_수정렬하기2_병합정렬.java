package sjhlko.week3.BOJ_2751;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751_수정렬하기2_병합정렬 {
    static int N;
    static int[] list, ans;

    static int[] divide(int start, int end) {
        if (start == end) return new int[]{list[start]};
        int[] left = divide(start, (start + end) / 2);
        int[] right = divide((start + end) / 2 + 1, end);
        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right) {
        int[] ret = new int[left.length + right.length];
        int i = 0, j = 0, index = 0;
        for (; i < left.length && j < right.length;) {
            if (left[i] <= right[j])
                ret[index++] = left[i++];
            else ret[index++] = right[j++];
        }
        while (i < left.length)
            ret[index++] = left[i++];
        while (j < right.length)
            ret[index++] = right[j++];
        return ret;
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(bf.readLine());
        }
        ans = divide(0, N - 1);
        printAns();
    }
}
