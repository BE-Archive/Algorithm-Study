import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008 {

    public static int n;
    public static int[] number;
    public static int maxResult;
    public static int minResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine().trim());
            int[] operator = new int[4];
            for (int i=0; i<4; i++) operator[i]=Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine().trim());
            number = new int[n];
            for (int i=0; i<n; i++) number[i]=Integer.parseInt(st.nextToken());
            maxResult=Integer.MIN_VALUE;
            minResult=Integer.MAX_VALUE;
            combination(0, operator, number[0]);
            sb.append("#").append(tc).append(" ").append(maxResult-minResult).append("\n");
        }
        System.out.println(sb.toString());
    }


    public static void combination(int nowIdx, int[] operator, int current){
        if (nowIdx==n-1){
            if (maxResult<current) maxResult=current;
            if (minResult>current) minResult=current;
            return;
        }

        if (operator[0]>0){
            operator[0]--;
            combination(nowIdx+1, operator, current+number[nowIdx+1]);
            operator[0]++;
        }
        if (operator[1]>0){
            operator[1]--;
            combination(nowIdx+1, operator, current-number[nowIdx+1]);
            operator[1]++;
        }
        if (operator[2]>0){
            operator[2]--;
            combination(nowIdx+1, operator, current*number[nowIdx+1]);
            operator[2]++;
        }
        if (operator[3]>0){
            operator[3]--;
            combination(nowIdx+1, operator, current/number[nowIdx+1]);
            operator[3]++;
        }
    }
}
