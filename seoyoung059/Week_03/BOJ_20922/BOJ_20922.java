package seoyoung059.Week_03.BOJ_20922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] check = new int[100_001];
        int left = 0;
        int right = 0;
        int answer = 0;
        check[arr[0]] = 1;
        while(left<n){
            while(right<n-1&&check[arr[right+1]]<k){
                right++;
                check[arr[right]]++;
            }
            answer = Math.max(answer, right-left+1);
            check[arr[left]]--;
            left++;
        }
        System.out.println(answer);
    }
}
