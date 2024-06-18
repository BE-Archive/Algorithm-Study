import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int min=v/a, max=v/(a-b);
        while (min<max){
            int mid=(min+max)/2;
            int midVal = a*mid - b*(mid-1);
            System.out.println(min+" "+max+" "+mid+" "+midVal);
            if (midVal<v){
                min=mid+1;
            }

        }

        System.out.println(max);
    }
}
