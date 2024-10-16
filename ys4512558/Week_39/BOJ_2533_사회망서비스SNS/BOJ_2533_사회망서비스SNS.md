# 소스코드

```Java
import java.io.*;
import java.util.*;

public class BOJ2533 {
    static int[][] dp;
    static Node[] adjList;
    static boolean[] isv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //N번 정점이 얼리어답터인지 여부에 따라 필요한 개수
        dp= new int[N + 1][2];
        //인접 리스트
        adjList = new Node[N + 1];
        //방문처리
        isv = new boolean[N + 1];
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            adjList[v] = new Node(u, adjList[v]);
            adjList[u] = new Node(v, adjList[u]);

        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int vertex) { //0:얼리어답터 X, 1:얼리어답터
        isv[vertex] = true;
        dp[vertex][0] = 0;
        dp[vertex][1] = 1;
        for(Node n = adjList[vertex]; n != null; n = n.next){
            if(isv[n.vertex]) continue;
            dfs(n.vertex);
            //현재 노드가 얼리어답터 X -> 자식은 무조건 얼리어답터
            dp[vertex][0] += dp[n.vertex][1];
            //현재 노드가 얼리어답터 -> 자식은 얼리어답터 or x
            dp[vertex][1] += Math.min(dp[n.vertex][0], dp[n.vertex][1]);
        }
    }
}

class Node {
    int vertex;
    Node next;

    public Node(int vertex, Node next) {
        this.vertex = vertex;
        this.next = next;
    }
}

```

# 소요시간

1시간 30분

# 알고리즘

> DP

# 풀이

# BOJ 2533 사회망서비스(SNS)

1. N번 정점이 얼리어답터가 아니라면 `자식은 무조건 얼리어답터`
2. N번 정점이 얼리어답터이면 자식은 `얼리어답터이거나 얼리어답터가 아닐 수 있다`.
3. 따라서, 현재 정점이 `얼리어답터가 아니라면` `자식이 얼리어답터`인 경우에 필요한 얼리어답터 수를 dp에 저장
4. 현재 정점이 얼리어답터라면 자식이 `얼리어답터`인 경우와 `얼리어답터가 아닌` 경우에 필요한 수 중 최소를 저장
5. 출발 정점을 임의로 하나 선택하여 이를 수행하여 시작점이 얼리어답터인 경우와 아닌 경우 중 최소를 출력한다.

---
