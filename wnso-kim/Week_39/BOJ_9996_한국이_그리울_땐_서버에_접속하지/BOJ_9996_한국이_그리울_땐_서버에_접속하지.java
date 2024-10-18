package Week_39;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9996_한국이_그리울_땐_서버에_접속하지 {

    static int N, LENGTH;
    static String PREFIX, SUFFIX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        
        String[] split = input.split("\\*");
        LENGTH = input.length()-1;
        PREFIX = split[0];
        SUFFIX = split[1];

        for(int i=0; i<N; i++){
            String target = br.readLine();

            if(target.length() < LENGTH){
                System.out.println("NE");
                continue;
            }

            String prefix = target.substring(0, PREFIX.length());
            String suffix = target.substring(target.length()-SUFFIX.length());

            if(PREFIX.equals(prefix) && SUFFIX.equals(suffix)){
                System.out.println("DA");
            }else{
                System.out.println("NE");
            }
        }
    }
}
