import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2170_선_긋기 {

    static int N;
    static int[][] lines;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];

        for(int i=0; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(stk.nextToken());
            lines[i][1] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(lines, (l1, l2) -> l1[0]!=l2[0]? l1[0]-l2[0]: l1[1]-l2[1]);

        long sum = 0;
        int s = lines[0][0];
        int e = lines[0][1];
        for(int[] line: lines){
            if(line[0] <= e) // 라인 연결
                e = Math.max(e, line[1]);
            else{          // 라인 끊김  
                sum += e-s;
                s = line[0];
                e = line[1];
            }
        }

        sum += e-s;

        System.out.println(sum);
    }
}
