import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940_쉬운최단거리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        String[] n_m = br.readLine().split(" ");
        int n = Integer.parseInt(n_m[0]);
        int m = Integer.parseInt(n_m[1]);

        Graph graph = new Graph(n,m);
        graph.twoThousandYearLater();
        graph.print();
    }

    static class Graph{
        class Node{
            int data;
            int r,c;
            boolean isVisit;
            Node(int data, int r, int c){
                this.r = r;
                this.c = c;
                this.data = data;
                this.isVisit = data==1? false: true;
            }
        }

        Node[][] nodes;
        int r,c;
        int n,m;

        Graph(int n, int m) throws IOException{
            this.n = n;
            this.m = m;
            nodes = new Node[n][m];

            setNode();
        }

        void setNode() throws IOException{
            for(int i=0; i<n; i++){
                StringTokenizer stk = new StringTokenizer(br.readLine());

                for(int j=0; j<m; j++){
                    nodes[i][j] = new Node(Integer.parseInt(stk.nextToken()), i, j);
                    if(nodes[i][j].data == 2){
                        this.r = i;
                        this.c = j;
                    }
                }
            }
        }

        void twoThousandYearLater(){
            Queue<Node> queue = new ArrayDeque<>();
            Node root = nodes[r][c];
            root.data = 0;

            queue.add(root);

            while(!queue.isEmpty()){
                Node node = queue.poll();

                int r = node.r;
                int c = node.c;
                int data = node.data;
                
                for(int i=0; i<4; i++){
                    if(isOut(r+dr[i], c+dc[i])) continue;
                    if(nodes[r+dr[i]][c+dc[i]].isVisit) continue;
            
                    Node cNode = nodes[r+dr[i]][c+dc[i]];
                    cNode.data = data+1;
                    cNode.isVisit = true;
                    queue.offer(cNode);
                }
            }
        }

        boolean isOut(int r, int c){
            return r<0 || c<0 || r>=this.n || c>=this.m;
        }

        void print(){
            StringBuilder result = new StringBuilder();

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!nodes[i][j].isVisit)
                        result.append(-1 + " ");
                    else
                        result.append(nodes[i][j].data + " ");
                }
                result.append("\n");
            }

            System.out.println(result);
        }
    }
}
