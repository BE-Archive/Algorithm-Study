import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2023 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<String> result = amazingChk(n);
		for (String s : result) {
			System.out.println(s);
		}
	}
	
	public static List<String> amazingChk(int number) {
		Queue<String> q = new LinkedList<>();
		q.add("2"); q.add("3"); q.add("5"); q.add("7");
		
		while (!q.isEmpty() && q.peek().length()!=number) { // ㄱ
			int lvCnt=q.size(); //4
			while (lvCnt>0) { //1
				String now = q.poll();
				for (int i=0; i<10; i++) {
					if (isPrime(Integer.parseInt(now+Integer.toString(i)))) {
						q.add(now+Integer.toString(i)); //23 29 31 37 53 59 71 73 79
					}
				}
				lvCnt--;
			}	
		}
		
		List<String> result = new ArrayList<>();
		while (!q.isEmpty()) {
			result.add(q.poll());
		}
		
		return result;
	}
	
	public static boolean isPrime(int number) {
		for (int i=2; i*i<=number; i++) {
			if (number%i==0) return false;
		}
		return true;
	}
}
