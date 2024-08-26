import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeSet;

class UserSolution
{
	/**
	 * 로봇 투입 방식
	 * 1. 높은 지능 우선 : 지능 높은 것 우선, 고유 번호 낮은 것 우선
	 * 2. 낮은 지능 우선 : 지능 낮은 것 우선, 고유 번호 낮은 것 우선
	 * @param N : 50_000
	 */
	//지능 낮은 순으로 정렬된 트리셋
	static TreeSet<Robot> maxTreeSet;
	static TreeSet<Robot> minTreeSet;
	
	//0부터 시작 일하느라 트레이닝을 못하고 있으면 - cTime 만큼 빼서 일하는 중에 지능 손실을 체크
	static int[] lossIQs;
	//해당 인덱스 (ID)에 해당하는 로봇이 일하고 있는지 0 일 X, 그 외 어떤 wID의 일을 하고 있는지
	static int[] isWorking;
	static boolean[] isBroken;
	//해당 wID에 투입된 로봇들
	static List<Robot>[] works;
	
	public void init(int N)
	{
		lossIQs = new int[N + 1];
		isWorking = new int[N + 1];
		works = new List[50001];
		maxTreeSet = new TreeSet<>((o1, o2) -> {
			if(o1.iq == o2.iq) return Integer.compare(o1.id, o2.id);
			return Integer.compare(o2.iq, o1.iq);
		});
		minTreeSet = new TreeSet<>();
		isBroken = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			maxTreeSet.add(new Robot(i, 0, 0));
			minTreeSet.add(new Robot(i, 0, 0));
		}
	}

	/**
	 * 작업에 로봇을 투입
	 * @param cTime : 현재 시간
	 * @param wID : 작업 ID
	 * @param mNum : 투입 로봇 수
	 * @param mOpt 0 : 높은 지능 우선, 1 : 낮은 지능 우선
	 * @return
	 */
	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
		works[wID] = new ArrayList<>();
		
		int sum = 0;
		if(mOpt == 0) {
			while(mNum != 0) {
				Robot robot = maxTreeSet.first();
				maxTreeSet.remove(robot);
				minTreeSet.remove(robot);
				
				//해당 일에 로봇 배정
				works[wID].add(new Robot(robot.id, robot.iq, cTime));
				//해당 로봇의 인덱스에 현재 일의 ID를 할당
				isWorking[robot.id] = wID;
				sum += robot.id;
				mNum--;
			}

		} else {
			while(mNum != 0) {
				Robot robot = minTreeSet.first();
				maxTreeSet.remove(robot);
				minTreeSet.remove(robot);
				works[wID].add(new Robot(robot.id, robot.iq, cTime));
				isWorking[robot.id] = wID;
				sum += robot.id;
				mNum--;
			}

		}
		
		return sum;
	}

	/**
	 * 투입된 로봇 센터로 복귀
	 * @param cTime : 현재 시간
	 * @param wID : 작업 ID 
	 */
	public void returnJob(int cTime, int wID)
	{
		for(int i = 0; i < works[wID].size(); i++) {
			Robot robot = works[wID].get(i);
			lossIQs[robot.id] -= (cTime - robot.startTime);
			Robot newRobot = new Robot(robot.id, lossIQs[robot.id], 0);
			maxTreeSet.add(newRobot);
			minTreeSet.add(newRobot);
			isWorking[robot.id] = 0;
		}
		works[wID] = null;
	}

	/**
	 * 로봇 ID에 해당하는 로봇이 고장
	 * @param cTime
	 * @param rID
	 */
	public void broken(int cTime, int rID)
	{
		if(isWorking[rID] == 0 || isBroken[rID]) return;
		isBroken[rID] = true;
		//작업중인 로봇이 고장난다.
		Robot robot = new Robot(rID, 0, 0);
		int wID = isWorking[rID];
		//해당 일에서 제외
		works[wID].remove(robot);
		isWorking[rID] = 0;
	}

	/**
	 * 로봇 수리가 완료됨
	 * 지능이 0이 된다.
	 * @param cTime
	 * @param rID
	 */
	public void repair(int cTime, int rID)
	{
		if(!isBroken[rID]) return;
		isBroken[rID] = false;
		//지능이 0이므로 실제 지능은 현재 시간만큼 빼준 값이다. (지능의 loss값을 체크하기 때문)
		Robot robot = new Robot(rID, -cTime, 0);
		
		maxTreeSet.add(robot);
		minTreeSet.add(robot);
		lossIQs[robot.id] = -cTime;
	}

	/**
	 * cTime에 로봇의 상태 확인 
	 * 고장 : 0
	 * 작업에 투입되어있으면, 투입된 작업의 ID * -1
	 * 그 외 로봇의 지능 
	 * @param cTime
	 * @param rID
	 * @return
	 */
	public int check(int cTime, int rID)
	{
		if(isBroken[rID]) {
			return 0;
		} else if(isWorking[rID] != 0) {
			return -isWorking[rID];
		} else {
			return cTime + lossIQs[rID];
		}
	}
	
}

class Robot implements Comparable<Robot> {
	int id, iq, startTime;

	public Robot(int id, int iq, int startTime) {
		super();
		this.id = id;
		this.iq = iq;
		this.startTime = startTime;
	}

	@Override
	public int compareTo(Robot o) {
		if(this.iq == o.iq) return Integer.compare(this.id, o.id);
		return Integer.compare(this.iq, o.iq);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Robot [id=" + id + ", iq=" + iq + ", startTime=" + startTime + "]";
	}
	
	
}