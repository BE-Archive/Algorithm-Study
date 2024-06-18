import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] arr, temp;
	
	static void MergeSort(int arr[], int start, int end)
	{
		if(start >= end) return;
		
		int mid = (start + end) /2;

		MergeSort(arr, start, mid);
		MergeSort(arr, mid+1, end);
		
		// i는 오른쪽 리스트의 첫 번째
		// j는 왼쪾 리스트의 첫번째라고 생각
		//int [] temp = new int[end-start +1];
		int i = start;
		int j = mid+1;
		int k = start;
		
		while(i<=mid && j<=end)
			temp[k++] = (arr[i] < arr[j] ? arr[i++] : arr[j++]);
		
		while(i<=mid)
			temp[k++] = arr[i++];
		
		while(j<=end)
			temp[k++] = arr[j++];
		
		for(int idx = start; idx<=end; ++idx)
			arr[idx] = temp[idx];
		
		return;
		
	}
	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		temp = new int[N];

		for (int i = 0; i<N; ++i)
			arr[i] = Integer.parseInt(br.readLine());

		MergeSort(arr, 0, N-1);
		
		for(int i=0; i<N; ++i)
			sb.append(arr[i]).append("\n");

		System.out.print(sb);
	}

}
