import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] time = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            time[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(time);
        for (int i=1; i<n; i++) time[i]+=time[i-1];

        long sum=0;
        for (long t : time) sum+=t;
        System.out.println(sum);
    }

}
