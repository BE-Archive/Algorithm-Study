package Week_01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] addX = {-1,0,1,0};
    static int[] addY = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        int index = 1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N==0) break;
            Graph graph = new Graph(N);
            graph.initNodes();

            bw.write(String.format("Problem %d: %d\n", index++, graph.bfs()));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Graph{
        class Node implements Comparable<Node>{
            int x;
            int y;
            int cost;
            boolean marked;

            Node(int x, int y, int cost){
                this.x = x;
                this.y = y;
                this.cost = cost;
                this.marked = false;
            }

            @Override
            public int compareTo(Node node) {
                return cost - node.cost;
            }
        }

        int N;
        Node[][] nodes;
        Graph(int N){
            this.N = N;
            nodes = new Node[N+2][N+2];
        }

        void initNodes() throws IOException{
            for(int i=1; i<=N; i++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                for(int j=1; j<=N; j++){
                    int cost = Integer.parseInt(stk.nextToken());
                    nodes[i][j] = new Node(i, j, cost);
                }
            }
        }

        int bfs(){
            Node root = nodes[1][1];
            root.marked = true;

            Queue<Node> queue = new PriorityQueue<>();
            queue.add(root);

            while(!queue.isEmpty()){
                Node node = queue.poll();
                int curX = node.x;
                int curY = node.y;
                int cost = node.cost;

                for(int i=0; i<4; i++){
                    Node cNode = nodes[curX+addX[i]][curY+addY[i]];

                    if(cNode != null && !cNode.marked){
                        cNode.marked = true;
                        cNode.cost += cost;
                        queue.add(cNode);
                    }
                }
            }

            return nodes[N][N].cost;
        }
    }
}
