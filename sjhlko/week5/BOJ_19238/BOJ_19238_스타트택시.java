package sjhlko.week5.BOJ_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19238_스타트택시 {
    //https://www.acmicpc.net/problem/19238
    //스타트 택시
    static int N, M, F;
    static int startI, startJ;
    static int[][] map;
    static boolean[][] isPassenger;
    static Map<String, int[]> passengerInfo = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static String makeKey(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(" ").append(j);
        return sb.toString();
    }

    // 유효하면 true, 다음으로 이동할 수 없는 조건이면 false 리턴
    static boolean bfs(boolean hasPassenger) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startI, startJ});
        boolean[][] visited = new boolean[N][N];
        visited[startI][startJ] = true;
        int[] target = passengerInfo.get(makeKey(startI, startJ));
        int nowF = 0, targetF = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            // 현재 단계까지 도달하면서 아무 이벤트도 발생하지 않았고 연료가 동났다면 종료
            if (nowF > F) return false;
            int size = queue.size();
            // size() 만큼 반복함으로써 같은 거리에 떨어져 있는 칸들을 모아서 탐색하고, 해당 반복이 끝나면 쓴 연료를 증가시킨다.
            while (size-- > 0) {
                int[] now = queue.poll();
                // 사람이 타있고, 목적지에 도달시 해당 목적지를 다음 탐색시작지점으로 지정후 연료를 깎아줌
                if (hasPassenger && target[0] == now[0] && target[1] == now[1]) {
                    F += nowF;
                    startI = now[0];
                    startJ = now[1];
                    return true;
                }
                // 사람을 찾아야하고, 현재 위치에 사람이 있으며, 사람을 처음 탐색하는것이거나, 이전에 탐색했던 사람보다 위 또는 왼쪽에 있을때 교환
                if (!hasPassenger && isPassenger[now[0]][now[1]] &&
                        (targetF == Integer.MAX_VALUE || startI > now[0] || (startI == now[0] && startJ > now[1]))
                ) {
                    targetF = nowF;
                    startI = now[0];
                    startJ = now[1];
                }
                for (int i = 0; i < 4; i++) {
                    int nextI = now[0] + dx[i];
                    int nextJ = now[1] + dy[i];
                    if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                    if (map[nextI][nextJ] == 1) continue;
                    if (visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.add(new int[]{nextI, nextJ});
                }
            }
            // 이 이후 반복은 연료를 1 더써야 갈 수 있는 칸들이므로 현시점에 임시로 태운 사람을 실제로 태운다.
            if (!hasPassenger && targetF != Integer.MAX_VALUE) {
                isPassenger[startI][startJ] = false;
                F -= targetF;
                return true;
            }
            nowF++;
        }
        return false;
    }

    static void solution() {
        int count = 0;
        while (true) {
            if (count == M) {
                System.out.println(F);
                return;
            }
            if (!bfs(false) || !bfs(true)) {
                System.out.println(-1);
                return;
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        isPassenger = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        startI = Integer.parseInt(st.nextToken()) - 1;
        startJ = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int si = Integer.parseInt(st.nextToken()) - 1;
            int sj = Integer.parseInt(st.nextToken()) - 1;
            int ei = Integer.parseInt(st.nextToken()) - 1;
            int ej = Integer.parseInt(st.nextToken()) - 1;
            isPassenger[si][sj] = true;
            passengerInfo.put(makeKey(si, sj), new int[]{ei, ej});
        }
        solution();
    }
}
