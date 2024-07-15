import java.io.*;
import java.util.StringTokenizer;

public class BOJ16432 {
    static int[][] arr;
    static int[] num;
    static boolean isLive = false;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][];
        num = new int[N];
        //이전 선택이 단일 선택일때 해당 값을 기억하기 위한 변수
        int pre = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            arr[i] = new int[size];

            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            
            if (size == 1) {
                // size가 1이면 어차피 단일 선택 
                // (만약 바로 이전에도 단일 선택이었는데 같으면 무조건 불가능)
                if (pre == arr[i][0]) {
                    flag = true;
                    break;
                } else {
                    pre = arr[i][0];
                }
                continue;
            } else if (size == 2) {
                // size가 2인데 이전에 size가 1이었는데 2개중 겹치는게 있다면 size가 2더라도 단일 선택 (겹치니깐)
                // 따라서 단일 선택
                if (pre == arr[i][0]) {
                    pre = arr[i][1];
                    continue;
                } else if (pre == arr[i][1]) {
                    pre = arr[i][0];
                    continue;
                }
            }
            //단일 선택이 아니라면 이전에 무엇을 선택하든 상관없으므로 1~9의 수가 아닌 0으로 초기화
            pre = 0;
        }
        // 위의 경우에 걸리지 않으면 무조건 가능한 경우가 있음 이를 찾아야함.
        // 단일 선택에 걸리지 않는다면 무조건 가능하기 때문에 (바로 전날이랑만 다르면 됨)
        // 즉, 현재 선택할 수 있는 값이 바로 이전에 선택되지 않았기 때문에 
        // size가 2이상이며 이전 선택과 겹치지 않으면 [1 2] [1 2]더라도 서로 뒤바꿔서 선택하면 가능함
        if (!flag) {
            dfs(0, 0);
        }
        bw.write(isLive ? sb.toString() : String.valueOf(-1));
        bw.flush();
        bw.close();
    }

    private static void dfs(int pre, int depth) {
        if(isLive) return;
        if (depth == N) {
            isLive = true;
            for (int i = 0; i < N; i++) {
                sb.append(num[i]).append("\n");
            }
            return;
        }

        for (int i = 0; i < arr[depth].length; i++) {
            if(isLive) return;
            if (pre == arr[depth][i]) continue;
            num[depth] = arr[depth][i];
            dfs(arr[depth][i], depth + 1);
        }
    }
}