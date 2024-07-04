import java.io.*;
import java.util.*;

public class Main {

    static int DAYS[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static int trans(final int month, final int day) {
        return DAYS[month - 1] + day;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        List<int[]> range = new ArrayList<int[]>();

        for (int i = 1; i < 13; ++i) {
            DAYS[i] += DAYS[i - 1];
        }
        int ts = trans(3, 1);
        int te = trans(11, 30);
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = trans(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int b = trans(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            range.add(new int[]{a, b});
        }

        Collections.sort(range, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int start, end, max_end = -1;

        // ts 보다 작은 start를 가지는 것들 중에서 가장 큰 end 값 가져오기
        for (int i = 0; i < N; ++i) {
            
            if (te < ts) break;

            max_end = -1;
            for (int j = i; j < N; ++j) {
                start = range.get(j)[0];
                end = range.get(j)[1];

                if (start > ts) break;

                if (max_end < end) max_end = end;
                i = j;

            }

            if (max_end != -1) {
                ++answer;
                ts = max_end;
            } else {
                answer = 0;
                break;
            }
        }


        System.out.print(ts < 335 ? 0 : answer);
    }
}
