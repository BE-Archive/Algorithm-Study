## 1차 풀이

**HashMap (67452 KB / 564 ms)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n =Integer.parseInt(st.nextToken());
        int k =Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i=0; i<n; i++) arr[i]=Integer.parseInt(st.nextToken());

        int maxLen=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int j=0;
        for (int i=0; i<n; i++){
            if (!map.containsKey(arr[i])) map.put(arr[i], 1);
            else map.replace(arr[i], map.get(arr[i])+1);

            while (map.get(arr[i])>k){
                map.replace(arr[j], map.get(arr[j])-1);
                j++;
            }
            maxLen = Math.max(maxLen, i-j+1);
        }

        System.out.println(maxLen);
    }
}
```

</br>

## 2차 풀이

**int 배열 (37352 KB / 288 ms)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n =Integer.parseInt(st.nextToken());
        int k =Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i=0; i<n; i++) arr[i]=Integer.parseInt(st.nextToken());

        int maxLen=0;
        int[] map = new int[100001];
        int j=0;
        for (int i=0; i<n; i++){
            map[arr[i]]++;

            while (map[arr[i]]>k){
                map[arr[j]]--;
                j++;
            }
            maxLen = Math.max(maxLen, i-j+1);
        }

        System.out.println(maxLen);
    }
}
```

</br>

### ✔️ 왜 이런 성능 차이가 발생했을까?

HashMap은 해시 함수를 통해 키를 해시 코드로 변환하고, 이것을 통해서 value에 접근한다. 이 점이 성능에 불리하게 작용할 수 있다.