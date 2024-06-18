import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15927 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        //팰린드롬인지 확인한다.
        int left=0, right=s.length-1;
        while (left<right && s[left]==s[right]){
            left++; right--;
        }
        //팰린드롬이 아니라면
        if (left<right){
            System.out.println(s.length);
        }
        else { //팰린드롬이면
            int sameCnt=0;
            for (int i=0; i<s.length; i++){
                if (s[0]==s[i]) sameCnt++;
                else break;
            }
            if (sameCnt==s.length) { //모든 문자가 동일하면
                System.out.println(-1);
            }
            else { //동일하지 않으면
                System.out.println(s.length-1);
            }
        }
    }
}