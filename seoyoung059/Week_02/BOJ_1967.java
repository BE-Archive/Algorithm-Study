package seoyoung059.Week_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static TreeNode[] arr;
    static TreeNode root;
    static LinkedList[] childArray;
    static int answer;
    static class TreeNode {
        int num;
        int parent;
        int weight;


        public TreeNode(int num, int parent, int weight){
            this.num = num;
            this.parent = parent;
            this.weight = weight;
        }

    }

    static int dfs(int currNode) {
        if (childArray[currNode].isEmpty())
            return arr[currNode].weight;
        int max = 0;
        int tmp=0;
        for (Object n: childArray[currNode]) {
            tmp = dfs((int)n);
            answer = Math.max(tmp+max, answer);
            max = Math.max(max, tmp);
        }
        return max + arr[currNode].weight;

    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new TreeNode[n+1];
        root = new TreeNode(1,0, 0);
        arr[1] = root;
        childArray = new LinkedList[n+1];
        for (int i = 1; i < n+1; i++) {
            childArray[i] = new LinkedList<Integer>();
        }
        int parent; int curr; int weight;

        for (int i = 2; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            parent = Integer.parseInt(st.nextToken());
            curr = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            arr[curr] = new TreeNode(curr,parent, weight);
            childArray[parent].offer(curr);
        }

        answer = 0;
        dfs(1);

        System.out.println(answer);
    }
}
