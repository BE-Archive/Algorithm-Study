import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] isCheck = new boolean[26];

    static int findShortcut(final String word, final int range) {
        for(int i=0; i<= range; ++i) {
            int idx = word.charAt(i) - 'a';
            if(idx<0 || idx >= 26 || isCheck[idx]) continue;
            isCheck[idx] = true;
            return i;
        }
        return -1;
    }

    static int findShortcut(final String inp) {
        String[] words = inp.toLowerCase().split(" ");

        // 먼저 모든 단어의 첫글자를 본다.
        int offset = 0;
        for(final String word : words) {
            int idx = findShortcut(word, 0);
            if(idx != -1) {
                return idx + offset;
            }
            offset += word.length() + 1;
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
            int shortcut = findShortcut(inp);
            if (shortcut == -1)
                sb.append(inp).append("\n");
            else {
                sb.append(inp.substring(0, shortcut))
                        .append("[").append(inp.charAt(shortcut)).append("]");

                if (shortcut + 1 < inp.length())
                    sb.append(inp.substring(shortcut + 1));

                sb.append("\n");
            }
        }

        System.out.print(sb);
    } // main
}
