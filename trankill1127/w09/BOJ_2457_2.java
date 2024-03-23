import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2457_2 {

    public static class Flower{
        int start; //피는 날
        int end; //지는 날-1

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        List<Flower> fs = new ArrayList<>();
        for (int i=0; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int m= Integer.parseInt(st.nextToken());
            int d= Integer.parseInt(st.nextToken());
            int start = dateToSingleLine(m,d);
            m= Integer.parseInt(st.nextToken());
            d= Integer.parseInt(st.nextToken());
            if (d==1){
                m-=1;
                if (m==4||m==6||m==9||m==11)d=30;
                else if (m==2) d=28;
                else d=30;
            }
            int end = dateToSingleLine(m,d);
            fs.add(new Flower(start, end));
        }


    }

    //날짜를 편하게 처리하는 방법
    public static int dateToSingleLine(int m, int d){
        return m*100+d;
    }

}

/*
n개의 꽃
5/8 피고 6/13 진다면
피어있는 날: 5/8-6/13

4,6,9,11: 30
1,3,5,7,8,10,12: 31
2: 28

n개의 꽃들 중 다음의 두 조건을 만족하는 꽃들의 개수는?
-3/11~11/30까지 꽃이 피어있게
-피어있는 꽃의 수는 최소한으로
 */