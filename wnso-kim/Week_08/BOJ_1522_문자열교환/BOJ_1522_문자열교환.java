import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();

        // dp
        int[] countA = new int[length*2+1];
        for(int i=1; i<=length*2; i++){
            countA[i] = countA[i-1] + (input.charAt((i-1)%length)=='a'? 1: 0);
        }

        // 문자열에 포함된 a의 갯수 카운트
        // stream은 성능 이슈로 사용하지 않음.(3배 차이남)
        // int maxCountOfA = (int)(input.chars().filter(ch -> ch == 'a').count());
        int maxCountOfA = 0;
        for(int i=0; i<length; i++){
            if(input.charAt(i) == 'a') maxCountOfA++;
        }

        // 확인
        int answer = Integer.MAX_VALUE;
        for(int i=maxCountOfA; i<=length*2; i++){
            int count = countA[i]-countA[i-maxCountOfA];
            answer = Math.min(answer, maxCountOfA-count);            
        }

        // 출력
        System.out.println(answer);
    }
}