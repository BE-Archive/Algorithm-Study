import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        char[][] land = new char[n][n];
        for (int i=0; i<n; i++){
            String s = br.readLine().trim();
            for (int j=0; j<n; j++){
                land[i][j]=s.charAt(j);
            }
        }
    }
}

/*
톹나무)
중심점의 좌표
무슨 방향으로 놓여있는지

이동)
상, 하, 좌, 우: 다음 위치를 잡고 이동할 수 있는지 확인, 없으면 이동
회전: 현재 기준 3*3 영역에 통나무가 없는지 확인, 없으면 이동
* 회전의 경우에는 4번 회전하면 그 위치 그대로라서 이걸 따로 관리해줘야 할 것 같음

visited[x][y][dirId]
 */