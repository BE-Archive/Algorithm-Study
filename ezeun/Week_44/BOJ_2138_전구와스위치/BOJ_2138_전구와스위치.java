
import java.util.Scanner;
public class Main {

	static boolean same(String[] x, String[] y) {
		int len = x.length;
		for(int i=0; i<len; i++) {
			if(!x[i].equals(y[i])) return false; //여기서도 ==으로 비교하면 안됨!!
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String a = sc.next();
		String[] arr = a.split(""); //모든 문자를 배열화
		String b = sc.next();
		String[] brr = b.split("");
		
		int ans1 = 0, ans2 = 0;
		
		String[] tmp = new String[n]; //arr 복제해놓기
		for(int i=0; i<n; i++) tmp[i]=arr[i];
		
		//첫번째 스위치를 누른 경우
		ans1+=1;
		arr[0] = Integer.toString(1-Integer.parseInt(arr[0]));
		arr[1] = Integer.toString(1-Integer.parseInt(arr[1]));
		for(int i=1; i<n; i++) {
			if(arr[i-1]!=brr[i-1]) { //i번째 스위치를 눌러야함
				ans1+=1;
				
				arr[i-1] = Integer.toString(1-Integer.parseInt(arr[i-1]));
				arr[i] = Integer.toString(1-Integer.parseInt(arr[i]));
				if(i==n-1) continue;
				arr[i+1] = Integer.toString(1-Integer.parseInt(arr[i+1]));				
			}
		}
		if(same(arr, brr)==false) ans1=-1; // ==으로 비교하면 안됨, 배열은 equals 비교도 안되는듯
		for(int i=0; i<n; i++) arr[i]=tmp[i]; //a초기화
		
		//첫번째 스위치를 안누른 경우
		for(int i=1; i<n; i++) {
			if(arr[i-1]!=brr[i-1]) { //i번째 스위치를 눌러야함
				ans2+=1;
				
				arr[i-1] = Integer.toString(1-Integer.parseInt(arr[i-1]));
				arr[i] = Integer.toString(1-Integer.parseInt(arr[i]));
				if(i==n-1) continue;
				arr[i+1] = Integer.toString(1-Integer.parseInt(arr[i+1]));						
			}
		}
		if(same(arr, brr)==false) ans2=-1;
		
		if(ans1==-1 && ans2==-1) System.out.println(-1);
		else if(ans1!=-1) System.out.println(ans1);
		else System.out.println(ans2);
		
	}

}

/* 스위치를 누르는 순서는 상관없음 -> 순차적으로 누르자
 * n이 버튼을 누름으로써 n-1, n, n+1이 바뀜 -> 현재꺼(n)를 바꾸고싶다면 n이 아니라 n+1을 눌러야 함(여기에만 영향)
 * -> 첫번째 버튼은 누를 기회가 없어지므로, 첫번째 버튼을 누르는 경우와 안누르는 경우 둘 다 살펴봐야함
 * 000
 * 110
 * 001
 * 010

 * 000
 * 111
 * 100
 */

