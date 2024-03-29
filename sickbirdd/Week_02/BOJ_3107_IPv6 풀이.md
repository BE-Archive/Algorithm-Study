## 소스 코드
[링크](https://www.acmicpc.net/source/72830699)

## 소모 시간
30분

## 알고리즘
구현, 문자열

## 풀이

문제에는 다음과 같은 축약 조건 2가지가 존재한다.

* 각 그룹의 앞자리의 0의 전체 또는 일부를 생략 할 수 있다.
* 0으로만 이루어져 있는 그룹이 있을 경우 그 중 한 개 이상 연속된 그룹을 하나 골라 콜론 2개(`::`)로 바꿀 수 있다.

**1. 입력받은 ipv6 문자열을 그룹 단위로 구별**
   * `String`의 `split` 메서드 활용 (`:`를 구분자로 사용)
   * `::`의 경우, 그냥 `split` 사용 시 축약된 그룹 구별 불가
   * 따라서 `::`을 `:*:`로 변경, `split` 사용 시 `*` 문자로 분리됨.

**2. 그룹으로 구분된 문자열 검사**
  * `0`이 생략된 경우
     * 문자열의 길이가 `4` 미만일 때, `0`이 생략된 경우라고 생각 가능
     * `4 - 문자열의 길이`만큼 문자열 앞에다가 `0`을 붙여줌.
  * **0으로 이루어진 그룹이 축약된 경우**
    * 문자열의 값으로 `*`가 온 경우 해당 위치에 축약된 그룹이 있다고 생각
    * `0으로 이루어진 그룹의 수 = 8 - 구별된 그룹의 수 + 1`
    * 0으로 이루어진 그룹의 수만큼 `0000` 추가
  * 두 경우 모두 `StringBuilder`를 활용하여 `append`하고 이후 한 번에 출력
### 유의사항
* 결과를 만들 때, 문자열 사이마다 `:`를 붙이는 경우를 유의할 것
* 맨 마지막 문자열에는 `:`를 붙이면 안됨.