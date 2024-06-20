import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        number=new int[n];
        for (int i=0; i<n; i++) number[i]=i+1;

        do {
            sb.append(Arrays.toString(number)+"\n");
        } while(nextPermutation());

        System.out.println(sb.toString());
    }

    public static int n;
    public static int[] number;

    public static boolean nextPermutation(){
        int i=n-1;
        while (number[i-1]>=number[i]) {
            i--;
            if (i == 0) return false;
        }

        int j=n-1;
        while (number[i-1]>=number[j]) j--;

        int tmp=number[i-1];
        number[i-1]=number[j];
        number[j]=tmp;

        Arrays.sort(number, i, n);
        return true;
    }

}
