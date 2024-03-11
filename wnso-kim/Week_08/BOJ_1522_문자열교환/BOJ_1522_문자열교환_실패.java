import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

    static String input;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        System.out.println(Math.min(swap('a','b'), swap('b','a')));        
    }

    static int swap(char a, char b){
        char[] str = input.toCharArray();
        System.out.println(Arrays.toString(str));
        
        int swap = 0;               // 정답

        int left = 0;               // 왼쪽 포인터
        int right = str.length-1;   // 오른쪽 포인터

        int cntByleft = 0;          // 왼쪽 b 개수
        int cntByright = 0;         // 오른쪽 b 개수

        while(left < right){
            boolean l = false, r = false;  

            if(str[left]==b && str[left+1]==a) l = true;        // b a  인 경우
            else if(str[left]==b) {cntByleft++; left++;}        // b    인 경우
            else left++;                                        // a    인 경우
            
            if(str[right-1]==a && str[right]==b) r = true;      // a b  인 경우
            else if(str[right]==b) {cntByright++; right--;}     //   b  인 경우
            else right--;                                       //   a  인 경우

            if(l && r){
                if(cntByleft < cntByright){ // 양측 b의 개수로 연결 방향 판단
                    str[left-cntByleft] = a; 
                    str[right-1] = b;
                    cntByleft--;
                }
                else{
                    str[right+cntByright] = a; 
                    str[left+1] = b;
                    cntByright--;
                }
                
                System.out.println(left + " " +right+
                Arrays.toString(str));
                swap++;
            }
        }

        System.out.println();
        

        return swap;
    }
}
