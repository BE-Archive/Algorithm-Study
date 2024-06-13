import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1661_다솜이의신발가게 {

    static int N, P;
    static double[][] prices;
    static double tmp;
    static double answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        P = Integer.parseInt(stk.nextToken());
        
        prices = new double[N][3];
        tmp = (double)P;
        double sum = 0;

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            prices[i][0] = Double.parseDouble(stk.nextToken());
            prices[i][1] = Double.parseDouble(stk.nextToken());
            prices[i][2] = prices[i][0] / prices[i][1]; 

            sum += prices[i][0];
            tmp *= (100-prices[i][1])/100;
        }

        Arrays.sort(prices, (p1, p2) -> (Double.compare(p2[2], p1[2])));
        answer = Math.min(P, tmp + sum);


        for(double[] price: prices){
            tmp = tmp*100/(100-price[1]);
            sum = sum - price[0];

            answer = Math.min(answer, tmp + sum);
        }

        System.out.println(answer);
    }
}