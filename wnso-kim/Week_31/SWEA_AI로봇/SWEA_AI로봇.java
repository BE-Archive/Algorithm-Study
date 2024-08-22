import java.util.*;

class UserSolution
{

    static int N;
    static int[][] robots;  // {상태, 지능, 트레이닝 시작 시간-지능, 작업ID} // 상태(0:고장, 1:작업중, 2:트레이닝중)
    static int[][] works;   // {작업ID, {로봇들}}
    static Queue<int[]> minHeap; // 지능 낮은 순   // {idx, 트레이닝 시작 시간-지능}
    static Queue<int[]> maxHeap; // 지능 높은 순   // {idx, 트레이닝 시작 시간-지능}

    public void init(int N)
    {
        this.N = N;
        this.robots = new int[N+1][];
        this.works = new int[50001][];
        this.minHeap = new PriorityQueue<>((arr1,arr2) -> (
                arr1[1]!=arr2[1]? arr2[1]-arr1[1]: arr1[0]-arr2[0]
        ));
        this.maxHeap = new PriorityQueue<>((arr1,arr2) -> (
                arr1[1]!=arr2[1]? arr1[1]-arr2[1]: arr1[0]-arr2[0]
        ));

        for(int i=1; i<=N; i++){
            robots[i] = new int[] {2,0,0,0};
            minHeap.add(new int[] {i,0});
            maxHeap.add(new int[] {i,0});
        }
    }

    public int callJob(int cTime, int wID, int mNum, int mOpt)
    {
        works[wID] = new int[mNum];

        int sum = 0;
        int index = 0;
        while(index<mNum){
            int[] robot = mOpt==0? maxHeap.poll(): minHeap.poll();
            int rID = robot[0];
            int rTime = robot[1];

            if(robots[rID][0] != 2) continue;    // 고장 or 작업중
            if(robots[rID][2] != rTime) continue;

            works[wID][index] = rID;
            robots[rID][0] = 1;
            robots[rID][1] = cTime-rTime;
            robots[rID][3] = wID;

            sum += rID;
            index++;
        }

        return sum;
    }

    public void returnJob(int cTime, int wID)
    {
        for(int rID: works[wID]) {
            if (robots[rID][0] != 1) continue; // 작업중이 아닌 경우
            if (robots[rID][3] != wID) continue; // 작업 번호가 다른 경우

            robots[rID][0] = 2;
            robots[rID][2] = cTime - robots[rID][1];

            minHeap.add(new int[]{rID, robots[rID][2]});
            maxHeap.add(new int[]{rID, robots[rID][2]});
        }
    }

    public void broken(int cTime, int rID)
    {
        if(robots[rID][0] != 1) return; // 작업중이 아닌 경우

        robots[rID][0] = 0;
    }

    public void repair(int cTime, int rID)
    {
        if(robots[rID][0] != 0) return; // 고장이 아닌 경우

        robots[rID][0] = 2;
        robots[rID][1] = 0;
        robots[rID][2] = cTime;

        minHeap.add(new int[] {rID, cTime});
        maxHeap.add(new int[] {rID, cTime});
    }

    public int check(int cTime, int rID)
    {
        if(robots[rID][0] == 0) return 0;
        else if(robots[rID][0] == 1) return -robots[rID][3];
        else return cTime-robots[rID][2];
    }
}