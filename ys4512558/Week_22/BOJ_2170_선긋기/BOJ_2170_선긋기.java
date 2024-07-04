import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2170 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Line> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Line(start, end));
        }

        Line first = pq.poll();
        int start = first.start;
        int end = first.end;
        int sum = 0;
        while (!pq.isEmpty()) {
            Line line = pq.poll();
            //이전과 이어지지 않으면
            if (end < line.start) {
                //이전까지의 길이의 합을 더해줌
                sum += (end - start);
                //현재 원소부터 다시 시작
                start = line.start;
                end = line.end;
                continue;
            }
            //이어지면 뒤만 갱신 (앞을 기준으로 우선순위를 줬기 때문)
            end = Math.max(end, line.end);
        }
        sum += end - start; //마지막 라인 길이 더해주기
        System.out.println(sum);
    }
}

class Line implements Comparable<Line>{
    int start, end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        if(this.start == o.start) return Integer.compare(this.end, o.end);
        return Integer.compare(this.start, o.start);
    }
}
