import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1041_주사위 {
    static long N;
    static int[] dice = new int[6];
    static long one = 50, two = 100, thr = 150;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++){
            dice[i] = Integer.parseInt(stk.nextToken());
            one = Math.min(one, dice[i]);
        }

        if(N==1){
            int max = 0;
            for(int i=0; i<6; i++){
                sum += dice[i];
                max = Math.max(max, dice[i]);
            }

            System.out.println(sum - max);
            return;
        }

        makeThreeeeeee();

        // 꼭지점
        sum += thr * 4;
        
        // 모서리
        sum += two*(N-2)*4 + two*(N-1)*4;

        // 면
        sum += one*(N*N*5 - (4*3) - (N-2)*4*2 - (N-1)*4*2);

        // 출력
        System.out.println(sum);
    }

    static void makeThreeeeeee(){
        int ab = dice[0]+dice[1];
        int bf = dice[1]+dice[5];
        int fe = dice[5]+dice[4];
        int ea = dice[0]+dice[4];

        int de = dice[3]+dice[4];
        int da = dice[3]+dice[0];
        int db = dice[3]+dice[1];
        int df = dice[3]+dice[5];

        int ce = dice[2]+dice[4];
        int ca = dice[2]+dice[0];
        int cb = dice[2]+dice[1];
        int cf = dice[2]+dice[5];

        two = Math.min(ab, (Math.min(bf, Math.min(fe, ea))));
        two = Math.min(two, Math.min(de, Math.min(da, Math.min(db, df))));
        two = Math.min(two, Math.min(ce, Math.min(ca, Math.min(cb, cf))));

        thr = Math.min(ab, (Math.min(bf, Math.min(fe, ea)))) + Math.min(dice[2], dice[3]);
    }

}
