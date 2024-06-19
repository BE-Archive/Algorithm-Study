import sys
input = sys.stdin.readline

N, d, k, c = map(int, input().split())

# 리스트 belt : 벨트 위 초밥의 종류
belt = [int(input()) for _ in range(N)]
belt.extend(belt[:k]) # 벨트의 끝과 처음을 연결

# 딕셔너리 eat : i부터 i+k까지 먹을 때 초밥번호와 갯수를 카운팅
eat = {i : 0 for i in range(d+1)}
eat[c] = 1

for i in range(k):
    eat[belt[i]] += 1

# 변수 type : i부터 i+k까지 먹을 때의 초밥 가짓수
type = sum(value != 0 for value in eat.values())
answer = type

#### 초기화 끝

for i in range(N):
    if not eat[belt[i+k]] : type+=1 # 이전에 없던 초밥이라면
    eat[belt[i+k]] += 1
    eat[belt[i]] -= 1
    if not eat[belt[i]]: type-=1 # 초밥종류가 줄어든다면

    if type>answer : answer=type
    if type>k : break

print(answer)