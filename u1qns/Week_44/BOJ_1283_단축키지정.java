import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] isCheck = new boolean[28];

    static int findShortcut(final String word, int range) {
        for(int i=0; i<=range; ++i) {
            int idx = word.charAt(i) - 'a';
            if(isCheck[idx]) continue;
            isCheck[idx] = true;
            return i;
        }
        return -1;
    }

    static int solve(String inp) {
        String[] words = inp.toLowerCase().split(" ");

        // 먼저 모든 단어의 첫글자를 본다.
        int tmp = 0;
        for(String word : words) {
            if(findShortcut(word, 0) != -1)
                return tmp;
            tmp += word.length() + 1;
        }

        // 왼쪽부터 단어의 문자열 탐색
        return findShortcut(inp.toLowerCase(), inp.length()-1);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        while(N -- > 0) {
            String inp = br.readLine();
            int shortcut = solve(inp);
            if(shortcut != -1) {
                sb.append(inp.substring(0, shortcut))
                        .append("[").append(inp.charAt(shortcut)).append("]")
                        .append(inp.substring(shortcut + 1));              
            } else
                sb.append(inp);
          
            sb.append("\n");
        }

        System.out.print(sb);
    } // main
}
