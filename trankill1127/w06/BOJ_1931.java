import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931 {

    public static class Meeting implements Comparable<Meeting>{
        int s;
        int e;
        Meeting(int s,int e){
            this.s=s;
            this.e=e;
        }
        public String toString(){
            return s+"~"+e;
        }
        @Override
        public int compareTo(Meeting o) {
            if (this.e==o.e){
                return this.s-o.s;
            }
            return this.e-o.e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());

        StringTokenizer st;
        Meeting[] timeList = new Meeting[n];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            timeList[i]=new Meeting(s,e);
        }

        Arrays.sort(timeList);

        int cnt=0;
        int befE=0;
        for (int i=0; i<n; i++) {
            if (befE <= timeList[i].s) {
                befE=timeList[i].e;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}