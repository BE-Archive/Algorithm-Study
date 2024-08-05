package trankill1127.w29;

import java.io.*;
import java.util.*;

public class BOJ_10424 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		List<int[]> list = new ArrayList<>();
		for (int i=0; i<n; i++){
			list.add(new int[]{Integer.parseInt(st.nextToken()), i});
		}

		Collections.sort(list, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2){
				return o1[0]-o2[0];
			}
		});

		StringBuilder sb = new StringBuilder("");
		for (int i=0; i<n; i++){
			sb.append(i-list.get(i)[1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
