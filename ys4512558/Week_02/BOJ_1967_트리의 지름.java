import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BOJ1967.prob1967();
    }
}

class BOJ1967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int max = 0;

    public static void prob1967() throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 인접 리스트 생성
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(parent).add(new Node(child, weight));
        }

        dfs(1);
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int currentNode) {
        ArrayList<Node> currentList = list.get(currentNode);

        int firstWeight = 0;
        int secondWeight = 0;
        for (Node node : currentList) {
            int weight = dfs(node.idx) + node.weight;
            if (firstWeight < weight) {
                secondWeight = firstWeight;
                firstWeight = weight;
            } else if (secondWeight < weight) {
                secondWeight = weight;
            }
        }
        max = Math.max(max, firstWeight + secondWeight);
        // 리프 노드는 0을 리턴하게 됨.
        return firstWeight;
    }
}

class Node {
    int idx;
    int weight;

    public Node(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
    }
}

/**
 * 메모리 초과 코드
 */

class BOJ1967_first {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static boolean isVisited[];
    static Map<Integer, ArrayList<TreeNode>> tree;
    static int max = 0;
    static int[] res;

    public static void prob1967() throws IOException {
        int N = Integer.parseInt(br.readLine());

        isVisited = new boolean[N + 1];
        tree = new TreeMap<>();
        res = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (tree.get(parent) == null) {
                tree.put(parent, new ArrayList<>());
            }
            if (tree.get(child) == null) {
                tree.put(child, new ArrayList<>());
            }

            tree.get(parent).add(new TreeNode(child, cost));
            tree.get(child).add(new TreeNode(parent, cost));
        }

        for (int i = 1; i <= N; i++) {
            res = new int[N + 1];
            for (int j = 0; j < tree.get(i).size(); j++) {
                if (i > tree.get(i).get(j).node) {
                    continue;
                }
                res[i] += dfs(tree.get(i).get(j).node, tree.get(i).get(j).cost);
            }
            max = Math.max(max, res[i]);
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int cur, int cost) {
        ArrayList<TreeNode> list = tree.get(cur);
        if (list.size() == 1) {
            return cost;
        }
        for (int i = 0; i < tree.get(cur).size(); i++) {
            TreeNode next = list.get(i);
            // 역류 방지
            if (list.get(i).node < cur) {
                continue;
            }
            int maxResult = dfs(next.node, next.cost);
            res[cur] = Math.max(res[cur], maxResult);
        }
        return res[cur] + cost;
    }
}

class TreeNode {
    int node;
    int cost;

    public TreeNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}