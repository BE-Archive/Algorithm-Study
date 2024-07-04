package Week_19.BOJ_14621_나만안되는연애;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14621_나만안되는연애 {

    static int answer = 0;
    static int N,M;
    static boolean[] males;
    static List<Weight>[] weights;

    static class Weight{
        int index;
        int weight;
        Weight(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N M
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        // 초기화
        males = new boolean[N+1];
        weights = new List[N+1];
        for(int i=0; i<=N; i++) weights[i] = new LinkedList<>();

        // 입력| 남초 여초
        stk = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            String male = stk.nextToken();
            males[i] = male.equals("M")? true: false;
        }

        // 입력| weights: 간선 정보
        for(int i=0; i<M; i++){
            stk = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(stk.nextToken());
            int second = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            
            if(males[first] == males[second]) continue;

            weights[first].add(new Weight(second, weight));
            weights[second].add(new Weight(first, weight));
        }

        // 최소 스패닝 트리
        prim();

        // 출력
        System.out.println(answer);
    }

    static void prim(){
        Queue<Weight> pq = new PriorityQueue<>((w1, w2) -> w1.weight-w2.weight);
        boolean[] visits = new boolean[N+1];

        for(Weight next: weights[1]){
            pq.add(next);
        }

        visits[1] = true;
        

        while(!pq.isEmpty()){
            Weight now = pq.poll();

            if(visits[now.index]) continue;
            
            visits[now.index] = true;
            answer += now.weight;

            for(Weight next: weights[now.index]){
                if(visits[next.index]) continue;

                pq.add(next);
            }
        }

        for(int i=1; i<=N; i++){
            if(!visits[i]){
                answer = -1;
                break;
            }
        }
    }
}
