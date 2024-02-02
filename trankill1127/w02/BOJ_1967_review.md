## 1차 시도

```java
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
```

이진 트리라고 생각하고 풀었는데 아니었다.

</br>
</br>

## 2차 시도

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1967 {

    static class Node {
        int p;
        int id;
        int w;

        public Node(int p, int id, int w){
            this.p=p;
            this.id=id;
            this.w=w;
        }
    }

    static ArrayList<ArrayList<Node>> graphDescribe;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        //인접 리스트 생성
        graphDescribe = new ArrayList<>();
        for (int i=0; i<n; i++){ //0 : 1번 노드의 인접 노드 리스트
            graphDescribe.add(new ArrayList<>());
        }
        for (int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graphDescribe.get(x-1).add(new Node(x,y,w));
        }

        for (int i=1; i<=n; i++){
            longest1=0; longest2=0;
            longest=0;
            getDistance(i);
            System.out.println(i+" "+longest1+" "+longest2);
            if (graphLongest<longest1+longest2){
                graphLongest=longest1+longest2;
            }
        }

        System.out.println(graphLongest);
    }

    static int graphLongest=0;
    static int longest1;
    static int longest2;
    static int longest;
    static boolean[] visited;

    static void getDistance(int nowId){
        if (graphDescribe.get(nowId-1).isEmpty()){ //더 이상 내려갈 수 있는 노드가 없을 때
            if (longest1<longest) {
                longest2=longest1;
                longest1=longest;
            }
            else if (longest2<longest){
                longest2=longest;
            }
            return;
        }

        for (int i=0; i<graphDescribe.get(nowId-1).size(); i++) {
            longest+=graphDescribe.get(nowId-1).get(i).w;
            getDistance(graphDescribe.get(nowId-1).get(i).id);
            longest-=graphDescribe.get(nowId-1).get(i).w;
        }
    }
} 
```


결과가 이상하게 나온 이유를 파악하기 위해 로그를 찍어본 결과, **1을 루트 노드로 삼았을 때의 최대 지름이 [1-3-5-9], [1-3-6-12]로 계산되고, 이 과정에서 1-3의 가중치가 2번이나 더해지는 게 문제임을 알 수 있었다.** 그런데 도무지 이 부분을 어떻게 해결해야 할지 모르겠어서 다른 스터디원의 코드를 참고해서 수정했다.

</br>
</br>

## 3차 시도

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1967 {

    static class Node {
        int id;
        int w;

        public Node(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static ArrayList<ArrayList<Node>> graphDescribe;
    static int graphLongest = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        //인접 리스트 생성
        graphDescribe = new ArrayList<>();
        for (int i = 0; i < n; i++) { //0 : 1번 노드의 인접 노드 리스트
            graphDescribe.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graphDescribe.get(x - 1).add(new Node(y, w));
        }

        getDistance(1);
        System.out.println(graphLongest);
    }

    static int getDistance(int nowId) {
        int longest1 = 0; //nowId를 중심으로 했을 때 가질 수 있는 가장 긴 경로의 길이 
        int longest2 = 0; //nowId를 중심으로 했을 때 가질 수 있는 두번째로 가장 긴 경로의 길이
        int longest; //nowId를 중심으로 했을 때 가질 수 있는 경로의 길이 

        if (!graphDescribe.get(nowId - 1).isEmpty()) { //자식 노드가 있을 때
            for (int i = 0; i < graphDescribe.get(nowId - 1).size(); i++) {
                longest = getDistance(graphDescribe.get(nowId - 1).get(i).id) + graphDescribe.get(nowId - 1).get(i).w;

                if (longest1 < longest) {
                    longest2 = longest1;
                    longest1 = longest;
                } else if (longest2 < longest) {
                    longest2 = longest;
                }
            }

            if (graphLongest<longest1+longest2) { //가장 긴 지름의 길이 업데이트 
                graphLongest=longest1+longest2;
            }
        }

        return longest1;
    }

}
```

노드를 검사하는 경우, **이 노드와 부모 노드를 잇는 간선의 가중치**까지 고려해서 가장 긴 최장거리와 두번째로 긴 최장거리를 판별한다. 그리고 이 최장거리를 반환해서 그 부모 노드가 이 값을 참고하여 최장 거리를 다시 계산할 수 있도록 getDistance를 고쳤다.