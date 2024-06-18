import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void bubbleSort(int[] arr, int start, int end) {
		for(int i = 0; i < end; i++) {
			for(int j = end - 1; j > i; j--) {
				if(arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				}
			}
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		bubbleSort(arr, 0, arr.length);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}
		System.out.println(sb);
	}
}