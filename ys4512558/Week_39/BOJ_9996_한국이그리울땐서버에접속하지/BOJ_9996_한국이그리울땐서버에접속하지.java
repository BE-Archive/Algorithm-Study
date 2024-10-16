import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(isMatch(pattern, br.readLine()) ? "DA" : "NE");
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean isMatch(String pattern, String str) {
        StringTokenizer st = new StringTokenizer(pattern, "*");
        String prefix = st.nextToken();
        boolean flag = str.startsWith(prefix);
        if(!flag) return false;
        str = str.substring(prefix.length());
        String suffix = st.nextToken();
        return str.endsWith(suffix);
    }

}