import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N, tmp;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean isPrime(final int n)
	{
		if(n<2)
			return false;
		
		for(int i=2; i*i<=n; ++i)
		{
			if(n%i==0)
				return false;
		}
		
		return true;
	}
	
	
	static void solve(final int cur, final int d)throws IOException
	{
		if(d==N)
		{
			bw.write(String.valueOf(cur));
			bw.newLine();
			bw.flush();
			return;
		}
		
		for(int i=1; i<10; ++i)
		{
			tmp = cur*10 + i;
			if(isPrime(tmp))
				solve(tmp, d+1);
		}
	}
	
	
  public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
    	    
	    solve(0, 0);
	    
	    bw.close();
	}

}
