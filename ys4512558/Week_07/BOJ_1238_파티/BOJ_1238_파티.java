import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[][] minDist;
    static Node[] adjList;
    static int N, M, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new Node[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, weight, adjList[from]);
        }

        minDist = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(minDist[i], INF);
        }
        for (int i = 1; i <= N; i++) {
            minDist[i][i] = 0;
            dijkstra(i);
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, minDist[i][X] + minDist[X][i]);
        }
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    public static void dijkstra(int vertex) {
        int min = 0, stopOver = 0;
        boolean[] isv = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            min = INF;
            stopOver = -1;
            for (int j = 1; j <= N; j++) {
                if(!isv[j] && min > minDist[vertex][j]){
                    min = minDist[vertex][j];
                    stopOver = j;
                }
            }
            if(stopOver == -1) break;
            isv[stopOver] = true;

            for (Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
                if (minDist[vertex][temp.vertex] > min + temp.weight) {
                    minDist[vertex][temp.vertex] = min + temp.weight;
                }
            }
        }
    }
}

class Node{
    int vertex, weight;
    Node next;

    public Node(int vertex, int weight, Node next) {
        this.vertex = vertex;
        this.weight = weight;
        this.next = next;
    }
}