import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Long, List<Edge>> adjMap;
    static int N;
    static long dest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjMap = new HashMap<>();

        //PQ를 통한 탐색
        //해당 번호(Long)가 갈 수 있는 엣지를 담는 맵을 통해 임의 접근
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            long pre = Long.parseLong(st.nextToken());
            for (int j = 0; j < cnt - 1; j++) {
                long cur = Long.parseLong(st.nextToken());
                Edge edge = new Edge(i, pre, cur);
                List<Edge> edgeList = adjMap.getOrDefault(pre, new ArrayList<>());
                edgeList.add(edge);
                adjMap.put(pre, edgeList);
                //양방향
                edge = new Edge(i, cur, pre);
                edgeList = adjMap.getOrDefault(cur, new ArrayList<>());
                edgeList.add(edge);
                adjMap.put(cur, edgeList);
                pre = cur;
            }
        }
        dest = Long.parseLong(br.readLine());
        System.out.println(dest == 0 ? 0 : dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        List<Edge> edgeList = adjMap.getOrDefault(0L, new ArrayList<>());
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            Info info = new Info(edge.num, edge.v, 0);
            info.set.add(info);
            pq.offer(info);
        }
        int res = -1;
        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (info.v == dest) {
                res = info.cnt;
                break;
            }

            List<Edge> edges = adjMap.getOrDefault(info.v, new ArrayList<>());
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);

                List<Edge> nexts = adjMap.getOrDefault(edge.u, new ArrayList<>());
                Info next = new Info(edge.num, edge.u, info.cnt + ((edge.num == info.num) ? 0 : 1));
                if (!info.set.add(next)) continue;
                next.set = new HashSet<>(info.set);
                pq.offer(next);
                for (int j = 0; j < nexts.size(); j++) {
                    Edge nextEdge = nexts.get(j);
                    next = new Info(nextEdge.num, nextEdge.v, next.cnt + ((nextEdge.num == next.num) ? 0 : 1));
                    Set<Info> set = new HashSet<>(info.set);
                    if (set.add(next)) {
                        next.set = set;
                        pq.offer(next);
                    }
                }
            }
        }
        return res;
    }
}

//이동 경로 정보 저장
class Info implements Comparable<Info> {
    int num;
    long v;
    int cnt;
    Set<Info> set = new HashSet<>(); //isv

    public Info(int num, long v, int cnt) {
        this.num = num;
        this.v = v;
        this.cnt = cnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return num == info.num && v == info.v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, v);
    }

    @Override
    public int compareTo(Info o) {
        return Integer.compare(this.cnt, o.cnt);
    }
}

class Edge {
    int num; //호선
    long v, u; //v -> u;

    public Edge(int num, long v, long u) {
        this.num = num;
        this.v = v;
        this.u = u;
    }
}