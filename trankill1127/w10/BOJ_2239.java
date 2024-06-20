import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2239 {

    public static int[][] area = new int[9][9];
    public static ArrayList<int[]> blank = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                area[i][j] = s.charAt(j) - '0';
                if (area[i][j]==0) blank.add(new int[]{i,j});
            }
        }

        solution(0);

        StringBuilder sb =new StringBuilder();
        for (int i=0; i<9; i++){
            for (int value : area[i]) sb.append(value);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean solution(int depth){
        if (depth==blank.size()){
            return true;
        }

        int x=blank.get(depth)[0];
        int y=blank.get(depth)[1];
        for (int i=1; i<=9; i++){
            area[x][y]=i;
            if (isValid(x, y) && solution(depth+1)) {
                return true;
            }
            area[x][y]=0;
        }

        return false;
    }

    public static boolean isValid(int x, int y){
        for (int i=0; i<9; i++){
            if (x!=i && area[x][y]==area[i][y]) return false;
            if (y!=i && area[x][y]==area[x][i]) return false;
        }

        for (int i=x/3*3; i<x/3*3+3; i++){
            for (int j=y/3*3; j<y/3*3+3; j++){
                if (x!=i && y!=j && area[x][y]==area[i][j]) return false;
            }
        }

        return true;
    }

}
