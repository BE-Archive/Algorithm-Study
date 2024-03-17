import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		int N = Integer.parseInt(st.nextToken());
        int max, answer = 0;
		int A[] = new int[N];
		int DP[] = new int[N];
		
		st= new StringTokenizer(br.readLine());
	    for (int i = 0; i<N; ++i)
	        A[i] = Integer.parseInt(st.nextToken());
	    
	    for (int i=0; i<N; ++i)
	    {
            max = 0;
	        for (int j=0; j<i; ++j)
	        {
	            if (A[i] > A[j]  && DP[j] > max)
	                max = DP[j];
	        }
	        
	        DP[i] = max + A[i];
            answer = Math.max(answer, DP[i]);
	    }

		System.out.print(answer);
	}
}
