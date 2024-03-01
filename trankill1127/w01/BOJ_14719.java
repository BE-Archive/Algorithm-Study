import java.util.Scanner;

public class BOJ_14719 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int w = sc.nextInt();

        int input;
        int[][] world = new int[h][w];
        for (int i=0; i<w; i++) {
            input=sc.nextInt();
            for (int j=0; j<input; j++) {
                world[h-1-j][i]=1;
            }
        }

        int totalVol = 0;

        for (int i=h-1; i>=0; i--) {

            for (int j=0; j<w-1; j++) {

                if (world[i][j]==1) {

                    int k = j+1;
                    while (k<w && world[i][k]==0) {
                        k++;
                    }

                    if (k!=w) {
                        totalVol+=(k-j-1);
                        if (k<w) j=k-1;
                    }

                }
            }
        }

        System.out.print(totalVol);
    }
}
