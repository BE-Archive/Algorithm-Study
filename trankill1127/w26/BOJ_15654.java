package trankill1127.w26;

import java.io.*;
import java.util.*;

public class BOJ_15654 {

	public static int n;
	public static int m;
	public static int[] arr;
	public static int[] perm;
	public static boolean[] visited;
	public static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n];
		visited = new boolean[n];
		perm = new int[m];

		st = new StringTokenizer(br.readLine().trim());
		for (int i=0; i<n; i++){
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		permutation(0);
		bw.flush();
		bw.close();
		return;
	}

	public static void permutation(int idx) throws IOException{
		if (idx==m){
			for (int i=0; i<m; i++){
				bw.write(String.valueOf(perm[i]));
				bw.write(" ");
			}
			bw.newLine();
			return;
		}

		for (int i=0; i<n; i++){
			if (visited[i]) continue;
			perm[idx]=arr[i];
			visited[i]=true;
			permutation(idx+1);
			visited[i]=false;
		}
		return;
	}

}
