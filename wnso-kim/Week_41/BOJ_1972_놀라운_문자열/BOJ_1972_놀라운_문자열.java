import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B1972 {

    static int length;
    static char[] inputArr;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();
            inputArr = input.toCharArray();

            // 마지막 입력인 경우 break
            if(inputArr[0] == '*') break;

            // 초기화
            boolean isSurprising = true;
            length = input.length();

            loop:
            for(int d=1; d<length; d++){
                set = new HashSet<>();

                for(int i=0; i+d<length; i++){
                    if(set.contains(""+ inputArr[i]+ inputArr[i+d])){
                        isSurprising = false;
                        break loop;
                    }

                    set.add(""+ inputArr[i]+ inputArr[i+d]);
                }
            }

            sb.append(input)
              .append(" is ")
              .append(isSurprising? "": "NOT ")
              .append("surprising.")
              .append("\n");
        }

        System.out.println(sb);
    }
}
