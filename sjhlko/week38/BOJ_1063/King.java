package sjhlko.week38.BOJ_1063;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class King {
    //https://www.acmicpc.net/problem/1063
    //킹
    static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static String[] command = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
    static Map<String, Integer> map = new HashMap<>();
    static char[] stone, king;

    static void init() {
        for (int i = 0; i < command.length; i++) {
            map.put(command[i], i);
        }
    }

    static boolean isValid(char i, char j) {
        return i >= '1' && i <= '8' && j >= 'A' && j <= 'H';
    }
    static void solution(String next) {
        int[] m = move[map.get(next)];
        char ni = (char) (king[0] + m[0]);
        char nj = (char) (king[1] + m[1]);
        if(!isValid(ni,nj)) return;
        if(ni==stone[0] && nj==stone[1]){
            if(!isValid((char) (ni+m[0]), (char) (nj+m[1]))) return;
            stone = new char[]{(char) (ni + m[0]), (char) (nj + m[1])};
        }
        king = new char[]{ni, nj};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String k = st.nextToken();
        king = new char[]{k.charAt(1), k.charAt(0)};
        String s = st.nextToken();
        stone = new char[]{s.charAt(1), s.charAt(0)};
        init();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            String next = bf.readLine();
            solution(next);
        }
        System.out.println(king[1] + "" + king[0]);
        System.out.println(stone[1] + "" + stone[0]);
    }
}
