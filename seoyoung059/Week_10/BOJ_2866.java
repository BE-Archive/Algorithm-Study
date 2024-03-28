package Ing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2866 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        HashSet<String> set;

        char[][] arr = new char[r][c];
        String str;
        for (int i = 0; i < r; i++) {
            str = br.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();
        String[] tmp = new String[c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                sb.append(arr[j][i]);
            }
            tmp[i] = sb.toString();
            sb = new StringBuilder();
        }
        int answer = r-1;
        loop: for (int i = 1; i < r; i++) {
            set = new HashSet<>();
            for (int j = 0; j < c; j++) {
                if(set.contains(tmp[j].substring(i))){
                    answer = i-1;
                    break loop;
                }
                else{
                    set.add(tmp[j].substring(i));
                }
            }
        }

        System.out.println(answer);
    }
}
