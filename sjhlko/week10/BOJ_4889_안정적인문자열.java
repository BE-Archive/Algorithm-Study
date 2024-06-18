package sjhlko.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4889_안정적인문자열 {
    static String s;

    private static int solution() {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{'){
                stack.push(1);
            }
            else {
                if(!stack.isEmpty())
                    stack.pop();
                else {
                    stack.push(1);
                    count++;
                }
            }
        }
        count += stack.size() / 2;
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; ; tc++) {
            s = bf.readLine();
            if (s.charAt(0) == '-') return;
            System.out.println((tc+1) + ". " + solution());
        }
    }

}
