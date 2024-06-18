import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1978 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt=0;
        for (int i=0; i<n; i++){
            int number = Integer.parseInt(st.nextToken());
            if (isIt(number)) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean isIt(int n){
        if (n==1) return false;

        for (int i=2; i*i<=n; i++){
            if (n%i==0) return false;
        }
        return true;
    }

}
