import sys
input = sys.stdin.readline

N = int(input())
map = [list(map(int, input().split())) for _ in range(N)]
dist = [[[0 for _ in range(3)] for _ in range(N+1)] for _ in range(N+1)]

for i in range(1, N+1):
    for j in range(1, N+1):
        milk = map[i-1][j-1]

        for m in range(3):
            dist[i][j][m] = max(dist[i-1][j][m], dist[i][j-1][m])

        if milk == 0:
            dist[i][j][0] = max(dist[i][j][0], dist[i][j][2] + 1)
        elif dist[i][j][milk - 1] != 0:
            dist[i][j][milk] = max(dist[i][j][milk], dist[i][j][milk - 1] + 1)

print(max(dist[N][N]))

''' 이전 풀이(시간초과)
N = int(input())
map = [list(map(int, input().split())) for _ in range(N)]
dist = [[[0 for _ in range(3)] for _ in range(N)] for _ in range(N)]

for i in range(N):
    for j in range(N):
        milk = map[i][j]

        # 우유 안마실때
        for m in range(3):
            if i+1 < N :
                dist[i+1][j][m] = max(dist[i][j][m], dist[i+1][j][m])
            if j+1 < N :
                dist[i][j+1][m] = max(dist[i][j][m], dist[i][j+1][m])

        # 딸기우유 마시면
        if milk == 0 :
            if i+1 < N :
                dist[i+1][j][milk] = max(dist[i+1][j][milk], dist[i][j][2] + 1)
            if j+1 < N :
                dist[i][j+1][milk] = max(dist[i][j+1][milk], dist[i][j][2] + 1)

        # 초코우유 마시면
        elif milk == 1 and dist[i][j][0] != 0 :
            if i+1 < N :
                dist[i+1][j][milk] = max(dist[i+1][j][milk], dist[i][j][0] + 1)
            if j+1 < N :
                dist[i][j+1][milk] = max(dist[i][j+1][milk], dist[i][j][0] + 1)

        # 바나나우유 마시면
        elif milk == 2 and dist[i][j][1] != 0 :
            if i+1 < N :
                dist[i+1][j][milk] = max(dist[i+1][j][milk], dist[i][j][1] + 1)
            if j+1 < N :
                dist[i][j+1][milk] = max(dist[i][j+1][milk], dist[i][j][1] + 1)

# 마지막 우유
milk = map[N-1][N-1]
if milk == 0 or dist[i][j][milk-1] != 0:
    dist[N-1][N-1][milk] = max(dist[N-1][N-1][milk], dist[N-1][N-1][2 if milk == 0 else milk - 1]+1)


print(max(dist[N-1][N-1]))
'''
