import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1865 {

    public static class Node implements Comparable<Node>{
        int e;
        int t;
        public Node(int e, int t) {
            super();
            this.e = e;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t, o.t);
        }
    }

    public static ArrayList<ArrayList<Node>> map = null;
    public static ArrayList<ArrayList<Node>> wormhole = null;
    public static int[] visited = null;
    public static boolean isPossible;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while (tc>0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken()); //지점

            map = new ArrayList<>();
            wormhole = new ArrayList<>();
            for (int j=0; j<n+1; j++) {
                map.add(new ArrayList<>());
                wormhole.add(new ArrayList<>());
            }

            int m = Integer.parseInt(st.nextToken()); //도로
            int w = Integer.parseInt(st.nextToken()); //웜홀
            for (int j=0; j<m; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int t=Integer.parseInt(st.nextToken());
                map.get(s).add(new Node(e,t));
                map.get(e).add(new Node(s,t));
            }
            for (int j=0; j<w; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s=Integer.parseInt(st.nextToken());
                int e=Integer.parseInt(st.nextToken());
                int t=Integer.parseInt(st.nextToken());
                wormhole.get(s).add(new Node(e,-t));
            }

            isPossible=false;
            visited = new int[n+1];
            for (int i=1; i<=n; i++) {
                if (isPossible) break;
                if (!wormhole.get(i).isEmpty()) {
                    DFS(i);
                }
            }
            if (isPossible) sb.append("YES\n");
            else sb.append("NO\n");

            tc--;
        }

        System.out.println(sb.toString());
    }

    public static void DFS(int start) {

        for (int wh=0; wh<wormhole.get(start).size(); wh++){

            Stack<Integer> stack = new Stack<>();
            stack.add(wormhole.get(start).get(wh).e);
            visited[stack.peek()]=1;
            int sum=wormhole.get(start).get(wh).t;

            while (!stack.isEmpty()) {
                int now = stack.pop();
                for (int i=0; i<map.get(now).size(); i++) {
                    int next=map.get(now).get(i).e; //2 3
                    if (visited[next]==0) {
                        visited[next]=1;
                        stack.add(now);
                        stack.add(next);
                        sum+=map.get(now).get(i).t; //now~next

                        //System.out.println("sum updated: "+sum);

                        break;
                    }
                }
            }

            if (sum<0) isPossible=true;
        }
    }

}

/*
 * 웜홀은 시간 -
 * 다른 건 시간 +
 *
 * 한 지점에서 출발해서 원래 위치로 돌아왓을 때 출발한 때보다 시간이 이전일 수 잇을까?
 * 가능한지 아닌지 구하라
 *
 * 자기로 돌아오는 애들이 여럿일 수가 잇어...
 * 긍까 n개인데 섬이 여러개일 수 있는 거잖아...
 *
 * dfs 쓰고
 * visited로 방문한 애들 체크하고
 * 없는 애들 출발점으로 다시 ㄱㄱ하는 거 반복
 * visited 값으로 그룹 분리!! 할 거임!!
 * 나중 set에 저장값들 다 넣어서 몇 그룹 있는지 확인한 후에
 * 그걸로 timesum[]배열 만들고
 * 한번에 싸악 돌면서 timeSum에 값 저장하고
 * 이 중에서 음수 인거 있으면
 * YES 출력
 * 만약 없으면 NO 출력
 *
 *
 *
 * 8퍼 틀이라니 쏘 킹받네요ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
 * 아 걍 웜홀 따로 관리해서
 * 웜홀이 있는 애들은 무조건 첫빠따로 들어가게 하고
 * 아닌 애들은 뭐 차피 고려할 필요도 없으니까 ㅇㅇ
 *
 */