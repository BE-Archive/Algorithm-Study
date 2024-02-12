
![alt text](image.png)

</br>

## 2750. 수 정렬하기

### 1. 삽입정렬

12544 KB / 144 ms

```java
public static void insertSort(int[] arr){
        int len = arr.length;
        for (int i=1; i<len; i++){
            for (int j=0; j<i; j++){
                if (arr[j]>arr[i]){
                    int tmp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
}
```

</br>

### 2. 선택정렬

12660 KB / 148 ms

```java
public static void selectSort(int[] arr){
        int len = arr.length;
        for (int i=0; i<len-1; i++){
            int minIdx=i;
            for (int j=i+1; j<len; j++){
                if (arr[minIdx]>arr[j]){
                    minIdx=j;
                }
            }
            int tmp=arr[i];
            arr[i]=arr[minIdx];
            arr[minIdx]=tmp;
        }
}
```

</br>

## 2751. 수 정렬하기2

### 1. 퀵정렬

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n= Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        quickSort(arr, 0, n-1);
        for (int an : arr)
            sb.append(an).append("\n");
        System.out.println(sb.toString());
    }

    public static void quickSort(int[] arr, int s, int e){
        if (s>=e) return;

        int pivot = divide(arr, s, e);
        quickSort(arr, s, pivot);
        quickSort(arr, pivot+1, e);
    }

    public static int divide(int[] arr, int s, int e){
        int left = s;
        int right = e;
        int pivot = arr[(s+e)/2];
        while (true){
            while (arr[left]<pivot){
                left++;
            }
            while (arr[right]>pivot && left<=right) {
                right--;
            }

            if (left>=right) return right;

            int tmp=arr[left];
            arr[left]=arr[right];
            arr[right]=tmp;
        }
    }
}
```

### ✔️ 퀵정렬로 2751. 수 정렬하기2를 통과하지 못한 이유는?

퀵정렬은 최악의 경우 `O(n^2)` 의 성능을 가질 수 있기 때문이다. 우리가 오름차순 정렬을 목적으로 하는데 이미 그렇게 정렬되어 있는 경우가 대표적이다.

</br>

### 2. 합병정렬

224096 KB   /   932 ms 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n= Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        divide(0, n);
        for (int sn : arr){
            sb.append(sn).append("\n");
        }
        System.out.println(sb.toString());
    }

    //합병정렬
    public static void divide(int s, int e){
        if ((e-s)==1) return;

        int mid = (s+e)/2;
        divide(s, mid);
        divide(mid, e);
        merge(s, mid, e);
    }

    public static void merge(int s, int mid, int e) {
        int[] tmp = new int[e-s];
        int idx = 0;
        int ls=s, rs=mid;
        while (ls<mid && rs<e){
            if (arr[ls]<arr[rs]) {
                tmp[idx]=arr[ls];
                ls++;
            }
            else {
                tmp[idx]=arr[rs];
                rs++;
            }
            idx++;
        }

        for (int i=0; i<tmp.length; i++){
            arr[s+i]=tmp[i];
        }
    }
}

```

### ✔️ 퀵정렬과 합병정렬의 차이는 뭘까?

둘의 공통점은 분할-정복 알고리즘을 기반으로 정렬한다는 것이다. 다만, **퀵정렬은 Pivot보다 작은 값과 큰 값으로 배열을 나눈다면 합병정렬은 무조건 절반으로 나눈다는 점에서 차이점을 가진다.**

</br>


### 3. 계수정렬

138996 KB / 764 ms

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n= Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] cnt = new int[2000001];
        for (int i=0; i<n; i++){
            arr[i]=Integer.parseInt(br.readLine());
            cnt[arr[i]+1000000]++;
        }

        for (int i=1; i<2000001; i++){
            cnt[i]+=cnt[i-1];
        }

        int[] sorted = new int[n];
        for (int j=n-1; j>=0; j--){
            cnt[arr[j]+1000000]--;
            sorted[cnt[arr[j]+1000000]]=arr[j];
        }

        for (int sn : sorted)
            sb.append(sn).append("\n");
        System.out.println(sb.toString());
    }
}
```