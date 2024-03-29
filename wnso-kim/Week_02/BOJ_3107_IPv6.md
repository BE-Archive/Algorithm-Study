### 소스코드
[여기 클릭해 보세요!](https://github.com/BE-Archive/Algorithm-Study/blob/main/wnso-kim/Week_02/BOJ_3107_IPv6.java)

---
### 소요시간
1시간 20분

---
### 알고리즘
문자열 split

---
### 알아두어야할 점
> This method works as if by invoking the two-argument split method with the given expression and a limit argument of zero. `Trailing empty strings are therefore not included in the resulting array.`

자바에서 split() 메서드는, 구분자를 기준으로 뒤에 문자열이 없으면 크기가 1인 String[]에 구분자 앞의 문자열을 넣어 반환한다. <br>
```
"ABC::".split("::") 의 결과: {"ABC"}
"::ABC".split("::") 의 결과: {"","ABC"} 
"ABC".split("::") 의 결과: {"ABC"}
```

---
### 풀이
1. 기존 문자열을 `::` 기준으로 나눈다.
2. `문자열1` 과 `문자열2` 사이에 `0000`이 들어갈 횟수를 파악한다.
3. `문자열1` 을 복원한다.
4. `0000` 을 횟수만큼 추가한다.
5. `문자열2` 을 복원한다.

<br>


우선, 문자열 배열을 매개변수로 받아 IPv6로 복원하는 demux 메소드를 만든다.<br>
demux에 `문자열`을 `:`으로 나눠 전달하면 복원된 주소를 받을 수 있다.

``` java
static void demux(String[] strs) {
    for(String str: strs) {
        int zero = 4-str.length();
        
        for(int i=0; i<zero; i++)
            str = "0"+str;
        
        result.append(str+":");
    }
}
```

<br>

문자열을 `::` 기준으로 나누면 아래와 같은 경우가 된다.(문자열은 ':'을 포함)<br>
|축약된 IPv6 주소|'::' 기준 나눈 결과|출력해야하는 모양|
|:-:|:-:|:-:|
|문자열 :: 문자열|{문자열, 문자열}|문자열+0000+문자열|
|:: 문자열|{"", 문자열}|""+0000+문자열|
|문자열 ::|{문자열}|문자열+0000|
|문자열|{문자열}|문자열|
|::|{}|0000|

`::` 기준으로 나누었을 때, 배열 크기에 따라 출력해야하는 모양이 결정된다.
- 2인 경우: 2 가지
- 1인 경우: 2 가지
- 0인 경우: 1 가지

<br>

크기가 2인 경우, `문자열+0000+문자열`, `""+0000+문자열`로 출력해야하는 모양이 비슷하다.
1. demux()를 사용해 `문자열` 과 `""` 을 복원해 추가한다.
2. `0000`의 횟수를 파악해 추가한다.
    - `0000` 앞 문자열 그룹의 수와 뒤 문자열 그룹의 수를 이용한다.
3. demux()를 사용해 `문자열`을 복원해 추가한다.

<br>

크기가 1인 경우, `문자열+0000`, `문자열` 모양으로 출력해야한다.
1. `문자열+0000`인 경우 demux()를 사용하고, 그룹의 수를 파악해 `0000`을 추가한다.
2. `문자열` 인 경우 demux()를 사용한다.

<br>
크기가 0인 경우, `0000`을 8개 출력한다.

<br>

---
### 이상한 점
크기가 0인 경우, 즉 `::`인 경우를 고려하지 않고 제출해도 정답이라고 뜬다.
백준이 이상하다.
