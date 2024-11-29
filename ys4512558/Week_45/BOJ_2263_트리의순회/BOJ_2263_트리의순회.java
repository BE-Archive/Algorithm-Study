import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] preOrder, inOrder, postOrder, inOrderIndex;

    static StringBuilder sb = new StringBuilder();

    static int N, index;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        index = 1;

        preOrder = new int[N + 1];
        inOrder = new int[N + 1];
        postOrder = new int[N + 1];
        inOrderIndex = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex[inOrder[i]] = i; //inOrder의 i번째 인덱스의 값의 인덱스 inOrder[0] = 1이면 1의 인덱스가 0이라는 것을 인덱싱
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        preOrder(1, N, 1, N);

        for (int i = 1; i <= N; i++) {
            sb.append(preOrder[i] + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void preOrder(int is, int ie, int ps, int pe) {
        if(is > ie || ps > pe) return;
        preOrder[index++] = postOrder[pe];

        int root = inOrderIndex[postOrder[pe]]; //인오더의 해당 값이 어떤 인덱스인지 바로 찾기
        //루트 기준으로 반가르기
        //왼쪽 inOrder(시작 ~ 루트 바로전), postOrder(시작 ~ 인오더로 구한 왼쪽 서브트리 개수 == 포스트오더 왼쪽 서브트리 개수)
        //따라서, 개수 = ie - is = root - 1 - is
        preOrder(is, root - 1, ps, ps + (root - 1 - is));
        //오른쪽 inOrder(루트 + 1 ~ 끝), postOrder(루트 + 1 (왼쪽 서브트리 기준 + 1) ~ 끝 - 1(루트 제외))
        preOrder(root + 1, ie, ps + (root - 1 - is) + 1, pe - 1);
    }
}