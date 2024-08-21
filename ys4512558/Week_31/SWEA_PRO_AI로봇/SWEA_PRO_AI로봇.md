# 소스코드

```Java
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
```

# 소요시간

중간중간 쉬어서 몰라요잉

# 알고리즘

> 자료구조

# 풀이

# SWEA PRO AI로봇

1. PriorityQueue와 TreeSet중 무엇을 사용할까 고민했던 문제
2. max, min 정렬 조건을 각각 가지는 두개의 TreeSet을 사용하고 이를 동기화 하는 것이 포인트  
   동기화 : max에서 뺐을 때 임의접근으로 min에도 같은 것을 빼주어야한다.
3. 이를 위해서 TreeSet을 사용하여 remove를 통해 동기화 작업을 해준다.

### 객체 설계

1. Robot
   1. id : 로봇 ID
   2. iq : 로봇 지능 (손실된 지능을 저장)
   3. startTime : 로봇이 어떤 시간 (cTime)에 일에 투입되었는지 저장  
      이를 이용해서 returnJob시 cTime (작업 종료 시간) - startTime (작업 시작 시간)을 계산  
      이는 곧, 얼마동안 일했는지 == 얼마동안 지능 손실이 있었는지를 파악하기 위함
   4. TreeSet에서 같은 id인 로봇은 동일하다고 판단하기 위해 equals, hashCode 재정의
   5. min비교를 위해 Comparable 구현 및 오버라이딩
   6. max는 Comparator를 통해 구현

### init

1. lossIQs : 해당 로봇이 작업에 투입됨으로써 발생하는 지능 손실을 저장
2. works : 해당 Job에 어떤 로봇들이 일하는 중인지 저장하는 리스트
3. isWorking : 로봇의 ID를 인덱스로 하여 해당 로봇이 일하는 중인지 확인
   1. 0 : 일 X
   2. 1 ~ : wID를 저장
4. N만큼 초기 로봇 상태를 넣어준다.

### callJob()

1. max, min일때 각각 해당하는 TreeSet에서 첫번째 로봇을 꺼낸다.
2. 꺼낸 로봇은 동기화를 위해 양쪽 TreeSet에서 모두 삭제한다.  
   이를 PQ를 통해 구현한다면, lossIQs, isBroken, isWorking을 통해 꺼낸 로봇이  
   동기화된 상태인지 확인하고 아니라면 동기화된 로봇이 나올 때 까지 계속 꺼낸다. (버전관리)
3. 꺼낸 로봇의 ID를 모두 더해 반환

### returnJob()

1. 해당 wID에 해당하는 일에 참여한 로봇 List에서 하나씩 꺼낸다.
2. 꺼낸 Robot을 다시 TreeSet에 넣어주기 위해 작업동안  
   손실된 지능을 lossIQs에 갱신하고 이를 통해 다시 TreeSet에 삽입
3. 또한, isWorking을 0(작업 중 X)로 바꿔준다.
4. 마지막으로, 해당 작업은 종료되었으므로 works를 null로 초기화

### broken

1. 일하고있지않거나, 이미 고장난 상태이면 그냥 리턴
2. 이 외에는 작업중인 로봇이므로 isBroken = true
3. 작업중이므로 isWorking을 통해 작업 ID를 가져온다.
4. 가져온 ID를 통해 해당 작업에서 로봇을 제거하고 isWorking = 0으로 세팅

### repair

1. 고장이 아니면 그냥 리턴
2. 아니라면 고장 상태를 false로 변경
3. 지능이 0인 상태로 다시 추가해야하므로 lossIQs에 현재 시간을 넣는다.  
   0 == -cTime (즉 처음부터 지금까지 손실된 값이 시간과 동일하다는 의미)
4. 이를 통해 TreeSet에 다시 로봇 넣기

### check

1. 고장이면(isBroken = true) 0리턴
2. 일하는 중이면 (isWorking != 0) 해당 작업 번호의 음수 반환
3. 그것도 아니면 현재 시간 (cTime) - 총 손실 지능 (lossIQs) = 실제 지능 반환

---
