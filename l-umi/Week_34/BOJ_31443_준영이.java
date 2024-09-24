import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int mod = 1_000_000_007;

        // scrap : 남은 초콜릿의 총 면적
        long scrap = N * M;
        // joy : 최대 기쁨을 저장
        long joy = 1;

        /* 
         * 초콜릿 조각이 4개 초과일 때,
         * 가능한 많은 1x3 조각은 만듦
         */
        while (scrap > 4) {
            joy *= 3;
            joy %= mod;
            scrap -= 3;
        }
        /*
         * 초콜릿 조각이 4개 이하일 때,
         * 조각을 더 이상 나누지 않고 그대로 사용
         */
        joy *= scrap;
        joy %= mod;
        System.out.println(joy);
    }

}


