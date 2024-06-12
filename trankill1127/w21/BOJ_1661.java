import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1661 {

    public static LinkedList<Integer> one = new LinkedList<Integer>();
    public static LinkedList<Integer> two = new LinkedList<Integer>();
    public static LinkedList<Integer> three = new LinkedList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        double p = Integer.parseInt(st.nextToken());
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            int price = Integer.parseInt(st.nextToken());
            int discount = Integer.parseInt(st.nextToken());
            if (discount==1) one.add(price);
            else if (discount==2) two.add(price);
            else three.add(price);
        }

        Collections.sort(one);
        Collections.sort(two);
        Collections.sort(three);

        double finalPrice = p;
        for (int i=0; i<=one.size(); i++){
            for (int j=0; j<=two.size(); j++){
                for (int k=0; k<=three.size(); k++){
                    finalPrice = Math.min(finalPrice, p*Math.pow(0.99, i)*Math.pow(0.98, j)*Math.pow(0.97, k)+additional(i, j, k));
                }
            }
        }
        System.out.println(finalPrice);
    }

    public static int additional(int ii, int jj, int kk) {
        int tot=0;
        for (int i=0; i<ii; i++){
            tot+=one.get(i);
        }
        for (int i=0; i<jj; i++){
            tot+=two.get(i);
        }
        for (int i=0; i<kk; i++){
            tot+=three.get(i);
        }
        return tot;
    }
}
