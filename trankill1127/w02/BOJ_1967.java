import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1967 {

    static class Node {
        int id;
        int w;

        public Node(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static ArrayList<ArrayList<Node>> graphDescribe;
    static int graphLongest = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        //인접 리스트 생성
        graphDescribe = new ArrayList<>();
        for (int i = 0; i < n; i++) { //0 : 1번 노드의 인접 노드 리스트
            graphDescribe.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graphDescribe.get(x - 1).add(new Node(y, w));
        }

        getDistance(1);
        System.out.println(graphLongest);
    }

    static int getDistance(int nowId) {
        int longest1 = 0; //nowId를 중심으로 했을 때 가질 수 있는 가장 긴 경로의 길이 
        int longest2 = 0; //nowId를 중심으로 했을 때 가질 수 있는 두번째로 가장 긴 경로의 길이
        int longest; //nowId를 중심으로 했을 때 가질 수 있는 경로의 길이 

        if (!graphDescribe.get(nowId - 1).isEmpty()) { //자식 노드가 있을 때
            for (int i = 0; i < graphDescribe.get(nowId - 1).size(); i++) {
                longest = getDistance(graphDescribe.get(nowId - 1).get(i).id) + graphDescribe.get(nowId - 1).get(i).w;

                if (longest1 < longest) {
                    longest2 = longest1;
                    longest1 = longest;
                } else if (longest2 < longest) {
                    longest2 = longest;
                }
            }

            if (graphLongest<longest1+longest2) { //가장 긴 지름의 길이 업데이트 
                graphLongest=longest1+longest2;
            }
        }

        return longest1;
    }

}
