package Week_19.BOJ_17609_회문;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17609_회문 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력| N
        int N = Integer.parseInt(br.readLine());

        // 결과 확인
        for(int i=0; i<N; i++){
            int result = palindrome(br.readLine());

            sb.append(result).append("\n");
        }

        // 출력
        System.out.println(sb);
    }

    static int palindrome(String input){
        int left = 0;
        int right = input.length()-1;

        while(left < right){
            // 같은 경우
            if(input.charAt(left) == input.charAt(right)){
                left++; right--;
            }
            else {  // 다른 경우| left++ 또는 right--
                if(isPalindrome(input.substring(left, right)))
                    return 1;
                else if(isPalindrome(input.substring(left+1, right+1))) 
                    return 1;
                else
                    return 2;
            }
        }

        return 0;
    }

    static boolean isPalindrome(String input){
        int left = 0;
        int right = input.length()-1;

        while(left < right){
            // 같은 경우
            if(input.charAt(left) == input.charAt(right)){
                left++; 
                right--;
            }
            else return false;
        }

        return true;
    }
}
