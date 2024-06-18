import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        int[] freq = new int[26];
        boolean flag = true;
        for(int i = 0; i < S.length; i++) {
        	freq[S[i] - 'A']++;
        	flag &= (S[i] == S[S.length - 1 - i]);
        }
        if(freq[S[0] - 'A'] == S.length) System.out.println(-1);
        else if(flag) System.out.println(S.length - 1);
        else System.out.println(S.length);
    }
}