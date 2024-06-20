import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1081 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		long L=Integer.parseInt(st.nextToken());
		long R=Integer.parseInt(st.nextToken());
		System.out.print(calculate(R)-calculate(L-1));
	}
	
	public static long calculate(long n) { //24660
		if (n==0) return 0;
		
		long result=0;
		long digit = 1; 
		while (digit<n)	digit*=10; 
		
		long left=0;
		while (digit>0) {
			if (left==0) {
				for (int i=1; i<=(n/digit); i++) {
					if (i<(n/digit)) result+=i*digit;
					else result+=i*(n%digit+1);
				}
			}
			else {
				for (int i=1; i<10; i++) {
					if (i<(n/digit)) result+=(left+1)*i*digit;
					else if (i==(n/digit)) result+=(left)*i*digit+i*(n%digit+1);
					else result+=(left)*i*(digit);
				}
			}
			
			left*=10;
			left+=(n/digit);
			n=n%digit;
			digit/=10;
		}
		
		return result;
	}
}
