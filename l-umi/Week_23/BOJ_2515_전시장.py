import sys
input = sys.stdin.readline

H, C = map(int, input().split())
pictures = [tuple(map(int, input().split())) for n in range(H)]
pictures.append((0,0))
pictures.sort()

# 자신보다 C 낮은 그림들 중 가장 키 큰 그림의 인덱스
limit = [0]*(H+1)
temp = 0
for i in range(1, H+1):
    while pictures[i][0] - pictures[temp][0] >= C :
        temp += 1
    limit[i] = temp-1

# 그림 선택
dp = [0]*(H+1)
for i in range(1, H+1):
    dp[i] = max(dp[i-1], dp[limit[i]] + pictures[i][1])

print(dp[-1])
