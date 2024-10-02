package BOJ2251;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ2251 {

    static TreeSet<Integer> resultSet;
    static int[] start;
    static boolean[][][] isv;
    static final int MAX = 200;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        resultSet = new TreeSet<>();
        start = new int[4];
        isv = new boolean[201][201][201];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 3; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : resultSet) {
            sb.append(i).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, start[3]});
        isv[0][0][start[3]] = true;
        resultSet.add(start[3]);

        while (!queue.isEmpty()) {
            int[] water = queue.poll();

            //1 -> 2, 1 -> 3, 2 -> 1, 2 -> 3, 3 -> 1, 3 -> 2
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if(i == j) continue;
                    int[] result = moveWater(i, j, water);

                    if (isv[result[1]][result[2]][result[3]]) continue;
                    isv[result[1]][result[2]][result[3]] = true;
                    if (result[1] == 0) {
                        resultSet.add(result[3]);
                    }
                    queue.offer(result);
                }
            }
        }
    }

    private static int[] moveWater(int from, int to, int[] origin) {
        //from의 최대 이동 가능 물 양
        int capaFrom = origin[from];
        //to가 받을 수 있는 최대 물 양
        int capaTo = start[to] - origin[to];
        //둘 중 더 작은 양만큼 이동해야함.
        int min = Math.min(capaFrom, capaTo);
        //원래 용량에서 빼기
        int resultFrom = origin[from] - min;
        //원래 용량에 더하기
        int resultTo = origin[to] + min;
        //from, to 제외 나머지 인덱스 = 1 + 2 + 3 - from - to;
        int idx = 6 - from - to;
        int[] result = new int[4];
        result[from] = resultFrom;
        result[to] = resultTo;
        result[idx] = origin[idx];
        return result;
    }

}
