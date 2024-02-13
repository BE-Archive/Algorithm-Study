import java.io.*;
import java.util.*;

public class Main {

	static int N, arr[];

	static void InsertSort() {
		int target;
		int pos;

		// i 위치 기준으로 왼쪽은 정렬 상태다.
		for (int i = 1; i < N; ++i) {
			target = arr[i];

			// 나 target은 정렬된 부분의 어디에 끼어들 수 있을까?
			for (pos = i - 1; pos > -1; --pos) {
				// 나보다 작은 친구 앞에 서야 한다.
				// !! 근데 그 위치 찾아서 끼어들려면 새로운 배열고 이어 붙여? ;;
				// 그러지 않기 위해 나보다 큰 친구들을 스왑해서 앞으로 밀어보자~

				if (target < arr[pos])
					arr[pos + 1] = arr[pos];
				else
					break;
			}
			arr[pos + 1] = target; // 나보다 작은 친구 앞에 서야하니까 pos+1
		}

	}

	static void selectionSort() {
		// index를 기준으로 양옆의 정렬 상태가 다르다.
		// [정렬] [index] [미정렬]
		// 미정렬 리스트에서 최소값을 찾아 index와 자리를 바꿔가며 진행한다.
		for (int i = 0; i < N; ++i) {
			int min_idx = i;
			for (int j = i + 1; j < N; ++j) {
				if (arr[j] < arr[min_idx]) {
					min_idx = j;
				}
			}

			int tmp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = tmp;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		InsertSort();

		// output
		for (int i = 0; i < N; ++i)
			sb.append(arr[i]).append("\n");
		System.out.print(sb);
	}
}
