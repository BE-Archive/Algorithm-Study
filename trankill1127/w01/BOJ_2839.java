import java.util.Scanner;

public class BOJ_2839 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] memo = new int[n+1];

        if (n>=3) memo[3]=1;
        if (n>=5) memo[5]=1;
        for (int i=3; i<n+1; i++) {
            if (memo[i]>0) {
                if (i + 3 < n + 1) {
                    if (memo[i+3]==0){
                        memo[i+3] = memo[i] + 1;
                    }
                    else {
                        memo[i+3] = Math.min(memo[i + 3], memo[i] + 1);
                    }
                }
                if (i + 5 < n + 1) {
                    if (memo[i+5]==0){
                        memo[i+5] = memo[i] + 1;
                    }
                    else {
                        memo[i+5] = Math.min(memo[i + 5], memo[i] + 1);
                    }
                }
            }
        }

        if (memo[n]==0) System.out.println(-1);
        else System.out.println(memo[n]);
    }

}

