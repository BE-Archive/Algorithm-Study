package Week_15.BOJ_1516_게임개발;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1516_게임개발 {

    static int N;
    static Building[] buildings;
    static int[] times;

    static class Building{
        int number;         // 빌딩 번호
        int time;           // 빌딩 건설에 남은 시간
        int rest;           // 먼저 지어야 하는 빌딩 개수
        List<Building> next;// 다음에 지을 수 있는 빌딩들
        public Building(int number){
            this.number = number;
            rest = 0;
            next = new LinkedList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N
        N = Integer.parseInt(br.readLine());

        // 초기화
        times = new int[N];
        buildings = new Building[N];
        for(int i=0; i<N; i++) buildings[i] =  new Building(i);

        // 입력| 빌딩 정보
        for(int i=0; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            buildings[i].time = Integer.parseInt(stk.nextToken());

            while(stk.hasMoreTokens()){
                int state = Integer.parseInt(stk.nextToken());
                if(state == -1) break;

                buildings[i].rest++;
                buildings[state-1].next.add(buildings[i]);
            }
        }

        // 위상 정렬
        twoThousandYearsLater();

        // 결과 확인
        StringBuilder result = new StringBuilder();
        for(int time: times) result.append(time).append("\n");

        // 결과 출력
        System.out.println(result);
    }

    static void twoThousandYearsLater(){
        Queue<Building> queue = new ArrayDeque<>();
        int minTime = Integer.MAX_VALUE;    // 큐에 들어간 빌딩 중, 가장 먼저 만들 수 있는 빌딩의 소요시간
        int time = 0;                       // 시간의 흐름 틱택톡.. 틱택톡..
        
        // 준비 없이 만들 수 있는 빌딩들 큐에 넣기
        for(Building building: buildings){
            if(building.rest == 0){
                queue.add(building);
                minTime = Math.min(minTime, building.time);
            }
        }

        // 이전 빌딩을 모두 건설 한 경우만 큐에 넣으며 확인
        while (!queue.isEmpty()) {
            time += minTime;
            int size = queue.size();
            int nextMinTime = Integer.MAX_VALUE;

            while(size-- > 0){
                Building building = queue.poll();
                building.time -= minTime;

                // 빌딩 건설이 끝난 경우
                if(building.time <= 0){
                    times[building.number] = time+building.time;

                    // 다음 빌딩 목록 중, 건설 가능한 빌딩 파악
                    for(Building nextB: building.next){
                        if(--nextB.rest > 0) continue;

                        queue.add(nextB);
                        nextMinTime = Math.min(nextB.time, nextMinTime);
                    }
                }else{ // 빌딩 건설에 시간이 더 필요한 경우
                    nextMinTime = Math.min(building.time, nextMinTime);
                    queue.add(building);
                }
            }

            minTime = nextMinTime;
        }
    }
}