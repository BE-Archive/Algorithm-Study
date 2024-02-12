import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_VALUE = 100000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N], freq = new int[MAX_VALUE + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = 0, answer = 1;
		while(left <= right && right < N) {
			freq[arr[right]]++;
			while(freq[arr[right]] > K) {
				freq[arr[left++]]--;
			}
			answer = Math.max(answer, right - left + 1);
			right++;
		}
		System.out.println(answer);
	}
}