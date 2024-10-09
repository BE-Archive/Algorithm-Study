package trankill1127.w38;

import java.io.*;
import java.util.*;

public class BOJ_1063 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		int[] kPos = new int[] {s.charAt(1) - '1', s.charAt(0) - 'A'};
		s = st.nextToken();
		int[] rPos = new int[] {s.charAt(1) - '1', s.charAt(0) - 'A'};
		int n = Integer.parseInt(st.nextToken());

		int nextPosKX = 0;
		int nextPosKY = 0;
		int nextPosRX = 0;
		int nextPosRY = 0;
		boolean isRockMove = false;

		for (int i = 0; i < n; i++) {
			String op = br.readLine();
			isRockMove = false;

			switch (op) {
				case "R":
					nextPosKX = kPos[0];
					nextPosKY = kPos[1] + 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX;
						nextPosRY = nextPosKY + 1;
					}
					break;
				case "L":
					nextPosKX = kPos[0];
					nextPosKY = kPos[1] - 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX;
						nextPosRY = nextPosKY - 1;
					}
					break;
				case "B":
					nextPosKX = kPos[0] - 1;
					nextPosKY = kPos[1];
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX - 1;
						nextPosRY = nextPosKY;
					}
					break;
				case "T":
					nextPosKX = kPos[0] + 1;
					nextPosKY = kPos[1];
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX + 1;
						nextPosRY = nextPosKY;
					}
					break;
				case "RT":
					nextPosKX = kPos[0] + 1;
					nextPosKY = kPos[1] + 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX + 1;
						nextPosRY = nextPosKY + 1;
					}
					break;
				case "LT":
					nextPosKX = kPos[0] + 1;
					nextPosKY = kPos[1] - 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX + 1;
						nextPosRY = nextPosKY - 1;
					}
					break;
				case "RB":
					nextPosKX = kPos[0] - 1;
					nextPosKY = kPos[1] + 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX - 1;
						nextPosRY = nextPosKY + 1;
					}
					break;
				case "LB":
					nextPosKX = kPos[0] - 1;
					nextPosKY = kPos[1] - 1;
					if (nextPosKX == rPos[0] && nextPosKY == rPos[1]) {
						isRockMove = true;
						nextPosRX = nextPosKX - 1;
						nextPosRY = nextPosKY - 1;
					}
					break;
			}

			if (0 <= nextPosKX && nextPosKX < 8 && 0 <= nextPosKY && nextPosKY < 8) {
				if (isRockMove) {
					if (0 <= nextPosRX && nextPosRX < 8 && 0 <= nextPosRY && nextPosRY < 8) {
						kPos[0] = nextPosKX;
						kPos[1] = nextPosKY;
						rPos[0] = nextPosRX;
						rPos[1] = nextPosRY;
					}
				} else {
					kPos[0] = nextPosKX;
					kPos[1] = nextPosKY;
				}
			}
		}

		System.out.println((char)(kPos[1] + 'A') + "" + (kPos[0] + 1));
		System.out.println((char)(rPos[1] + 'A') + "" + (rPos[0] + 1));
	}

}
