import java.io.*;
import java.util.*;
public class Main {

    final static int MAX_VALUE = 1_000_000_000;
    static int N, L, W, H, answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 조건을 만족하는 A를 최대로 지정했다가 이분 탐색으로 조지면 될 것 같음
        int left = 1, right = MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;
            if( <= mid)
            else ()


        }

        System.out.print(right);
    } // main
}
