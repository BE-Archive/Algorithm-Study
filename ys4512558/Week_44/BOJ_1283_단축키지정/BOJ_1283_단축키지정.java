import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] str = new String[N];
        boolean[] alphabet = new boolean[26];

        StringBuilder sb = new StringBuilder();

        Loop : for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
            StringTokenizer st = new StringTokenizer(str[i]);
            StringBuilder temp = new StringBuilder();
            int cnt = st.countTokens();
            for (int j = 0; j < cnt; j++) {
                StringBuilder s = new StringBuilder(st.nextToken());
                char c = s.charAt(0);
                int idx = getIdx(c);
                if (alphabet[idx]) {
                    temp.append(s + " ");
                    continue;
                }
                s.delete(0, 1);
                String string = "[" + c + "]";
                s.insert(0, string);
                alphabet[idx] = true;
                temp.append(s + " ");
                while (st.hasMoreTokens()) {
                    temp.append(st.nextToken() + " ");
                }
                sb.append(temp).append("\n");
                continue Loop;
            }

            StringBuilder s = new StringBuilder();
            boolean flag = false;
            for (int j = 0; j < str[i].length(); j++) {
                char c = str[i].charAt(j);
                int idx = getIdx(c);
                if(c == ' ' || alphabet[idx] || flag){
                    s.append(c);
                    continue;
                }
                flag = true;
                alphabet[idx] = true;
                s.append("[" + c + "]");
            }
            sb.append(s).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    public static int getIdx(char c) {
        if ('A' <= c && c <= 'Z') return c - 'A';
        return c - 'a';
    }

}