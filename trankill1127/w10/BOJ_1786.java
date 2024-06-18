import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1786 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();
        String p = br.readLine();

        //부분 일치 테이블 만들기
        int[] k = new int[p.length()];
        int j = 0;
        for (int i=0; i < p.length(); i++) {
            while (j>0 && p.charAt(i) != p.charAt(j)) j=k[j-1];
            if (p.charAt(i) == p.charAt(j)) {
                j++;
                k[i] = j;
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        //문자열 매칭
        j = 0;
        for (int i=0; i < t.length(); i++) {
            while (j>0 && t.charAt(i) != p.charAt(j)) j=k[j-1];
            if (t.charAt(i) == p.charAt(j)) {
                if (j==p.length()-1){
                    sb.append(i-j+1).append(" ");
                    cnt++;
                    j=k[j];
                }
                else j++;
            }
        }

        System.out.println(cnt);
        System.out.print(sb.toString());
    }
}
