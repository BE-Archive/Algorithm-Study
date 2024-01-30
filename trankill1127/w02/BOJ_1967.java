import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1967 {

    public static class Edge{
        int x,w;

        Edge(int x, int w){
            this.x=x;
            this.w=w;
        }
    }

    static Edge[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); //노드 개수

        //그래프 관계를 저장할 배열
        int len = 2;
        while (len<n) len*=2;

        //System.out.println("graph size: "+len); 16

        graph = new Edge[len+1];
        graph[1]=new Edge(1,0);

        //System.out.println(graph[1].x+" "+graph[1].w);

        for (int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            //그래프 관계 저장
            int parentIdx = 1;
            for (int j=1; j<len+1; j++){
                if (graph[j]==null) continue;
                if (graph[j].x==x){
                    parentIdx=j;
                    break;
                }
            }
            int leftChildIdx = parentIdx*2;
            int rightChildIdx = leftChildIdx+1;
            if (graph[leftChildIdx]==null){ //좌측 자식이 없으면 좌측 저장
                graph[leftChildIdx]=new Edge(y,w);
            }
            else { //좌측 자식이 있으면 우측 저장
                graph[rightChildIdx]=new Edge(y,w);
            }
        }

        int maxSum=0;
        int sum=0;
        for (int i=1; i*2+1<len+1; i++){
            if (graph[i]!=null && graph[i*2]!=null && graph[i*2+1]!=null){
                sum=getDistance(i);

                //System.out.println("정점: "+graph[i].x + " 누적 거리: "+sum);

                maxSum = Math.max(sum, maxSum);
            }
        }

        System.out.println(maxSum);

    }

    public static int getDistance(int p){
        int sum=0;

        int leftP=p;
        int lChild = p*2;
        while (lChild<graph.length && graph[lChild]!=null){
            sum+=graph[lChild].w;
            leftP=lChild;
            lChild=leftP*2;
        }

        int rightP =p;
        int rChild = p*2+1;
        while (rChild<graph.length && graph[rChild]!=null){
            sum+=graph[rChild].w;;
            rightP=rChild;
            rChild=rChild*2+1;
        }

        return sum;
    }
}
