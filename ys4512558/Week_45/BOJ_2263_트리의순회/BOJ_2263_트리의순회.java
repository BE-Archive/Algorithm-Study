import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int index = 1;
    static int[] tree;
    static int[] inOrder;
    static int[] postOrder;

    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new int[N + 1];

        inOrder = new int[N + 1];
        postOrder = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(N, 1);
        preOrder(1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void preOrder(int idx) {
        if(idx > N) return;
        sb.append(tree[idx] + " ");
        preOrder(idx * 2);
        preOrder(idx * 2 + 1);
    }

    /**
     * PostOrder를 기준으로 트리를 만들기
     * @param n
     * @param idx
     */
    private static void makeTree(int n, int idx) {
        if (n == 1) {
            tree[idx] = postOrder[index++];
            return;
        }

        Pair overCount = overCount(n);
        int cnt = (n - 1 - overCount.sum()) / 2; //N에서 루트 제외 초과 노드 개수 제외
        int l = cnt + overCount.l; //왼쪽에 초과된 만큼 추가
        int r = cnt + overCount.r; //오른쪽에 초과된 만큼 추가

        //왼쪽
        makeTree(l, idx * 2);
        //오른쪽
        makeTree(r, idx * 2 + 1);
        //중앙
        tree[idx] = postOrder[index++];
    }

    private static Pair overCount(int n) {
        int exp = 1;
        int pow = 1;
        while (pow < n) {
            //몇층인지 (2^exp) - 1 은 해당 층이 최대로 찼을 때 가능한 노드 개수
            pow = (int) Math.pow(2, ++exp) - 1;
        }

        //해당 층을 다 채우려면 더 있어야 할 수 ex pow = 15, n = 13 이면 2개 더 있으면 포화 이진 트리
        // 구하고자 하는 값은 층 - 1의 최대 노드 수에 몇개가 더 추가되었는지를 사용할 것이기 때문에 이를 구해야함
        //exp - 1배수 -1 (현재 트리의 층 - 1)의 최대 노드 개수를 n에서 빼면 몇개가 더 추가된 상태인지 알 수 있음

        if(pow == n) return new Pair(0, 0);
        int overCount = n - ((int) Math.pow(2, exp - 1) - 1); //초과 개수
        //초과 개수도 반 이상이면 왼/오 나눠야함 ex) n = 12이면 2^3 - 1 = 7 <= 12 <= 2^4 - 1 = 15
        //이때 왼쪽 리프 4개 오른쪽 리프 1개 이렇게 나눠야함 반으로 갈라서 작으면 모두 왼쪽 갈라서 많으면 오른쪽 나눠주기
        int diff = ((int) Math.pow(2, exp) - (int) Math.pow(2, exp - 1)) / 2;
        int l = Math.min(overCount, diff); //최대치는 반
        int r = overCount - l; //초과된 개수 - 왼쪽에 준 개수
        return new Pair(l, r);
    }
}

class Pair {
    int l, r;

    public Pair(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public int sum() {
        return l + r;
    }
}