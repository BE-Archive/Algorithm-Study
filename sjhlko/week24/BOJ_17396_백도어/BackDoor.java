package sjhlko.week24.BOJ_17396_백도어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BackDoor {
    //https://www.acmicpc.net/problem/17396
    //백도어
    static int N, M;
    static boolean[] visible;
    static List<List<Info>> infos = new ArrayList<>();
    static class Info {
        int to, cost;

        public Info(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int solution() {
        //다익스트라..?
        return -1;
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visible = new boolean[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            visible[i] = st.nextToken().equals("1");
            infos.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            infos.get(a).add(new Info(b, c));
            infos.get(b).add(new Info(a, c));
        }
    }
}
