package trankill1127.w27;

import java.io.*;
import java.util.*;

public class BOJ_5904 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine().trim());

		Deque<Integer> d = new ArrayDeque<>();
		d.add(2); //d: 2
		int idx=1;
		int k=1;
		while (idx<n) { //4<11
			d.offerFirst(k+2); //d: 2 3 4
			k++; //k: 3

			int size=d.size();
			while (size>1){
				idx+=(d.peekFirst()+1); //idx: 6
				if (idx==n) break;
				d.offerLast(d.pollFirst()); //d: 3 4 2

				size--;
			}


		}
		if (idx==n) System.out.print('m');
		else System.out.print('o');
	}
}
