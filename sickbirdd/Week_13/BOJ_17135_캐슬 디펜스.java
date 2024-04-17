import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int LIVE = 1, DEAD = 2, EXIT = 3, INF = (int) 1e9;
	static int[] archors = new int[3];
	static int[][] mat;
	static int N, M, D, answer;
	
	static class Monster {
		int row, col, status;
		Monster(int row, int col) {
			this.row = row;
			this.col = col;
			this.status = LIVE;
		}
		void move() {
			if(status == LIVE && ++row == N) {
				status = EXIT;
			}
		}
	}
	
	static int getDist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
	
	static int startWave() {
		Queue<Monster> wave = new ArrayDeque<>();
		int kill = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				if(mat[i][j] == 1) wave.add(new Monster(i, j));
			}
		}
		
		while(!wave.isEmpty()) {
			int size = wave.size();
			int[] range = {INF, INF, INF};
			int[] cols = {M + 1, M + 1, M + 1};
			Monster[] target = new Monster[3];
			
			while(--size >= 0) {
				Monster monster = wave.poll();
				if(monster.status != LIVE) continue;
				for(int k = 0; k < 3; k++) {
					int col = archors[k];
					int dist = getDist(N, col, monster.row, monster.col);
					if(dist > D) continue;
					if(dist < range[k]) {
						range[k] = dist;
						cols[k] = monster.col;
						target[k] = monster;
					}
					else if(dist == range[k] && cols[k] > monster.col) {
						cols[k] = monster.col;
						target[k] = monster;
					}
				}
				monster.move();
				if(monster.status == LIVE) wave.add(monster);
			}
			
			for(int k = 0; k < 3; k++) {
				if(target[k] != null && target[k].status != DEAD) {
					kill++;
					target[k].status = DEAD;
				}
			}
		}
		
		return kill;
	}
	
	static void selArchor(int count, int index) {
		if(count == 3) {
			answer = Math.max(answer, startWave());
			return;
		}
		for(int i = index; i < M; i++) {
			archors[count] = i;
			selArchor(count + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selArchor(0, 0);
		System.out.println(answer);
	}
}