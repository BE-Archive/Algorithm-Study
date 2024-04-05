import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_15501 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) {
			arr1[i]=Integer.parseInt(st.nextToken());

		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) arr2[i]=Integer.parseInt(st.nextToken());
		
		boolean isPossible=false;
		
		//뒤집기 전
		ArrayDeque<Integer> d = new ArrayDeque<>(n);
		for (int i=0; i<n; i++) d.add(arr1[i]);
		while (d.getFirst()!=arr2[0]) d.add(d.pollFirst());
		for (int i=0; i<n; i++) arr1[i]=d.pollFirst();
		for (int i=0; i<n; i++) {
			if (arr1[i]!=arr2[i]) break;
			else {
				if (i==n-1) isPossible=true;
			}
		}
		
		//뒤집기 후
		if (!isPossible) {
			for (int i=n-1; i>=0; i--) d.add(arr1[i]);
			while (d.getFirst()!=arr2[0]) d.add(d.pollFirst());
			for (int i=0; i<n; i++) arr1[i]=d.pollFirst();
			for (int i=0; i<n; i++) {
				if (arr1[i]!=arr2[i]) break;
				else {
					if (i==n-1) isPossible=true;
				}
			}
		}
		
		if (isPossible) System.out.println("good puzzle");
		else System.out.println("bad puzzle");
	}
}
