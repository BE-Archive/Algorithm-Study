## 1차 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int size;
    static int[] arrACnt;
    static int[] arrBCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int[] a = new int[sizeA];
        int[] b = new int[sizeB];
        for (int i=0; i<sizeA; i++) {
            a[i]=Integer.parseInt(br.readLine());
        }
        for (int i=0; i<sizeB; i++) {
            b[i]=Integer.parseInt(br.readLine());
        }

        arrACnt=new int[size+1]; arrACnt[0]=1; 
        arrBCnt=new int[size+1]; arrBCnt[0]=1;
        cntPossible(a, arrACnt, size);
        cntPossible(b, arrBCnt, size);

        int cnt=0;
        for (int i=0; i<=size; i++){
            cnt+=arrACnt[i]*arrBCnt[size-i];
        }
        System.out.println(cnt);
    }

    public static void cntPossible(int[] arr, int[] memo, int target){
        int baseLen = arr.length;
        int sumCnt=0;
        for (int i=1; i<=target; i++){
            sumCnt=0;
            for (int j=1; j<=baseLen; j++){
                sumCnt+=lenPossible(arr, j, i);
            }
            memo[i]=sumCnt;
        }
    }

    public static int lenPossible(int[] arr, int len, int target){
        int baseLen = arr.length;
        int cnt=0;
        for (int i=0; i<baseLen; i++){
            int sum=0;
            for (int j=0; j<len; j++){
                if (i+j>=baseLen) sum+=arr[(i+j)%baseLen];
                else sum+=arr[i+j];

                if (sum>target) break;
            }

            if (sum==target) cnt++;
        }
        return cnt;
    }
}
```

## 2차 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

     static int size;
    static int[] arrACnt;
    static int[] arrBCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int[] a = new int[sizeA];
        int[] b = new int[sizeB];

        int maxA=0;
        for (int i=0; i<sizeA; i++) {
            a[i]=Integer.parseInt(br.readLine());
            maxA+=a[i];
        }
        int maxB=0;
        for (int i=0; i<sizeB; i++) {
            b[i]=Integer.parseInt(br.readLine());
            maxB+=b[i];
        }

        arrACnt=new int[Math.max(size, maxA)+1]; arrACnt[0]=1; arrACnt[maxA]=1; 
        arrBCnt=new int[Math.max(size, maxB)+1]; arrBCnt[0]=1; arrACnt[maxB]=1;

        cntPossible(a, arrACnt, size);
        cntPossible(b, arrBCnt, size);

        int cnt=0;
        for (int i=0; i<=size; i++){
            cnt+=arrACnt[i]*arrBCnt[size-i];
        }
        System.out.println(cnt);
    }

    public static void cntPossible(int[] arr, int[] memo, int target){
        int baseLen = arr.length;
        for (int i=0; i<baseLen; i++){ //시작 인덱스
            int sum=0;
            for (int j=1; j<baseLen; j++){ //선택하는 피자 조각
                int sum_j = arr[(i + j) % baseLen];
                if(sum + sum_j > size) break;
                sum += sum_j;
                memo[sum]++;
            }
        }
    }
}
```

- 함수를 분리하면서 쓸데없이 중복 연산이 늘어났다.
- 아래의 예외 케이스를 어떻게 처리해야 하는지 몰랐다.
    
    ```java
    6
    3 3
    1
    1
    1
    1
    1
    1
    ```
    
    j를 전체 범위로 잡으면 이 경우에 memo[3]이 a배열, b배열 각각의 경우에 3으로 카운트된다. 그래서 나중에 main에서 최종 결과로 9가 나오게 되는 것이다. 이걸 해결하는 방법은 아래와 같다.
    
    ```java
    arrACnt=new int[Math.max(size, maxA)+1]; arrACnt[0]=1; arrACnt[maxA]=1; 
    arrBCnt=new int[Math.max(size, maxB)+1]; arrBCnt[0]=1; arrACnt[maxB]=1;
    
    for (int j=1; j<baseLen; j++){ //선택하는 피자 조각
         int sum_j = arr[(i + j) % baseLen];
         if(sum + sum_j > size) break;
         sum += sum_j;
         memo[sum]++;
    }
    ```
    
    위의 두 줄을 통해서 a, b 각각의 배열을 모두 더한 값을 갖는 횟수가 1로 고정할 수 있고, 아래에 위치한 for문에서는 모든 값을 더한 경우는 아예 세지 않도록 j의 범위를 1 줄임으로써 예외 케이스를 처리할 수 있다.