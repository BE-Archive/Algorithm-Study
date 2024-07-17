package trankill1127.w24;

import java.io.*;
import java.util.*;

public class BOJ_15553 {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int[] time = new int[n];
		for (int i=0; i<n; i++) time[i]=Integer.parseInt(br.readLine().trim());
		int onTime=time[n-1]-time[0]+1;

		int[] offTime = new int[n-1];
		for (int i=0; i<n-1; i++){
			offTime[i]=time[i+1]-time[i]-1;
		}
		Arrays.sort(offTime);

		for (int i=0; i<k-1; i++){
			onTime-=offTime[n-2-i];
		}
		System.out.print(onTime);
	}
}