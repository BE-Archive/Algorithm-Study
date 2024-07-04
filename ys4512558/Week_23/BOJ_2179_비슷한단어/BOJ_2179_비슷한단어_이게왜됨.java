import java.io.*;
/**
 * 이게 왜 시간내에 들어오는지 모르겠는 코드
 */
public class BOJ2179_이게왜됨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = br.readLine();
        }

        int max = 0;
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int min = Math.min(strs[i].length(), strs[j].length());
                if(min <= max) continue; //어차피 끝까지 가도 안되면 스킵
                int cnt = 0;
                for (int k = 0; k < min; k++) {
                    if (strs[i].charAt(k) != strs[j].charAt(k)) break;
                    cnt++;
                }
                if(cnt > max) {
                    str1 = strs[i];
                    str2 = strs[j];
                    max = cnt;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str1).append("\n").append(str2);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}