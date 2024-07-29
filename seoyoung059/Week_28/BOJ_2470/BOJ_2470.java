package Week_28.BOJ_2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        int l=0, r=0;
        int left, right, mid;

        for (int curr = 0; curr < n-1; curr++) {
            left = curr+1; right = n-1;
            while(left < right) {
                mid = (left + right) / 2;
                if(arr[mid]+arr[curr] < 0) left = mid+1;
                else right = mid;
            }
            int abs1 = Math.abs(arr[curr] + arr[right]);
            if(min > abs1){
                l = curr;
                r = right;
                min = abs1;
            }
            if(curr == right-1) continue;
            int abs2 = Math.abs(arr[curr] + arr[right - 1]);
            if(min > abs2){
                l = curr;
                r = right-1;
                min = abs2;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(arr[l]).append(' ').append(arr[r]);
        System.out.println(sb);
    }
}