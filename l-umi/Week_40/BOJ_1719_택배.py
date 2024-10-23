import sys

INF = sys.maxsize
input = sys.stdin.readline
n, m = map(int, input().split())

###########################################
distance = [[INF] * (n + 1) for _ in range(n + 1)]
next_point = [[0] * (n + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    distance[i][i] = 0

for _ in range(m):
    u, v, w = map(int, input().split())
    distance[u][v] = w
    distance[v][u] = w
    next_point[u][v] = v
    next_point[v][u] = u

###########################################
for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if distance[i][j] > distance[i][k] + distance[k][j]:
                distance[i][j] = distance[i][k] + distance[k][j]
                next_point[i][j] = next_point[i][k]

for i in range(1, n + 1):
    for j in range(1, n + 1):
        print("-" if i == j else next_point[i][j], end=" ")
    print()
