package trankill1127.w33;

import java.util.*;
import java.io.*;

public class BOJ_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);  // 정렬

		int count = 0;
		for (int i = 0; i < n; i++) { //두개의 값을 더해서 nums[i]를 만드는 조합을 찾는다.

			//투 포인터
			int left = 0;
			int right = n - 1;

			while (left < right) {
				if (left == i) {
					left++;
					continue;
				}
				if (right == i) {
					right--;
					continue;
				}

				int sum = nums[left] + nums[right];
				if (sum == nums[i]) {
					count++;
					break;
				}
				else if (sum < nums[i]) left++;
				else right--;

			}
		}

		System.out.print(count);
	}
}