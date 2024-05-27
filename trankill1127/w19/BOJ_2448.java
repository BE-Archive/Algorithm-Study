import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2448 {

    public static boolean[][] arr;
    public static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n=Integer.parseInt(br.readLine());
        h = n;
        w = 2*n-1;
        arr = new boolean[h][w];

        function(h, 0, n-1);

        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                if (arr[i][j]) sb.append("*");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void function(int triH, int curX, int curY){
        if (triH==3){
            arr[curX][curY]=true;
            arr[curX+1][curY-1]=true;
            arr[curX+1][curY+1]=true;
            for (int i=curY-2; i<curY+3; i++){
                arr[curX+2][i]=true;
            }
            return;
        }

        function(triH/2, curX, curY);
        function(triH/2, curX+triH/2, curY-triH/2);
        function(triH/2, curX+triH/2, curY+triH/2);
    }

}
