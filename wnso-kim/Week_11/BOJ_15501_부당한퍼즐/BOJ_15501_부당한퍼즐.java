import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ_15501_부당한퍼즐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N, input, input2
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");

        // 초기화, cmp
        int length = N*2-1;
        int[] cmp = new int[length];
        for(int i=0; i<length; i++){
            cmp[i] = Integer.parseInt(input[i%N]);
        }

        // 초기화 target
        int[] target = new int[N];
        for(int i=0; i<N; i++){
            target[i] = Integer.parseInt(input2[i]);
        }

        // 확인을 위한 초기값 세팅
        boolean ok = false;
        int startNumber = target[0];
        List<Integer> list = new LinkedList<>();
        for(int i=0; i<length; i++) if(cmp[i]==startNumber) list.add(i);

        // 1. 순차
        loop:
        for(int start: list){
            for(int i=start, j=0; i<start+N; i++,j++){
                if(i<0 || i>=length) continue loop;
                if(cmp[i] != target[j]) continue loop;
            }
            ok = true; 
            break;
        }

        // 2. 역순
        loop:
        for(int start: list){
            for(int i=start, j=0; i>start-N; i--,j++){
                if(i<0 || i>=length) continue loop;
                if(cmp[i] != target[j]) continue loop;
            }
            ok = true; 
            break; 
        }

        // 출력
        System.out.println(ok? "good puzzle": "bad puzzle");
    }
}
