import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_12904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine(); //전
        String t = br.readLine(); //후
        System.out.println(check(s,t));
    }

    private static int check(String s, String t){
        StringBuilder sb = new StringBuilder(t);

        while (sb.length()>s.length()){
            if (sb.charAt(sb.length()-1)=='A') sb.setLength(sb.length()-1);
            else {
                sb.setLength(sb.length()-1);
                sb.reverse();
            }
        }

        if (s.equals(sb.toString())) return 1;
        else return 0;
    }
}
