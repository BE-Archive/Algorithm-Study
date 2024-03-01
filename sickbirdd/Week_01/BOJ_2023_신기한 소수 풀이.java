import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void findPrime(int x, int now) throws IOException {
		if(x == N) {
			bw.write(String.valueOf(now));
			bw.newLine();
			bw.flush();
			return;
		}
		for(int i = 1; i <= 9; i++) {
			int sum = now * 10 + i;
			boolean flag = true;
			for(int j = 2; j * j <= sum; j++) {
				if(sum % j == 0) {
					flag = false;
					break;
				}
			}
			if(flag) findPrime(x + 1, sum);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken()); 
	    findPrime(1, 2);
	    findPrime(1, 3);
	    findPrime(1, 5);
	    findPrime(1, 7);
	    bw.close();
	}
}