import java.io.*;
import java.util.*;

public class Main {
	final static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static Unit[] units;
	static int[][] mat;
	static ArrayDeque<Unit>[][] dq;

	static class Unit {
		int row, col, type;
		Unit next;

		Unit(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.next = null;
		}

		void setLoc(int row, int col) {
			this.row = row;
			this.col = col;
		}

		boolean go() {
			int nrow = row + dir[type][0], ncol = col + dir[type][1];
			if (nrow < 0 || nrow >= mat.length || ncol < 0 || ncol >= mat.length || mat[nrow][ncol] == 2) {
				type = type % 2 == 0 ? (type + 1) % 4 : (type + 3) % 4;
				nrow = row + dir[type][0];
				ncol = col + dir[type][1];
			}
			if(nrow < 0 || nrow >= mat.length || ncol < 0 || ncol >= mat.length || mat[nrow][ncol] == 2) return true;
			return move(row, col, nrow, ncol, mat[nrow][ncol] == 1);
		}
	}

	static boolean move(int row, int col, int nrow, int ncol, boolean reverse) {
		ArrayDeque<Unit> from = dq[row][col], to = dq[nrow][ncol];
		while(!from.isEmpty()) {
			Unit temp = reverse ? from.pollLast() : from.pollFirst();
			temp.setLoc(nrow, ncol);
			temp.next = to.peekLast();
			to.addLast(temp);
		}
		return to.size() < 4;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		dq = new ArrayDeque[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				dq[i][j] = new ArrayDeque<>();
			}

		}

		units = new Unit[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			units[i] = new Unit(r - 1, c - 1, d - 1);
			dq[r - 1][c - 1].addLast(units[i]);
		}

		int turn = 1;
		while(turn <= 1000) {
			for(int i = 0; i < K; i++) {
				if(units[i].next != null) continue;
				if(!units[i].go()) {
					System.out.println(turn);
					return;
				}
			}
			turn++;
		}
		System.out.println(-1);
	}
}