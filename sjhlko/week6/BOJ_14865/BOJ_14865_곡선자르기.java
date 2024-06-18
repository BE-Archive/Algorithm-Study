package sjhlko.week6.BOJ_14865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14865_곡선자르기 {
    //https://www.acmicpc.net/problem/14865
    //곡선 자르기
    static int N;
    static List<Integer> zeros = new ArrayList<>();
    static Map<Integer, Integer> mapZero = new HashMap<>();
    static int[] ans = new int[2];

    static class Info {
        int coordinate;
        int count;

        public Info(int coordinate, int count) {
            this.coordinate = coordinate;
            this.count = count;
        }
    }

    static void solution() {
        Collections.sort(zeros);
        Stack<Info> stack = new Stack<>();
        for (int i = 0; i < zeros.size(); i++) {
            int coordinate = zeros.get(i);
            if (stack.isEmpty()) {
                ans[0]++;
                stack.push(new Info(coordinate, 0));
                continue;
            }
            if (mapZero.get(stack.peek().coordinate).equals(coordinate)) {
                if (stack.peek().count == 0) ans[1]++;
                stack.pop();
                continue;
            }
            stack.peek().count++;
            stack.push(new Info(coordinate, 0));
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] first = new int[]{a, b};
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if ((b <= 0 && d >= 0) || (b >= 0 && d <= 0)) {
                zeros.add(a);
                if ((b <= 0 && d >= 0) || stack.isEmpty()) stack.push(a);
                else {
                    int index = stack.pop();
                    mapZero.put(a, index);
                    mapZero.put(index, a);
                }
            }
            if (i == N - 2) {
                if ((first[1] <= 0 && d >= 0) || (first[1] >= 0 && d <= 0)) {
                    zeros.add(c);
                    if (!stack.isEmpty()) {
                        int index = stack.pop();
                        mapZero.put(c, index);
                        mapZero.put(index, c);
                    }
                }
            }
            a = c;
            b = d;
        }
        if (!stack.isEmpty()) {
            int index1 = stack.pop();
            int index2 = stack.pop();
            mapZero.put(index1, index2);
            mapZero.put(index2, index1);
        }
        solution();
        System.out.println(ans[0] + " " + ans[1]);
    }
}
