import java.io.*;
import java.util.*;



public class Main {

    static boolean visited[];
    static int to[];
    static Set<Integer> picks = new TreeSet<>();

    private static void findCircle(int index) {

        // System.out.println("findCircle 시작 : index is " + index);

        Set<Integer> set = new HashSet<>();
        
        int current = index;
        int next = to[index];
        set.add(current);
        visited[current] = true;

        // 1. 순환지점 찾기
        while (set.contains(next) == false) {
            // 이미 방문한 곳이라면
            if (visited[next]) {
                return;
            }
            current = next;
            next = to[next];
            set.add(current);
            // 방문 처리
            visited[current] = true;
        }
        
        // System.out.println();
        // System.out.println("current point is : " + current);
        // System.out.println("next is : "  + next);

        // 순환지점은 current
        picks.add(current);
        while (next != current) {
            picks.add(next);
            next = to[next];
        }
        
    }
    
    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        to = new int[N + 1];

        for (int n = 1; n <= N; n++) {
            to[n] = Integer.parseInt(br.readLine());
        }
        
        // System.out.println();
        // for (int i : to) {
        //     System.out.print(i + " ");
        // }
        // System.out.println("\n=====================");

        
        for (int n = 1; n <= N; n++) {
            if (visited[n] == false) {
                findCircle(n);
            }
        }

        sb.append(picks.size()).append("\n");
        for (int p : picks) {
            sb.append(p).append("\n");
        }

        System.out.print(sb);
    }

}
