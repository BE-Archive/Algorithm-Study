package sjhlko.week23.BOJ_2515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2515_전시장 {
    //https://www.acmicpc.net/problem/2515
    //전시장
    static int N, S;
    static List<Info> infos = new ArrayList<>();

    static class Info implements Comparable<Info>{
        int h, c;

        public Info(int h, int c) {
            this.h = h;
            this.c = c;
        }

        @Override
        public int compareTo(Info o) {
            return h - o.h;
        }
    }

    static int solution() {
        Collections.sort(infos);
        //dp같은데..
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        S = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            infos.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}
