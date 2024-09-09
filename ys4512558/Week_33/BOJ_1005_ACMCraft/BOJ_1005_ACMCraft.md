# 소스코드

```Java
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1005 {
    static ArrayList<Integer>[] adjList;
    static int[] arr;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            adjList = new ArrayList[N + 1];
            times = new int[N + 1];
            Arrays.fill(times, -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= N; j++) {
                adjList[j] = new ArrayList<Integer>();
                if(j == 0) continue;
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adjList[Y].add(X); //Y를 만들기 위해서 X가 필요하다는 의미
            }
            int dest = Integer.parseInt(br.readLine());
            sb.append(solve(dest)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve(int dest) {
        if (adjList[dest].isEmpty()) {
            times[dest] = arr[dest];
            return times[dest];
        }
        if(times[dest] != -1) return times[dest];

        int max = -1;
        for (int i = 0; i < adjList[dest].size(); i++) {
            max = Math.max(max, solve(adjList[dest].get(i)));
        }
        return times[dest] = max + arr[dest];
    }
}

```

# 알고리즘

> DFS, DP

# 풀이

# BOJ 1005 ACM Craft

1. 해당 건물을 건설하기 위한 사전 조건 (다른 건물)을 만족해야하므로 이를 인접 리스트로 관리
2. DFS를 통해 최종적으로 건설해야하는 번호를 통해 탐색을 수행한다.
3. 인접 리스트를 통해 건설하고자 하는 건물의 이전에 건설되어야 하는 건물들을 먼저 건설한다.
4. DFS이므로 기저조건은 사전 조건이 없는 건물로 설정한다.
5. 해당 건물을 건설하기 이전에 건설되어있어야 하는 건물들은 기본적으로 동시에 건설될 수 있으므로  
   해당 건물의 사전 건설 건물들 중 가장 오래 걸리는 건물이 완료된 경우 현재 건물을 건설할 수 있다.
   > 3을 짓기 위해 1(2초, 조건없음), 2(4초, 조건없음) 이라면 1, 2는 동시에 건설되어 4초가 걸린다.
6. 위의 조건에 따라 가장 오래 걸리는 사전 건설 건물의 건설 완료 시간 + 현재 건물의 건설에 걸리는 시간을 더하여 반환한다.
7. 이때, 3번 건물이 1, 2를 조건으로 하며, 4번 건물이 2번 건물을 조건으로 하는 경우  
   2번 건물의 건설 시간을 확인하기 위해 반복적으로 접근하게 되면 시간초과가 발생하게 된다.
8. 이를 해결하기 위해 DP를 통해 해당 건물이 건설되는 시간을 기록하여 더 이상 재귀함수를 타지 않도록 최적화한다.

---
