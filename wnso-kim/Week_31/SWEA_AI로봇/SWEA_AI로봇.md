## 소스코드
[여기 클릭해 보세요!](https://github.com/BE-Archive/Algorithm-Study/blob/main/wnso-kim/Week_31/SWEA_AI로봇/SWEA_AI로봇.java)

## 소요시간
`2시간 30분`

## 알고리즘
`자료구조`, `우선순위 큐`

## 풀이
**로봇 상태**와 **우선순위 큐(min, max)** 에서의 로봇 상태를 판별해 해결했다.   

작업 호출 시 지능을 기반으로 호출되기에 로봇의 지능은 `항상 최신화` 되어야한다.   
이에 대한 문제점은 아래와 같다.

- 배열 사용 시 로봇은 50000개 이므로 나이브하게 해결할 수 없다.   
- 우선순위 큐 두 개(min, max) 사용 시 두 큐에 들어 있는 로봇의 상태가 동일함을 보장해야 한다.

따라서, `로봇 상태`를 저장하는 구조체를 따로 두고, 두 개의 큐에는 로봇 상태의 `일부`를 저장한다.   

큐에는 한 로봇의 이전/현재 상태가 존재할 수 있다.    
따라서, 큐에서 `poll한 상태`와 `로봇 상태`가 동일한지 체크 후 메소드를 실행한다.

<br>

### 최소 힙과 최대 힙
큐에 들어있는 로봇의 지능은 매초 변화하고, 언제 들어갔는지에 따라 현재의 지능이 다르다.   
지능으로 작업에 투입될 로봇을 판별할 수 없다.   

따라서, 트레이닝 시작 시간과 트레이닝 시작 당시 지능을 이용해 기준을 정한다.    
`현재 시간 - (트레이닝 시작 시간 - 트레이닝 시작 당시 지능)`으로 현재 로봇의 지능을 판별할 수 있다.   


두 정보를 저장한다.
- 로봇의 ID
- 시간: 트레이닝 시작 시간-지능

``` java
static Queue<int[]> minHeap; // 지능 낮은 순   // {idx, 트레이닝 시작 시간-지능}
static Queue<int[]> maxHeap; // 지능 높은 순   // {idx, 트레이닝 시작 시간-지능}

~~~

this.minHeap = new PriorityQueue<>((arr1,arr2) -> (
                arr1[1]!=arr2[1]? arr2[1]-arr1[1]: arr1[0]-arr2[0]
        ));
this.maxHeap = new PriorityQueue<>((arr1,arr2) -> (
                arr1[1]!=arr2[1]? arr1[1]-arr2[1]: arr1[0]-arr2[0]
        ));

```

<br>


### 로봇 상태 
2차원 정수 배열로 저장한다.   

0. 현재 로봇 상태   
    0: 고장
    1: 작업 중
    2: 트레이닝 중
1. 로봇의 지능
2. 시간: 트레이닝 시작 시간-지능
3. 작업 ID
```java
// {상태, 지능, 트레이닝 시작 시간-지능, 작업ID} 
// 상태 (0:고장, 1:작업중, 2:트레이닝중)
static int[][] robots;  
```

<br>

### 작업 상태
2차원 정수 배열로 저장한다.   
- 1차원 인덱스는 작업 ID를 의미한다.    
- 2차원 인덱스는 해당 작업에 투입된 로봇들의 ID를 의미한다.

```java
static int[][] works;   // {작업ID, {로봇들}}
```

<br>

### call_job()
기준(mOpt)에 따라 큐에서 로봇을 Poll한다.   
- 로봇 상태가 `고장 또는 작업중`이라면 다음 로봇을 뽑는다.
- `Poll한 로봇의 시간`과 `로봇 상태의 시간`이 다른 경우 다음 로봇을 뽑는다.
- 작업 가능한 로봇인 경우 상태를 변경한다.

<br>

### return_job()
works 배열을 이용해 작업을 종료한다.
- 로봇이 작업중이 아닌 경우 무시한다.(고장, 트레이닝)
- 작업 번호가 다른 경우 무시한다.(고장 -> 수리 -> 다른 작업 투인 된 경우)
- 큐에 Add 시 `시간`을 갱신 한다.

<br>

### broken()
작업중이 아닌 경우 무시한다.

<br>

### repair()
- 고장이 아닌 경우 무시한다.
- 큐에 Add 시 `시간`을 갱신 한다.
    - 지능이 0 으로 초기화 됐으므로 현재 시간으로 초기화한다.

<br>

### check()
지능의 경우, `현재 시간 - 시간` 을 이용한다.

