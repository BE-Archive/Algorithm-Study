import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    final static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = len-;
        int a = 0, b = 0;
        int len, idx = 0;

        String input = br.readLine();
        len = input.length();

        for(int i=0; i<len; ++i)
            if(input.charAt(i) == 'a') ++a;

        for(int i=0; i<len; ++i)
        {
            b = 0;
            if(input.charAt(i) == 'a') continue;
            for(int j=0; j<a; ++j)
            {
                idx = (i+j+1) % len;
                if(input.charAt(idx) == 'b')
                    ++b;
            }
            answer = Math.min(answer, b);
        }
        
        System.out.print(answer==INF ? 0 : answer);
    }
}
