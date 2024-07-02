import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long arr[];
	public static void main(String[] args) throws IOException {

		//StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long answer1 = 0, answer2 = 0;
		long min = Integer.MAX_VALUE;
		long sum = 0;
		int start = 0, end = N-1;

		while(start < end)
		{
			sum = arr[start] + arr[end];
			if(sum == 0)
			{
				answer1 = arr[start];
				answer2 = arr[end];
				break;//return;
			}
			
			if(Math.abs(sum) < min)
			{
				min = Math.abs(sum);
				answer1 = arr[start];
				answer2 = arr[end];
			}
			
			if(sum < 0) ++start;
			else if(sum > 0) --end;

		}
				
		System.out.print(answer1 + " " + answer2);
	} // main
}
