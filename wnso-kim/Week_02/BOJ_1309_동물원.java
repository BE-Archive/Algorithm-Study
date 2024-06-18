import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long mod = 9901l; 
		// dpMap
		// [i][0]: 사자를 넣지 않는 경우
		// [i][1]: 사자를 첫 번째에 넣는 경우
		// [i][2]: 사자를 두 번째에 넣는 경우
		int[][] dpMap = new int[N][3];
		dpMap[0][0] = dpMap[0][1] = dpMap[0][2] = 1;
		
		for(int i=1; i<N; i++) {
			dpMap[i][0] = (int)((dpMap[i-1][0] + dpMap[i-1][1] + dpMap[i-1][2])%mod);
			dpMap[i][1] = (int)((dpMap[i-1][0] + dpMap[i-1][2])%mod);
			dpMap[i][2] = (int)((dpMap[i-1][0] + dpMap[i-1][1])%mod);
		}
		
		System.out.println((dpMap[N-1][0] + dpMap[N-1][1] + dpMap[N-1][2])%mod);
	}

}