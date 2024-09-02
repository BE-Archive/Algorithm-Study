package week57;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1253 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        HashMap<Integer, Integer> counts = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int count = counts.getOrDefault(arr[i], 0);
            counts.put(arr[i], count + 1);
        }

        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                int target = cur - arr[j];
                int idx = binarySearch(target);
                if (idx == -1) continue;
                if (arr[idx] == arr[i] && arr[idx] == arr[j] && counts.get(arr[idx]) <= 2) continue;
                if ((arr[idx] == arr[i] || arr[idx] == arr[j]) && counts.get(arr[idx]) < 2) continue;
                cnt++;
                break;
            }
        }
        System.out.println(cnt);
    }

    public static int binarySearch(int target) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] < target) {
                l = mid + 1;
            } else if(arr[mid] == target){
                return mid;
            } else {
                r = mid;
            }
        }
        return -1;
    }
}
