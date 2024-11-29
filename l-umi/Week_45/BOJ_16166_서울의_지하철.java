import java.util.*;
import java.io.*;

public class Main {
    static Map<Integer, List<Integer>> line = new HashMap<>();
    static Map<Integer, List<Integer>> station = new HashMap<>();
    static List<Integer> visited = new ArrayList<>();
    static int dest;

    static Integer bfs(int start, int cnt) {
        List<Integer> togo = new ArrayList<>();

        // start 역이 속한 각 호선에 대해
        for (int l : station.get(start)) {
            // 해당 호선의 모든 역 탐색
            for (int y : line.get(l)) {
                // 도착역이면 환승 횟수 반환
                if (y == dest) return cnt;

                // 방문하지 않은 역이면
                if (!visited.contains(y)) {
                    // 환승 가능한 역이면
                    if (station.get(y).size() > 1) {
                        togo.add(y);
                    }
                    visited.add(y);
                }
            }
        }

        for (int x : togo) {
            return bfs(x, cnt + 1);
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        // 각 호선의 역 정보 입력
        for (int lineNum = 1; lineNum <= n; lineNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int k = Integer.parseInt(st.nextToken());
            List<Integer> stations = new ArrayList<>();
            
            for (int i = 0; i < k; i++) {
                int stationNum = Integer.parseInt(st.nextToken());
                stations.add(stationNum);

                if (!station.containsKey(stationNum)) {
                    station.put(stationNum, new ArrayList<>());
                }
                station.get(stationNum).add(lineNum);
            
            }

            line.put(lineNum, stations);
        }

        dest = Integer.parseInt(br.readLine());

        //////////////////////////////////////////////
        visited.add(0);
        Integer cnt = bfs(0, 0);
        System.out.println(cnt);
    }
}
