import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_2042 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        num = new long[n+1];
        t = new long[n+1];
        for (int i=1; i<n+1; i++) {
            num[i]=Long.parseLong(br.readLine().trim());
            update(i, num[i]);
        }

        for (int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a==1) { //b번째 수를 c로 변경한다.
                update(b,c-num[b]);
                num[b]=c;
            }
            else { //b번째 수부터 c까지의 수를 출력한다.
                sb.append(sum((int)c)-sum(b-1)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static long[] num;
    public static long[] t;

    public static long sum(int i) {
        long total=0;
        while (i>0){
            total+=t[i];
            i-=(i & -i);
        }
        return total;
    }

    public static void update(int i, long diff) {
        while (i<t.length){
            t[i]+=diff;
            i+=(i & -i);
        }
    }
}

