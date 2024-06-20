import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1661 {
    public static List<Integer> one = new ArrayList<Integer>();
    public static List<Integer> two = new ArrayList<Integer>();
    public static List<Integer> three = new ArrayList<Integer>();

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

        int[] accOne = new int[one.size()+1];
        int[] accTwo = new int[two.size()+1];
        int[] accThree = new int[three.size()+1];
        for (int i=0; i<one.size(); i++){
            accOne[i+1]=accOne[i]+one.get(i);
        }
        for (int i=0; i<two.size(); i++){
            accTwo[i+1]=accTwo[i]+two.get(i);
        }
        for (int i=0; i<three.size(); i++){
            accThree[i+1]=accThree[i]+three.get(i);
        }

        double finalPrice = p;
        for (int i=0; i<=one.size(); i++){
            for (int j=0; j<=two.size(); j++){
                for (int k=0; k<=three.size(); k++){
                    finalPrice = Math.min(
                            finalPrice, p*Math.pow(0.99, i)*Math.pow(0.98, j)*Math.pow(0.97, k)
                            +accOne[i]
                            +accTwo[j]
                            +accThree[k]);
                }
            }
        }

        System.out.println(finalPrice);
    }

}
