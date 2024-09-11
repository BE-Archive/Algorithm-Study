public class SWEA_병사_관리 {

	public class Soldier {
		int id;
		int version;
		Soldier next; //c++과 달리 프인터 없이 next를 관리하는군...

		public Soldier(int id, int version, Soldier next) {
			this.id = id;
			this.version = version;
			this.next = next;
		}
	}

	public class Team {
		Soldier[] head = new Soldier[6];
		Soldier[] tail = new Soldier[6];
	}

	public Soldier[] soldiers = new Soldier[200005];
	public Team[] teams = new Team[6];
	public int[] versions = new int[100005];
	public int[] toTeam = new int[100005];
	public int soldierCnt=0;

	public Soldier getNewSoldier(int mID, Soldier next){
		Soldier newSol = soldiers[soldierCnt];
		soldierCnt++;
		newSol.id=mID;
		versions[mID]++;
		newSol.version=versions[mID];
		newSol.next=next;
		return newSol;
	}

	public void init() {
		soldierCnt=0;

		for (int i=0; i<200005; i++){
			soldiers[i] = new Soldier(0,0,null);
		}

		for (int i=1; i<6; i++){
			teams[i] = new Team();
			for (int j=1; j<6; j++){
				teams[i].head[j] = new Soldier(0,0,null);
				teams[i].tail[j] = new Soldier(0,0,null);
			}
		}

		for (int i=0; i<100005; i++){
			versions[i]=0;
			toTeam[i]=0;
		}
	}

	public void hire(int mID, int mTeam, int mScore) {  // O(1)
		Soldier newSoldier = getNewSoldier(mID, null);
		teams[mTeam].tail[mScore].next=newSoldier;
		teams[mTeam].tail[mScore]=newSoldier;
		toTeam[mID]=mTeam;
	}

	public void fire(int mID) {  // O(1)
		versions[mID]=-1;
	}

	public void updateSoldier(int mID, int mScore) {  // O(1)
		hire(mID, toTeam[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore) {  // O(1)
		if (mChangeScore<0){
			for (int i=1; i<=5; i++){
				int changedScore = (i+mChangeScore)<=1 ? 1 : i+mChangeScore;

				if (teams[mTeam].head[i].next == null) continue;

				teams[mTeam].tail[changedScore].next=teams[mTeam].head[i].next;
				teams[mTeam].tail[changedScore] = teams[mTeam].head[i];
				teams[mTeam].head[i].next=null;
				teams[mTeam].tail[i]=teams[mTeam].head[i];
			}
		}
		else if (mChangeScore>0){
			for (int i=5; i>=1; i--){
				int changedScore = (i+mChangeScore)>=5 ? 5 : i+mChangeScore;

				if (teams[mTeam].head[i].next == null) continue;

				teams[mTeam].tail[changedScore].next=teams[mTeam].head[i].next;
				teams[mTeam].tail[changedScore] = teams[mTeam].head[i];
				teams[mTeam].head[i].next=null;
				teams[mTeam].tail[i]=teams[mTeam].head[i];
			}
		}
	}

	public int bestSoldier(int mTeam) {  // O(N)
		int bestScoreID=0;

		for (int i=5; i>=1; i--){
			Soldier cur = teams[mTeam].head[i].next;
			while (cur!=null){
				if (cur.version==versions[cur.id]){
					bestScoreID= cur.id>bestScoreID ? cur.id : bestScoreID;
				}

				cur = cur.next;
			}
			if (bestScoreID!=0) break;
		}

		return bestScoreID;
	}

}

/*

[내 풀이]================================================
- 병사 id를 입력받았을 때 점수를 바로 알 수 있어야 한다.
- 팀 id를 입력받았을 때 속한 병사를 바로 알 수 있어야 한다.

Map<Integer, List<Integer>>로 팀에 속한 병사를 관리한다.
그리고 이걸로 각각의 병사에 접근해서 점수를 업데이트하면 되지 않을까?

hire O(1)
fire 팀에 속하는 모든 멤버를 순회해서 삭제할 병사를 찾아야 한다. O(n)
updateSoldier 팀에 속하는 모든 병사를 순회해야 한다. O(n)
updateTeam 팀에 속하는 모든 병사를 순회해야 한다. O(n)
bestSoldier Comparator를 써서 List를 정렬한다. O(n logn)

[답]================================================
hire O(1)
fire 팀에 속하는 모든 멤버를 순회해서 삭제할 병사를 찾아야 한다. O(1)
updateSoldier 팀에 속하는 모든 병사를 순회해야 한다. O(1)
updateTeam 팀에 속하는 모든 병사를 순회해야 한다. O(1)
bestSoldier Comparator를 써서 List를 정렬한다. O(n)

=====fire
B형은 메모리를 희생하고 시간을 줄이는 게 정해인 경우가 많은 것 같다.
List에서 병사를 일일히 삭제하던 나와 달리 정답에서는 이를 삭제하지 않는다.
대신 version 배열을 추가하고 soldier에서도 version 값을 속성으로 추가한다.
추후 팀 내 병사를 순회할 때 배열과 병사의 속성을 비교하여 동일한 경우에만 유효한 병사로 판정한다.
이렇게 하면 삭제하지 않고도 유효하지 않은 병사를 표시할 수 있다.

=====updateSoldier
version 배열이 있어서 최신 병사가 무엇인지 알 수 있다.
따라서, 굳이 기존의 병사를 찾아서 점수를 변경할 필요가 없이 hire를 재활용하면 된다.

=====updateTeam
팀 내에서도 점수 단위로 한번 더 나눠서 관리한다.
그리고 LinkedList를 직접 구현한다.
이렇게 하면 점수 단위로 한번에 이동시킬 수 있기 때문에 성능 개선이 가능하다.
기본 LinkedList의 경우에는 addAll을 수행하면 O(n)이지만 직접 구현 시 O(1)로 가능하기 때문에
꼭 LinkedList를 직접 구현해야 한다.

=====bestSoldier
매우 적게 호출되기 때문에 O(n)이어도 괜찮다.

 */