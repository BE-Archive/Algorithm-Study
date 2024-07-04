import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static ArrayList<ArrayList<Integer>> adjList;
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int previous = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size - 1; j++) {
                int idx = Integer.parseInt(st.nextToken());
                arr[idx]++;
                adjList.get(previous).add(idx);
                previous = idx;
            }
        }
        bw.write(topologySort() ? sb.toString() : String.valueOf(0));
        bw.flush();
        bw.close();
    }
    public static boolean topologySort(){
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if(arr[i] == 0) queue.offer(i);
        }
        int len = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            len++;
            if(arr[vertex] == 0) sb.append(vertex).append(" ");

            ArrayList<Integer> list = adjList.get(vertex);
            for (int i = 0; i < list.size(); i++) {
                if(--arr[list.get(i)] == 0) queue.offer(list.get(i));
            }
        }
        if(len != N) return false;
        return true;
    }
}