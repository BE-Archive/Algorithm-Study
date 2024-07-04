package Week_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 소수 배열에서 소수만 리스트에 담기
        // 크기: (10^(N/2+1))의 제곱근
        // N이 5인 경우 0~999까지 소수 여부 체크 가능한 배열
        List<Integer> primeNumbers = new ArrayList<>();
        primeNumbers.add(2); primeNumbers.add(3); primeNumbers.add(5); primeNumbers.add(7);

        int max = (int)Math.pow(10, (N+1)/2);
        for(int num=11; num<max; num++){
            if(isPrime(num)) primeNumbers.add(num);
        }

        // 신기한 소수 찾기
        findSpecialPrimeNumber(2, 1, N, primeNumbers);
        findSpecialPrimeNumber(3, 1, N, primeNumbers);
        findSpecialPrimeNumber(5, 1, N, primeNumbers);
        findSpecialPrimeNumber(7, 1, N, primeNumbers);
    }

    static boolean isPrime(int num){
        for(int i=2; i*i<=num; i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    static void findSpecialPrimeNumber(int number, int index, int depth, List<Integer> primeNumbers){
        // 해당 숫자가 소수인지 판별
        for(Integer i: primeNumbers){
            // 소수인 경우 -> 판별 끝
            if(number==i) break;
            // 소수가 아닌 경우 -> 함수 종료
            else if(number%i==0) return;
        }

        // N자리수 인경우 -> 위에서 소수판별 했으므로, 출력하고 함수 종료
        if(index==depth){
            System.out.println(number);
            return;
        }

        // N자리수가 아닌경우 -> 수 붙이기(홀수만)
        for(int i=1; i<=9; i+=2){
            findSpecialPrimeNumber(number*10+i, index+1, depth, primeNumbers);
        }
    }
}

