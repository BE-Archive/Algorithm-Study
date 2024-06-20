import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {

    public static ArrayList<ArrayList<Integer>> graph;
    public static List<Integer> groupA;
    public static List<Integer> groupB;
    public static int n;
    public static int[] pop;
    public static int minDiff;
    public static int totPop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); //선거구 개수

        pop = new int[n+1]; //선거구의 인구
        totPop=0; //전체 선거구 인구 총합

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=n; i++) {
            pop[i]=Integer.parseInt(st.nextToken());
            totPop+=pop[i];
        }

        //그래프 생성
        graph = new ArrayList<>();
        for (int i=0; i<n+1; i++) graph.add(new ArrayList<>());
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cnt = Integer.parseInt(st.nextToken()); //지역구의 인접 지역구 개수
            for (int j=0; j<cnt; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                graph.get(i).add(neighbor);
            }
        }

        minDiff=1000;
        for (int i=1; i<=n/2; i++){
            groupA = new LinkedList<>();
            solution(0, i, 1);
        }
        if (minDiff==1000) System.out.println(-1);
        else System.out.println(minDiff);
    }

    public static void solution(int curLen, int targetLen, int start){
        if (curLen==targetLen){ //A 선거구를 구성하는 구역을 모두 골랐다면
            //B 지역구도 설정해준다.
            groupB = new LinkedList<>();
            for (int i=1; i<=n; i++){
                if (!groupA.contains(i)) groupB.add(i);
            }

            connectionCheck();
            return;
        }

        for (int i=start; i<=n; i++){
            groupA.add(i);
            solution(curLen+1, targetLen, i+1);
            groupA.remove((Integer) i);
        }
    }

    public static boolean connectionCheck() {//연결 여부 확인
        Queue<Integer> q = new LinkedList<>();

        int totA=0;
        q.add(groupA.remove(0));
        while (!q.isEmpty()){
            int now = q.poll();
            for (int next : graph.get(now)){
                if (groupA.contains(next)){ //두 지역을 잇는 간선이 존재하면서, 같은 지역구에 포함된다면
                    totA+=pop[next];
                    groupA.remove((Integer) next);
                    q.add(next);
                }
            }
        }
        if (!groupA.isEmpty()){
            return false;
        }

        int totB=0;
        q.add(groupB.remove(0));
        while (!q.isEmpty()){
            int now = q.poll();
            for (int next : graph.get(now)){
                if (groupB.contains(next)){
                    totB+=pop[next];
                    groupB.remove((Integer) next);
                    q.add(next);
                }
            }
        }
        if (!groupB.isEmpty()){
            return false;
        }

        minDiff = Math.min(Math.abs(totA-totB), minDiff);
        return true;
    }
}