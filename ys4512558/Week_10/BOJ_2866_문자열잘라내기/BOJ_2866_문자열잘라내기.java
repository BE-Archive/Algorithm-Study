import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] chars = new char[M][N];
        StringBuilder[] stringBuilders = new StringBuilder[M];
        for (int i = 0; i < M; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                stringBuilders[j].append(input.charAt(j));
            }
        }

        int cnt = 0;
        Loop:for (int i = 1; i < N; i++) {
            Set<String> set = new TreeSet<>();
            for (int j = 0; j < M; j++) {
                stringBuilders[j] = stringBuilders[j].delete(0, 1);
                if(!set.add(stringBuilders[j].toString())) break Loop;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}