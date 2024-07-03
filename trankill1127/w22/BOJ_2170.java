import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine().trim());
        List<int[]> intervals = new ArrayList<>();
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            intervals.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int answer=0;
        int[] now = intervals.get(0);
        for (int i=1; i<n; i++){
            if ( now[0]<=intervals.get(i)[0] && intervals.get(i)[0]<=now[1] ) {
                now[1]=Math.max(now[1], intervals.get(i)[1]);
            }
            else {
                answer+=(now[1]-now[0]);
                now=intervals.get(i);
            }
        }
        answer+=(now[1]-now[0]);
        System.out.println(answer);

    }
}