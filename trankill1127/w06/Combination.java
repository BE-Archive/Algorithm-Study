import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Combination {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        number=new int[n];
        for (int i=0; i<n; i++) number[i]=i+1;
        memo = new int[r];

        combination(0,0);

        System.out.println(sb.toString());
    }

    public static int n;
    public static int[] number;
    public static int r;
    public static int[] memo;
    public static StringBuilder sb;

    public static void combination(int idx, int bef){
        if (idx==r){
            for (int i=0; i<idx; i++){
                sb.append(memo[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=bef; i<n; i++){
            memo[idx]=number[i];
            combination(idx+1, i+1);
        }
    }
}
