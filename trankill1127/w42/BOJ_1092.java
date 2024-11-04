package trankill1127.w42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1092 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] ns = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            ns[i]=Integer.parseInt(br.readLine());
        }

        int bn = Integer.parseInt(br.readLine());
        int[] limit = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<bn; i++){
            limit[i]=Integer.parseInt(br.readLine());
        }

        /*
        11:12-
        크레임 N대
        1분에 박스 하나씩 배에 데려가기
        모든 크레인은 동시에 이동
        각 트레인마다 무게 제한 있어서 이것보다 무거운 건 못 넣음
        모든 박스를 배로 옮기는데 드는 시간은?
         */
    }

}
