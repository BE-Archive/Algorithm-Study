import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] sentence;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sentence = br.readLine().toCharArray();
        size = sentence.length;

        if(size==1) {
            System.out.println(1);
            return;
        }

        int answer = size * 2;

        for(int i=0; i<size; i++){
            if(isPalindrome(i, size-1)){
                answer = size + i;
                break;
            }
        }

        System.out.println(answer);
    }

    static boolean isPalindrome(int left, int right){
        while(left < right){
            if(sentence[left++] != sentence[right--]){
                return false;
            }
        }
        return true;
    }
}