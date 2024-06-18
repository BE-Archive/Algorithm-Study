import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n =Integer.parseInt(st.nextToken()); //벨트 위 접시의 수
        int d =Integer.parseInt(st.nextToken()); //초밥 종류
        int k =Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 개수
        int c =Integer.parseInt(st.nextToken()); //쿠폰 번호

        int[] belt = new int[n];
        for (int i=0; i<n; i++)
            belt[i]=Integer.parseInt(br.readLine().trim());

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<k; i++){
            if (map.get(belt[i])==null) map.put(belt[i], 1);
            else map.put(belt[i], map.get(belt[i])+1);
        }
        int maxDiversity = map.size();
        if (map.get(c)==null) maxDiversity++;

        int diversity=0;
        for (int i=0; i<n; i++){
            //삭제
            if (map.get(belt[i])==1) map.remove(belt[i]);
            else if (map.get(belt[i])>1) map.put(belt[i], map.get(belt[i])-1);
            //추가
            int addIdx=(i+k)%n;
            if (map.get(belt[addIdx])==null) map.put(belt[addIdx], 1);
            else map.put(belt[addIdx], map.get(belt[addIdx])+1 );

            diversity=map.size();
            if (map.get(c)==null) diversity++;
            if (diversity>maxDiversity) maxDiversity=diversity;
        }

        System.out.println(maxDiversity);
    }
}

