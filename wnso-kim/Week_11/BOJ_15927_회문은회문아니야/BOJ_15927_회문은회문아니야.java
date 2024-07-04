import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15927_회문은회문아니야 {
    // 문장이 팰린드롬인 경우
    // 전부 같은 문장인지 아닌지 확인
    
    // 문장이 팰린드롬이 아닌 경우
    // 끝 알파벳이 다르면 바로 길이 반환
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();
    
        // 문장이 팰린드롬이 아닌 경우
        if(input.charAt(0) != input.charAt(length-1)){
            System.out.println(length);
            return;
        }

        boolean isPALINDROME = true;
        int half = length%2==0? length/2: length/2+1;
        for(int i=0; i<half; i++){
            if(input.charAt(i) != input.charAt(length-i-1)){
                isPALINDROME = false;
                break;
            }
        }

        // 팰린드롬이 아닌 경우
        if(!isPALINDROME){
            System.out.println(length);
            return;
        }

        boolean isSAME = true;
        char first = input.charAt(0);
        for(int i=0; i<half; i++){
            if(input.charAt(i) != first){
                isSAME = false;
                break;
            }
        }

        // 팰린드롬이고, 전부 같은 문장인 경우
        if(isSAME){
            System.out.println(-1);   
        }// 팰린드롬은 맞지만, 전부 같은 문장이 아닌 경우
        else{
            System.out.println(length-1);
        }
    }
}
