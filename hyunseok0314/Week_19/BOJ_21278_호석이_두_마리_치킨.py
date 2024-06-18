from itertools import combinations
import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())

dist = [[float("inf") if i != j else 0 for j in range(N)] for i in range(N)]

for _ in range(M):
    u, v = map(lambda x: int(x) - 1, input().rstrip().split())
    dist[u][v] = 1
    dist[v][u] = 1

for k in range(N):
    for i in range(N):
        for j in range(N):
            dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

ans = (float("inf"), float("inf"), float("inf"))

for c in combinations(range(N), 2):
    u, v = c
    ans = min(ans, (sum(map(min, zip(dist[u], dist[v]))) << 1, u + 1, v + 1))

print(ans[1], ans[2], ans[0])
