import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1614_영식이의_손가락 {

    static int finger, count;
    static int[][] songarack = {
        {0, 0},
        {8, 8},
        {6, 2},
        {4, 4},
        {2, 6},
        {8, 8}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        finger = Integer.parseInt(br.readLine());
        count = Integer.parseInt(br.readLine());

        long answer = finger;
        answer += songarack[finger][0] * (count/2l + (count%2==1? 1: 0));
        answer += songarack[finger][1] * (count/2l);

        System.out.println(answer-1);
    }
}