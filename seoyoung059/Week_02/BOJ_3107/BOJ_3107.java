package seoyoung059.Week_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3107 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LinkedList<String> ll = new LinkedList<>();
        String ipv6 = br.readLine();

        int cnt=0;
        char prev = ipv6.charAt(0);
        // 생략된 ipv6 주소 받는 부분
        for (int i = 1; i < ipv6.length(); i++) {
            if(ipv6.charAt(i)==':'){
                if(prev!=':') {
                    sb.append(prev);
                    ll.offer(sb.toString());
                    cnt++;
                } else {
                    ll.offer(" ");
                }
                sb = new StringBuilder();
            }
            else {
                if(prev!=':')
                    sb.append(prev);
                if (i==ipv6.length()-1)
                    sb.append(ipv6.charAt(i));
            }
            prev = ipv6.charAt(i);
        }
        ll.offer(sb.toString());
        cnt++;
        sb.setLength(0);

        // 주소 복원 부분
        for (String s: ll) {
            if(s.equals(" ")){
                for (int i = 0; i < 8-cnt; i++) {
                    sb.append("0000");
                    sb.append(":");
                }
            }
            else {
                for (int i = 0; i < 4-s.length(); i++) {
                    sb.append("0");
                }
                sb.append(s);
                sb.append(":");
            }
        }
        sb.setLength(39);

        System.out.println(sb.toString());
    }
}