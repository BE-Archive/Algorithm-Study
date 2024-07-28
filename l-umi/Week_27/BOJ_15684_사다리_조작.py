import sys
input = sys.stdin.readline

N, M, H = map(int, input().split())
ans = sys.maxsize

lines = [[0] * (H+1) for _ in range(N+1)]


for _ in range(M):
  h, n = map(int, input().split())
  lines[n][h] = n + 1
  lines[n + 1][h] = n

###################################################
# check : i번 세로선의 결과가 i번이 나오는 상황인지 체크하는 함수
def check():
  for i in range(1, N):
    curr = i
    for j in range(1, H + 1):
      if lines[curr][j] != 0:
        curr = lines[curr][j]
    if curr != i:
      return False
  return True

# find_min : 추가해야 하는 가로선 개수의 최솟값을 계산하는 함수
def find_min(count):
  global ans
  
  if check():
    ans = min(ans, count)
    return

  # 정답이 3보다 크거나 불가능한 경우
  if count >= ans or count == 3:
    return

  for i in range(1, N):
    flag = False

    
    for j in range(1, H + 1):
      if flag and lines[i][j] == 0 and lines[i + 1][j] == 0:
         continue
      # flag : 같은 세로선에 연속해서 설치할 필요가 없을때를 체크
      flag = False

      
      if lines[i][j] == 0 and lines[i + 1][j] == 0:
        lines[i][j] = i + 1
        lines[i + 1][j] = i
        find_min(count + 1)
        lines[i][j] = 0
        lines[i + 1][j] = 0
        flag = True
###################################################

find_min(0)
print(-1 if ans == sys.maxsize else ans)
