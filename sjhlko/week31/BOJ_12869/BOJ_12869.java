package sjhlko.week31.BOJ_12869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12869 {
    //https://www.acmicpc.net/problem/12869
    //뮤탈리스크
    static int N;
    static List<Integer> hp = new ArrayList<>();
    static int[][] type1 = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
    static int[][] type2 = {{3, 9}, {9, 3}};
    static int[][] type3 = {{9}};
    static int[][][] types = {type3, type2, type1};
    static int[][] type;


    static int solution() {
        Collections.sort(hp);
        HashSet<String> set = new HashSet<>();
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            list.get(0).add(0);
        }
        set.add(list.get(0).toString());
        int count = 1;
        while (true) {
            List<List<Integer>> nList = new ArrayList<>();
            for (List<Integer> l : list) {
                for (int i = 0; i < type.length; i++) {
                    List<Integer> next = new ArrayList<>();
                    boolean isEnd = true;
                    for (int j = 0; j < type[i].length; j++) {
                        int attack = l.get(j) + type[i][j];
                        next.add(attack);
                    }
                    Collections.sort(next);
                    for (int j = 0; j < type[i].length; j++) {
                        if (next.get(j) < hp.get(j)) {
                            isEnd = false;
                            break;
                        }
                    }
                    if (isEnd) return count;
                    if (set.add(next.toString())) nList.add(next);
                }
            }
            list = nList;
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        type = types[N - 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            hp.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(solution());
    }

}