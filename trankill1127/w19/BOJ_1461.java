import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> plus = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        int input;
        for (int i=0; i<n; i++){
            input = Integer.parseInt(st.nextToken());
            if (input<0) minus.add(input);
            else plus.add(input);
        }

        Collections.sort(minus); //-39 -37 -29 -28 -6
        Collections.sort(plus); //2 11

        int tot=0;
        ArrayList<Integer> std = new ArrayList<>();
        for (int i=0; i<minus.size(); i+=m){
            std.add(Math.abs(minus.get(i))); //-39 -29 -6
            tot+=Math.abs(minus.get(i)*2);
        }
        for (int i=plus.size()-1; i>=0; i-=m){
            std.add(plus.get(i)); //11
            tot+=plus.get(i)*2;
        }
        Collections.sort(std);

        System.out.println(tot-std.get(std.size()-1));
    }
}
