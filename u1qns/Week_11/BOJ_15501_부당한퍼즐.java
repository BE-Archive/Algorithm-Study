import java.util.*;
import java.io.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int [N];
		int[] number = new int[N];
		int start, start2;
		start = start2 = -1;
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
		{
			input[i] = Integer.parseInt(st.nextToken());
			if(input[i] == 1)
				start = i;
			
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
		{
			number[i] = Integer.parseInt(st.nextToken());
			if(number[i] == 1)
				start2 = i;
			
		}

		boolean r1 = true, r2 = true;
		int pos = start, pos2 = start2;
		for(int i=0 ; i<N; ++i)
		{
			pos = (start + i) % N;
			pos2 = (start2 + i) % N;
			if(input[pos] != number[pos2])
			{
				r1 = false;
				break;
			}
		}
		
		for(int i=0; i<N; ++i)
		{
			pos = (start + N - i) % N;
			pos2 = (start2 +i) % N;
			if(input[pos] != number[pos2])
			{
				r2 = false;
				break;
			}
		}
		
		System.out.print((r1 || r2) ? "good puzzle" : "bad puzzle");
	} // main
}
