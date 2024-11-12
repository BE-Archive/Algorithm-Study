import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N,L,W,H;

        N = sc.nextInt();
        L = sc.nextInt();
        W = sc.nextInt();
        H = sc.nextInt();

        double l=0, m=0, r=Math.min(L, Math.min(W,H));
        // (L/m)*(W/m)*(H/m)>=N인 m 최댓값
        while(l<r){
            m = (l+r)/2;
            if((long)(L/m) * (long)(W/m) * (long)(H/m) < N){ // 곱하므로 int 범위 넘어섬
                if(r==m) break;
                r=m; //N개 못넣음 -> m이 작아져야함
            }
            else{ //N개 넣을 수 있음 -> m의 최댓값 찾아야 함
                if(l==m) break;
                l=m;
            }
        }

        System.out.println(l);
    }
}