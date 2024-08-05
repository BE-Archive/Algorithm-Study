import sys
sys.setrecursionlimit(10000)  # 재귀 한도를 높이기
from collections import deque 
input = sys.stdin.readline

N, M, K = map(int, input().split())
visited = [[False] * (M + 2) for _ in range(N + 2)]

for _ in range(K):
    r, c = map(int, input().split())
    visited[r][c] = True

###################################################
dr = (-1, 0, 1, 0)
dc = (0, 1, 0, -1)
answer = 0

def dfs(r, c):
    stack = [(r, c)]
    depth = 0
    while stack:
        r, c = stack.pop()
        if visited[r][c]:
            visited[r][c] = False
            depth += 1
            for i in range(4):
                rr = r + dr[i]
                cc = c + dc[i]
                if visited[rr][cc]:
                    stack.append((rr, cc))
    return depth

for n in range(1, 1 + N):
    for m in range(1, 1 + M):
        if visited[n][m]:
            current_depth = dfs(n, m)
            if current_depth > answer:
                answer = current_depth

print(answer)
