import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1756 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int d = Integer.parseInt(st.nextToken()); //오븐 깊이
        int n = Integer.parseInt(st.nextToken()); //피자 반죽 개수
        int[] oven = new int[d+1];
        oven[0]=Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine().trim());
        for (int i=1; i<=d; i++){
            oven[i]=Math.min(oven[i-1], Integer.parseInt(st.nextToken()));
        }

        int bottom=d;
        st = new StringTokenizer(br.readLine().trim());
        for (int i=0; i<n; i++) {
            int dough = Integer.parseInt(st.nextToken());
            while (bottom>=0 && oven[bottom]<dough) bottom--;
        }
        System.out.println(bottom+1);
    }
}

/*
나는 이분탐색 계의 쓰레기인걸가........
쥴라ㅠㅠㅠㅠ
아 진차 개빡쵸
 */