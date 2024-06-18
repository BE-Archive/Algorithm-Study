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
        for (int i=0; i<n; i++) {
            int input = Integer.parseInt(st.nextToken());
            if (input<0) minus.add(-1*input);
            else plus.add(input);
        }

        Collections.sort(minus);
        Collections.sort(plus);

        int minusIdx=minus.size()-1;
        int plusIdx=plus.size()-1;
        int steps=0;
        if (minusIdx>=0 && plusIdx>=0){
            if (minus.get(minusIdx)>plus.get(plusIdx)) {
                steps+=minus.get(minusIdx);
                minusIdx-=m;
            }
            else {
                steps+=plus.get(plusIdx);
                plusIdx-=m;
            }
        }
        else {
            if (plusIdx>=0) {
                steps+=plus.get(plusIdx);
                plusIdx-=m;
            }
            else {
                steps+=minus.get(minusIdx);
                minusIdx-=m;
            }
        }

        while (minusIdx>=0){
            steps+=minus.get(minusIdx)*2;
            minusIdx-=m;
        }
        while (plusIdx>=0){
            steps+=plus.get(plusIdx)*2;
            plusIdx-=m;
        }

        System.out.println(steps);
    }
}
