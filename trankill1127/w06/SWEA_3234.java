import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3234 {

    public static int n;
    public static int[] ws;
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++){

            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            ws = new int[n];
            for (int i=0; i<n; i++) ws[i]=Integer.parseInt(st.nextToken());
            Arrays.sort(ws);

            cnt=0;
            do {
                solution(0,0,0);
            } while(nextPermutation());

            sb.append("#"+tc+" ").append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean nextPermutation(){
        int i = ws.length-1;
        while (ws[i-1]>=ws[i]) {
            i--;
            if (i==0) return false;
        }

        int j = ws.length-1;
        while (ws[i-1]>=ws[j]) j--;

        int tmp=ws[i-1];
        ws[i-1]=ws[j];
        ws[j]=tmp;

        Arrays.sort(ws, i, ws.length);

        return true;
    }

    public static void solution(int idx, int leftSum, int rightSum){
        if (leftSum<rightSum) return;

        if (idx==n) {
            cnt++;
            return;
        }

        solution(idx+1,leftSum,rightSum+ws[idx]);
        solution(idx+1, leftSum+ws[idx], rightSum);
    }

}