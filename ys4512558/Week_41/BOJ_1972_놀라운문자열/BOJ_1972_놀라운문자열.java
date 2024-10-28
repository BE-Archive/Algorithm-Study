import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1972 {
    static String isSurprising = " is surprising.";
    static String isNotSurprising = " is NOT surprising.";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;
        while (!(str = br.readLine()).equals("*")) {
            int N = str.length();
            boolean flag = true;
            Loop:
            for (int i = 1; i <= N - 1; i++) {
                Set<String> set = new HashSet<>();
                for (int j = 0; j < N - i; j++) {
                    String subStr = String.valueOf(str.charAt(j)) + String.valueOf(str.charAt(j + i));
                    if (!set.add(subStr)) {
                        flag = false;
                        break Loop;
                    }
                }
            }
            sb.append(str);
            sb.append(flag ? isSurprising : isNotSurprising);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}