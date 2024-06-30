import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ22866 {
    static int[][] result;
    static int[] tower;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        tower = new int[N + 1];
        int[][] sorted = new int[N][2];
        // 높이를 기준으로 정렬한 후
        // 큰 높이를 가진 탑부터 하나씩 갱신
        // 왼쪽, 오른쪽을 나눠서 해당 탑의 왼쪽의 가장 큰 타워의 인덱스 저장
        // 이를 통해 재귀적으로 탐색하며 왼, 오 가 0이면 해당 탑이 방향 기준 가장 큰 탑이므로 더 이상 진행 X
        result = new int[N + 1][3]; // 0 : 왼쪽 개수, 1 : 오른쪽 개수, 2 : 가장 가까운 idx,
        for (int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
            sorted[i - 1][0] = i;
            sorted[i - 1][1] = tower[i];
        }

        Arrays.sort(sorted, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        for (int i = 0; i < N; i++) {
            find(sorted[i][0], sorted[i][1]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int sum = result[i][0] + result[i][1];
            sb.append(sum);
            if (sum != 0){
                sb.append(" ")
                  .append(result[i][2]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void find(int current, int h){
        int left = -1;
        //왼쪽에서 나보다 큰 가장 가까운 인덱스를 찾는다.
        for (int i = current - 1; i >= 1; i--) {
            if (tower[i] > h) {
                left = i;
                break;
            }
        }

        //오른쪽에서 나보다 큰 가장 가까운 인덱스를 찾는다.
        int right = -1;
        for (int i = current + 1; i <= N; i++) {
            if (tower[i] > h) {
                right = i;
                break;
            }
        }

        int leftCnt = 0;
        if(left != -1){ //나보다 큰 왼쪽이 있으면
            //정렬된 상태이므로 무조건 현재 위치보다 높은 탑 개수를 가지고 있음
            //큰 탑을 먼저 갱신했기 때문에.
            leftCnt = result[left][0] + 1;
        }

        int rightCnt = 0;
        if(right != -1){ //나보다 큰 오른쪽이 있으면
            rightCnt = result[right][1] + 1;
        }

        result[current][0] = leftCnt;
        result[current][1] = rightCnt;
        //왼쪽, 오른쪽 모두 나보다 큰게 없는 경우
        if(result[current][0] == 0 && result[current][1] == 0) return;
        //왼, 오 가장 가까운 것 찾기 같으면 왼쪽
        int diffLeft = left != -1 ? current - left : Integer.MAX_VALUE;
        int diffRight = right != -1 ? right - current : Integer.MAX_VALUE;
        result[current][2] = diffLeft <= diffRight ? left : right;
    }
}