package seoyoung059.Week_04.BOJ_14267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14267 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        int[] w = new int[n+1];
        st = new StringTokenizer(br.readLine());

        // 각 직원의 직속 상사 정보 저장
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 각 직원의 칭찬 값 누적 저장
        for (int i = 1; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());
            w[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        // 직원 번호가 낮을 수록 회사의 윗 직급이므로
        // 낮은 것부터 차례로 칭찬 누적합을 구함
        sb.append(0).append(" ");
        for (int i = 2; i < n+1; i++) {
            w[i] += w[arr[i]];
            sb.append(w[i]).append(" ");
        }
        System.out.print(sb);

    }
}