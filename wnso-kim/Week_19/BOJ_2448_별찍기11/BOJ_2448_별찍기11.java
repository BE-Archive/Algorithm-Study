package Week_19.BOJ_2448_별찍기11;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2448_별찍기11 {

    static boolean[][] stars;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        stars = new boolean[N][N*4];

        // 별 찍기
        countingStars(N, 0, N-1);

        // 출력
        StringBuilder result = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N*2; j++){
                result.append(stars[i][j]? "*": " ");
            }
            result.append("\n");
        }

        System.out.println(result);
    }

    static void countingStars(int depth, int r, int c){
        if(r<0 || c<0 ||  r>=N*2 || c>=N*4) return;

        if(depth == 3){
            line3(r, c);
            return;
        }

        int half = depth/2;
        countingStars(half, r, c);
        countingStars(half, r+half, c-half);
        countingStars(half, r+half, c+half);
    }

    static void line3(int r, int c){
        stars[r][c] = true;                              //  *
        stars[r+1][c-1] = stars[r+1][c+1]= true;         // * *
        for(int i=-2; i<3; i++) stars[r+2][c+i] = true;  //*****
    }
}
