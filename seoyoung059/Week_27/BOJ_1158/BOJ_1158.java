package Week_27.BOJ_1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> list=new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append('<');
        for (int i = (k-1)%n; list.size()>1; i = (i+k-1)%list.size()) {
            sb.append(list.remove(i)).append(", ");
        }
        sb.append(list.remove(0));
        sb.append('>');


        System.out.println(sb);
    }
}
