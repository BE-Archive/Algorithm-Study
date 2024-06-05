import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		int[] min_width = new int[D + 1], index = new int[D + 1];
		min_width[0] = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < D; i++) {
			int width = Integer.parseInt(st.nextToken());
			min_width[i + 1] = min_width[i] >= width ? width : min_width[i];
			index[i + 1] = min_width[i] >= width ? i : index[i];
		}
		st = new StringTokenizer(br.readLine());
		int bottom = D;
		for(int i = 0; i < N; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			if(bottom <= 0) {
				bottom--;
				continue;
			}
			while(min_width[bottom] < pizza) {
				bottom = index[bottom];
			}
			if(min_width[bottom] >= pizza) {
				bottom--;
			}
		}
		System.out.println(bottom < 0 ? 0 : bottom + 1);
	}
}