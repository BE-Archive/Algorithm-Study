import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931_회의실배정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력| N, 회의정보
        int N = Integer.parseInt(br.readLine());
        int[][] meetings = new int[N][2];

        for(int i=0; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(stk.nextToken());
            meetings[i][1] = Integer.parseInt(stk.nextToken());
        }

        // 정렬| 1. 종료시간(오름차순), 2. 시작시간(오름차순) 
        Arrays.sort(meetings, (m1,m2)->(m1[1]!=m2[1]? m1[1]-m2[1]: m1[0]-m2[0]));
        
        // 순회하며 회의실 카운드
        int answer = 0;
        int endTime = 0;
        for(int[] meeting: meetings){
            if(meeting[0] >= endTime){
                answer++;
                endTime = meeting[1];
            }
        }

        //출력
        System.out.println(answer);
    }
}
