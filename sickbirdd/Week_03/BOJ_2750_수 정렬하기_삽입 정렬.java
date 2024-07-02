import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void insertionSort(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int value = arr[i], index = i - 1;
			while(index >= 0 && arr[index] > value) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		insertionSort(arr);
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append('\n');
		}
		System.out.print(sb);
	}
}