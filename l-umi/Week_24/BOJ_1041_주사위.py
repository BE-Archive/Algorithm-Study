import sys
input = sys.stdin.readline

N = int(input())
dice = list(map(int, input().split()))

##########################################

if N == 1 :
  print(sum(dice) - max(dice))
  exit()
  
# 인접한 3면의 인덱스
threeIndex = [(0,1,2), (0,1,3), (0,2,4), (0,3,4), (1,2,5),(1,3,5),(2,4,5),(3,4,5)]
# 인졉한 2면의 인덱스
twoIndex = [(0,1), (0,2), (0,3), (0,4), (1,2), (1,3), (1,5), (2,4), (2,5), (3,4), (3,5), (4,5)]

# 인접한 면의 최솟값 계산
min3 = min(dice[i[0]] + dice[i[1]] + dice[i[2]] for i in threeIndex)
min2 = min(dice[i[0]] + dice[i[1]] for i in twoIndex)
min1 = min(dice)

# 점화식
print(min3 * 4 + min2 * (2*N-3) * 4 + min1 * 4 * (N-1) * (N-2) + min1 * (N-2) * (N-2))
