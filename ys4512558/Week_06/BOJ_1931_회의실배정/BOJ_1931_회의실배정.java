import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] times = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times, (o1, o2)-> {
			if(o1[0] == o2[0]) {
				return Integer.compare(o1[1], o2[1]);
			}
			return Integer.compare(o1[0], o2[0]);
		});
		int cnt = 1;
		int end = times[0][1];
		for (int i = 1; i < times.length; i++) {
			if(end > times[i][1]) {
				end = times[i][1]; 
			} else if(end <= times[i][0]) {
				cnt++;
				end = times[i][1];
			}
		}
		System.out.println(cnt);
	}
}