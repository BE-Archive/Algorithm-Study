import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
	public static Random random = new Random();
	public static void quickSort(int[] arr, int start, int end) {
		if(start >= end) return;
		swap(arr, start, start + random.nextInt(end - start));
		int pivot = arr[start];
		
		int left = start, right = end;
		
		while(left < right) {
			while(arr[right] > pivot) {
				right--;
			}
			while(left < right && arr[left] <= pivot) {
				left++;
			}
			swap(arr, left, right);
		}
		swap(arr, start, left);
		
		quickSort(arr, start, left - 1);
		quickSort(arr, left + 1, end);
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
		
		quickSort(arr, 0, arr.length - 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}
		
		System.out.println(sb);
	}
}