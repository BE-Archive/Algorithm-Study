import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2457_2 {

    public static class Flower implements Comparable<Flower> {
        int start; //피는 날
        int end; //지는 날

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - this.end;
            } else {
                return this.start - o.start;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        List<Flower> fs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = dateToSingleLine(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int end = dateToSingleLine(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            fs.add(new Flower(start, end));
        }

        //피는 날 오름차순, 피는 날이 같다면 지는 날 내림차순으로 정렬
        Collections.sort(fs);

        //첫번째로 피는 꽃을 결정한다.
        int prevIdx = -1;
        int i = 0;
        while (i < n) {
            //3월 1일 전에 피면서
            if (301 < fs.get(i).start) break;
            //가장 늦게 지는 꽃을 첫번째로 피는 꽃을 선택한다.
            if (prevIdx == -1 || fs.get(prevIdx).end < fs.get(i).end) prevIdx = i;
            i++;
        }
        if (prevIdx == -1) { //3월 1일 전에 피는 꽃이 없는 경우
            System.out.println(0);
            return;
        }
        int cnt = 1; //3월 1일 전에 피는 꽃이 있는 경우

        //이제 다음 꽃들을 결정한다.
        int nowIdx=-1;
        //직전 꽃이 이미 12월 1일 이후에 진다면 더 이상 꽃을 선택하지 않는다.
        while (fs.get(prevIdx).end < 1201) {
            int j = prevIdx+1;
            while (j<n) {
                //직전 꽃이 지는 날보다 일찍 피면서
                if (fs.get(prevIdx).end<fs.get(j).start) break;
                //더 늦게 지는 꽃을 선택한다.
                if (nowIdx==-1 || fs.get(nowIdx).end < fs.get(j).end) nowIdx = j;
                j++;
            }

            //조건을 만족하는 꽃이 없으면
            if (nowIdx==-1) break;

            //다음 꽃을 선택하기 위해 값을 정리해준다.
            prevIdx = nowIdx;
            nowIdx=-1;
            cnt++;
        }

        //마지막 꽃이 12월 1일 전에 지는 경우
        if (fs.get(prevIdx).end<1201) System.out.println(0);
        //이후에 지는 경우
        else System.out.println(cnt);
    }

    public static int dateToSingleLine(int m, int d) {
        return m * 100 + d;
    }

}