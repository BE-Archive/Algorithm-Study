import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void mergeSort(int[] arr, int[] copy, int start, int end) {
		if(end - start < 2) return;
		
		int mid = (start + end) / 2;
		mergeSort(arr, copy, start, mid);
		mergeSort(arr, copy, mid, end);
		
		int a = start, b = mid;
		int index = start;
		
		while(a < mid || b < end) {
			copy[index++] = (b == end || (a < mid && arr[a] < arr[b])) ? arr[a++] : arr[b++];
		}
		
		while(--index >= start) {
			arr[index] = copy[index];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N], copy = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		mergeSort(arr, copy, 0, arr.length);
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append('\n');
		}
		System.out.print(sb);
	}
}