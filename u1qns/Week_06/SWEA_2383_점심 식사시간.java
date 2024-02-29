import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    // 정렬 추가 
	static class Info implements Comparable<Info> {
		int x, y, d, time;

		public Info(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Info(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.d, o.d);
		}
	}

	static int N, map[][], answer;

	static List<Info> person = new LinkedList<>();
	static List<Info> stair = new LinkedList<>();
	static boolean isSelected[];

	static int getDistance(int idx, int idx2) {
		return Math.abs(person.get(idx).x - stair.get(idx2).x) + Math.abs(person.get(idx).y - stair.get(idx2).y);

	}

	static void go() {
		// 계단
		int result = 0;

    // 계단으로 이동 중인 사람
		List<Info> A = new LinkedList<>();
		List<Info> B = new LinkedList<>();

    // 계단에 도착한 사람
		List<Info> stairA = new LinkedList<>();
		List<Info> stairB = new LinkedList<>();

    // 이동 후에도 이동 중인 사람만 담은 리스트
		List<Info> tmpA = new LinkedList<>();
		List<Info> tmpB = new LinkedList<>();

    // 계단에서 탈출 못 한 사람만 담은 리스트
		List<Info> tmpA2 = new LinkedList<>();
		List<Info> tmpB2 = new LinkedList<>();

		// 그룹 나누기
		for (int i = 0; i < person.size(); ++i) {
			if (isSelected[i]) {
				person.get(i).d = getDistance(i, 0);
				person.get(i).time = stair.get(0).d;
				A.add(person.get(i));
			} else {
				person.get(i).d = getDistance(i, 1);
				person.get(i).time = stair.get(1).d;
				B.add(person.get(i));
			}
		}

		while (true) {
			++result;

			if (A.isEmpty() && stairA.isEmpty() && B.isEmpty() && stairB.isEmpty())
				break;

      // 계단에 있는 사람들 중 3명만 내려가
      // 계단에 머물면 TMP에 넣는다.
      
			tmpA = new LinkedList<>();
			tmpB = new LinkedList<>();

			int countA = 0;
			for (int i = 0; i < stairA.size(); ++i) {
				if (countA < 3)
        {
          countA++;
					if (--stairA.get(i).time != 0)
						tmpA.add(stairA.get(i));
				} 
        else
					tmpA.add(stairA.get(i));
			}

			int countB = 0;
			for (int i = 0; i < stairB.size(); ++i) {
				if (countB < 3) 
        {
          countB++;
					if (--stairB.get(i).time != 0)
						tmpB.add(stairB.get(i));
				} 
        else
					tmpB.add(stairB.get(i));
      }
			


			// -------------------------

      // 이동 못 한 친구들을 빨리 계단으로 이동하도록..
      // 아직도 이동 중이면 tmp2
      // 계단으로 갔다면 tmp
			for (int i = 0; i < A.size(); ++i) 
      {
				if (--A.get(i).d == 0)
					tmpA.add(A.get(i)); // 계단
				else
					tmpA2.add(A.get(i));
			}

			for (int i = 0; i < B.size(); ++i) 
      {
				if (--B.get(i).d == 0)
					tmpB.add(B.get(i)); // 계단
				else
					tmpB2.add(B.get(i));
			}

      // 위 로직에 따라 이동할 친구들은 보내주고 남은 애들 처리 
			A = tmpA2;
			B = tmpB2;

			stairA = tmpA;
			stairB = tmpB;

			tmpA = new LinkedList<>();
			tmpB = new LinkedList<>();

			tmpA2 = new LinkedList<>();
			tmpB2 = new LinkedList<>();
		}

		answer = Math.min(result, answer);
	}

  // 만들 수 있는 조합 찾기
	static void pick(int cnt, int idx, int r) {
		if (cnt == r) {
			go();
			return;
		}

		for (int i = idx; i < person.size(); ++i) {
			
			if (isSelected[i]) continue;
			isSelected[cnt] = true;
			pick(cnt + 1, i + 1, r);
			isSelected[cnt] = false;
      
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			// init
			person.clear();
			stair.clear();

			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
          
					if (map[i][j] == 1)
						person.add(new Info(i, j));
					else if (map[i][j] != 0)
						stair.add(new Info(i, j, map[i][j]));
					
				}
			}

			isSelected = new boolean[person.size()];
			for (int i = 0; i <= person.size(); ++i)
				pick(0, 0, i);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}
}
