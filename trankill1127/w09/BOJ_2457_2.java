import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2457_2 {

    public static class Flower implements Comparable<Flower>{
        int start; //피는 날
        int end; //지는 날

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start==o.start){
                return o.end-this.end;
            }
            else {
                return this.start-o.start;
            }
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        List<Flower> fs = new ArrayList<>();
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = dateToSingleLine(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            int end = dateToSingleLine(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            fs.add(new Flower(start, end));
        }
        Collections.sort(fs);

        int cnt=0;
        int candidate=0;
        for (int i=0; i<n; i++){

            if (i==0) {
                int tmp = 0;
                for (int j=1; j<n; j++) {
                    if (fs.get(j).start <= 301 && fs.get(tmp).end < fs.get(j).end) {
                        tmp = j;
                    }
                }
                if (301<fs.get(tmp).start) break;
                i=tmp;
                cnt++;
                //System.out.println(tmp);
            }

            candidate=i;
            for (int j=i+1; j<n; j++){
                if (fs.get(i).end<fs.get(j).start) break;
                if (1201<=fs.get(candidate).end) break;
                if (fs.get(candidate).end<fs.get(j).end) candidate=j;
            }

            if (candidate!=i) {
                //System.out.println("c: "+candidate);
                i=candidate-1;
                cnt++;
                if (1201<=fs.get(candidate).end) break;
            }
            else break;
        }

        if (cnt>0 && 1201<=fs.get(candidate).end) System.out.println(cnt);
        else System.out.println(0);

    }

    //날짜를 편하게 처리하는 방법
    public static int dateToSingleLine(int m, int d){
        return m*100+d;
    }

}