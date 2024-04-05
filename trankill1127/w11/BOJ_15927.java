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

/*
11:50-

펠린드롬이 아닌 가장 긴 부분문자열의 길이를 구해보자
길이 1~50만
있으면 길이를, 없으면 -1 출력

아니 근데 문제가 이상한데???

펠린드롬인 경우
- 모두 같은 경우
- 그렇지 않은 경우
펠린드롬이 아닌 경우 (여기서 작은 펠린드롬이 존재할 수 있는 거 아닌가?)
 */