import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1756 {
    public static int[] oven;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int d = Integer.parseInt(st.nextToken()); //오븐 깊이
        int n = Integer.parseInt(st.nextToken()); //피자 반죽 개수

        oven = new int[d+1];
        st = new StringTokenizer(br.readLine().trim());
        oven[0]=Integer.MAX_VALUE;
        for (int i=1; i<=d; i++){
            oven[i]=Math.min(Integer.parseInt(st.nextToken()), oven[i-1]);
        }

        st = new StringTokenizer(br.readLine().trim());
        int bottom=d;
        for (int i=0; i<n; i++){
            int cur = Integer.parseInt(st.nextToken());
            while (bottom>=0 && cur>oven[bottom--]);
        }

        System.out.println(bottom+1);
    }

}
