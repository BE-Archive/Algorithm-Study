import java.io.*;
import java.util.*;

public class Main {
    static int[] inOrder;
    static int[] postOrder;
    static Map<Integer, Integer> inOrderIndex = new HashMap<>();
    static StringBuilder preOrderResult = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        // 중위 순회
        inOrder = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        
        // 후위 순회
        postOrder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
        
        // 중위 순회 인덱스(inOrderIndex) 매핑
        for (int i = 0; i < N; i++) {
            inOrderIndex.put(inOrder[i], i);
        }
        
        /////////////////////////////////////////////////////////////
        findChild(0, N - 1, 0, N - 1);
        System.out.println(preOrderResult.toString().trim());
    }
    
    // 트리 탐색하는 재귀 함수
    static void findChild(int inLeft, int inRight, int postLeft, int postRight) {
        // 유효한 범위 체크
        if (inLeft > inRight) return;
        
        // 후위 순회의 마지막 원소가 루트
        int root = postOrder[postRight];
        preOrderResult.append(root).append(" ");
        
        int rootIndex = inOrderIndex.get(root);
        int offset = rootIndex - inLeft; // offset : 왼쪽 서브트리의 크기
        
        // 왼쪽 자식 탐색
        findChild(inLeft, rootIndex - 1, postLeft, postLeft + offset - 1);
        
        // 오른쪽 자식 탐색
        findChild(rootIndex + 1, inRight, postLeft + offset, postRight - 1);
    }
}