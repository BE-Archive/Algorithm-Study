import sys
from typing import List

n, m = map(int, input().split())

graph = [[sys.maxsize] * (n + 1) for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1

for i in range(1, n + 1):
    graph[i][i] = 0

for k in range(1, n + 1):
    for a in range(1, n + 1):
        for b in range(1, n + 1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

min_sum = sys.maxsize
result: List[int] = []
for i in range(1, n):
    for j in range(2, n + 1):
        sum_val = 0
        for k in range(1, n + 1):
            sum_val += min(graph[k][i], graph[k][j]) * 2
        if sum_val < min_sum:
            min_sum = sum_val
            result = [i, j, sum_val]

print(result[0], result[1], result[2])


