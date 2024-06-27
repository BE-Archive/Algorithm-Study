package Week_23.BOJ_2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_2179 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }


        HashMap<String, Integer> hm = new HashMap<>();
        String sub, str;
        int ans1 = Integer.MAX_VALUE, ans2 = Integer.MAX_VALUE, length = -1, l;
        Integer tmp;
        for (int i = 0; i < n; i++) {
            str = arr[i];
            for (int j = 0; j < str.length() + 1; j++) {
                sub = str.substring(0, j);
                tmp = hm.get(sub);
                l = sub.length();
                if (tmp != null) {
                    if ((length < l || (length == l && ans1 > tmp)) && !arr[i].equals(arr[tmp])) {
                        ans1 = tmp;
                        ans2 = i;
                        length = l;
                    }
                } else {
                    hm.put(sub, i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(arr[ans1]).append("\n").append(arr[ans2]);
        System.out.println(sb);
    }
}
