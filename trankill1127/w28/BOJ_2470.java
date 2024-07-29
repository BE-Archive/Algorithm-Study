package trankill1127.w28;

import java.io.*;
import java.util.*;

public class BOJ_2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());

		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int l = 0;
		int r = n - 1;
		int minAbs = Integer.MAX_VALUE;
		int[] result = new int[2];

		while (l < r) {
			int sum = arr[l] + arr[r];
			int absSum = Math.abs(sum);

			if (absSum < minAbs) {
				minAbs = absSum;
				result[0] = arr[l];
				result[1] = arr[r];
				if (sum == 0)
					break;
			}

			if (sum > 0)
				r--;
			else
				l++;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(result[0]).append(' ').append(result[1]);
		System.out.print(sb);
	}
}



