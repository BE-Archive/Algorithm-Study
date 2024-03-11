import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BOJ_1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int aCnt=0;
        for (int i=0; i<s.length; i++) {
            if (s[i]=='a') aCnt++;
        }

        int minCnt=Integer.MAX_VALUE;
        for (int i=0; i<s.length; i++) {
            int cnt=0;
            for (int j=0; j<aCnt; j++) {
                if (s[(i+j)%s.length]=='b') cnt++;
            }
            if (minCnt>cnt) minCnt=cnt;
        }

        System.out.println(minCnt);
    }
}
