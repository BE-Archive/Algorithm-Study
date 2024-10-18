package seoyoung059.Week_39.BOJ_9996;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9996 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] pattern = br.readLine().split("[*]");
        for(String str: pattern){
            System.out.println(str);
        }
        int front = pattern[0].length();
        int back = pattern[1].length();
        String curr; int currLength;
        StringBuilder sb = new StringBuilder();
        
        loop:
        for (int i = 0; i < n; i++) {
            curr = br.readLine();
            currLength = curr.length()-1;
            if(currLength + 1 < front+back) {
                sb.append("NE").append('\n');
                continue loop;
            }
            for (int j = 0; j < front; j++) {
                if(curr.charAt(j)!=pattern[0].charAt(j)) {
                    sb.append("NE").append('\n');
                    continue loop;
                }
            }
            for (int j = 0; j < back; j++) {
                if(curr.charAt(currLength-j)!=pattern[1].charAt(back-1-j)){
                    sb.append("NE").append('\n');
                    continue loop;
                }
            }
            sb.append("DA").append('\n');
        }

        System.out.print(sb);
    }
}
