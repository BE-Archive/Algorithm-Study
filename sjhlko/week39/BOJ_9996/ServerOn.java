package sjhlko.week39.BOJ_9996;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerOn {
    //https://www.acmicpc.net/problem/9996
    //한국이 그리울땐 서버에 접속하지
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        String s = bf.readLine()
                       .replace("*", "[a-z]*");
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            String now = bf.readLine();
            if(now.matches(s)) sb.append("DA")
                                 .append("\n");
            else sb.append("NE")
                   .append("\n");
        }
        System.out.println(sb);
    }
}
