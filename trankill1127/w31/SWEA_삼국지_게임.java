package trankill1127.w31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SWEA_삼국지_게임 {

	class Monarch{
		int posX;
		int posY;
		int solCnt;

		public Monarch(int posX, int posY, int solCnt) {
			this.posX = posX;
			this.posY = posY;
			this.solCnt = solCnt;
		}
	}

	int[][] dir = {
		{-1,-1}, {-1,0}, {-1,1},
		{0,-1}, {0,1},
		{1,-1}, {1,0}, {1,1}
	};
	int N;

	Monarch[] mons = new Monarch[8625];
	int monId=0;
	int[][] posToId = new int[25][25];
	HashMap<String, Integer> nameToId = new HashMap<>();

	int[] parent = new int[8625];

	List<Integer>[] allyList = new ArrayList[625];
	List<Integer>[] enemyList = new ArrayList[625];

	void init(int N, int mSoldier[][], char mMonarch[][][])
	{
		this.N=N;

		monId=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				mons[monId]=new Monarch(i, j, mSoldier[i][j]);
				posToId[i][j]=monId;
				nameToId.put(new String(mMonarch[i][j]).trim(), monId);

				parent[monId]=monId;

				allyList[monId] = new ArrayList<>();
				allyList[monId].add(monId);
				enemyList[monId] = new ArrayList<>();

				monId++;
			}
		}
		monId++;
	}

	void destroy()
	{

	}

	int ally(char mMonarchA[], char mMonarchB[]) //8000
	{
		int idA = nameToId.get(new String(mMonarchA).trim());
		int idB = nameToId.get(new String(mMonarchB).trim());

		if (isAlly(idA, idB)) return -1;

		if (isEnemy(idA, idB)) return -2;

		makeAlly(idA, idB);
		return 1;
	}

	int attack(char mMonarchA[], char mMonarchB[], char mGeneral[]) //8000
	{
		int idA = nameToId.get(new String(mMonarchA).trim());
		int idB = nameToId.get(new String(mMonarchB).trim());

		if (isAlly(idA, idB)) return -1;

		boolean isAttackable = false;
		for (int i=0; i<8; i++) {
			int nx=mons[idB].posX+dir[i][0];
			int ny=mons[idB].posY+dir[i][1];

			if (!isInBoundary(nx,ny)) continue;

			if (find(posToId[nx][ny])==find(idA)) {
				isAttackable=true;
				break;
			}
		}
		if (!isAttackable) return -2;

		makeEnemy(idA, idB);

		int tmp=0;
		int attackSols=0;
		int defenceSols=mons[idB].solCnt;
		for (int i=0; i<8; i++) {
			int nx=mons[idB].posX+dir[i][0];
			int ny=mons[idB].posY+dir[i][1];

			if (!isInBoundary(nx,ny)) continue;

			int nId=posToId[nx][ny];
			if (find(nId)==find(idA)) {
				tmp=mons[nId].solCnt/2;
				attackSols+=tmp;
				mons[nId].solCnt-=tmp;
			}
			else if (find(nId)==find(idB)) {
				tmp=mons[nId].solCnt/2;
				defenceSols+=tmp;
				mons[nId].solCnt-=tmp;
			}
		}

		if (defenceSols>=attackSols) {
			mons[idB].solCnt = defenceSols-attackSols;
			return 0;
		}
		else {
			allyList[find(idB)].remove(Integer.valueOf(idB)); //?
			mons[monId]=new Monarch(mons[idB].posX, mons[idB].posY, attackSols-defenceSols);
			posToId[mons[idB].posX][mons[idB].posY]=monId;
			nameToId.put(new String(mGeneral).trim(), monId);

			int bossA=find(idA);
			parent[monId]=bossA;

			allyList[bossA].add(monId);

			monId++;

			return 1;
		}
	}

	int recruit(char mMonarch[], int mNum, int mOption) //13000
	{
		int id = nameToId.get(new String(mMonarch).trim());
		int solCnt=0;

		if (mOption==0) {
			mons[id].solCnt+=mNum;
			solCnt+=mons[id].solCnt;
		}
		else {
			id = find(id);
			for (int innerId: allyList[id]) {
				mons[innerId].solCnt+=mNum;
				solCnt+=mons[innerId].solCnt;
			}
		}

		return solCnt;
	}

	int find(int id) {
		if (parent[id]==id) return id;
		else return parent[id]=find(parent[id]);
	}
	boolean isInBoundary(int x, int y) {
		if (x<0||x>=N||y<0||y>=N) return false;
		else return true;
	}
	boolean isAlly(int idA, int idB) {
		idA = find(idA);
		idB = find(idB);

		if (idA==idB) return true;
		else return false;
	}
	boolean isEnemy(int idA, int idB) {
		idA = find(idA);
		idB = find(idB);

		for (int id: enemyList[idA]) {
			if (find(id)==idB) return true; //#
		}
		return false;
	}
	void makeEnemy(int idA, int idB) {
		idA = find(idA);
		idB = find(idB);

		enemyList[idA].add(idB);
		enemyList[idB].add(idA);
	}
	void makeAlly(int idA, int idB) {
		idA = find(idA);
		idB = find(idB);

		if (idA==idB) return;
		else if (idA<idB) {
			parent[idB]=idA;
			allyList[idA].addAll(allyList[idB]);
			enemyList[idA].addAll(enemyList[idB]);
			allyList[idB].clear();
			enemyList[idB].clear();
		}
		else {
			parent[idA]=idB;
			allyList[idB].addAll(allyList[idA]);
			enemyList[idB].addAll(enemyList[idA]);
			allyList[idA].clear();
			enemyList[idA].clear();
		}
	}

}
