from itertools import combinations
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
adj = [[sys.maxsize] * N for _ in range(N)]
for _ in range(M):
    node1, node2 = map(lambda x : int(x) - 1, input().split())
    adj[node1][node2] = 1
    adj[node2][node1] = 1

# 플로이드 워샬
for n in range(N):
    for r in range(N):
        for c in range(N):
            adj[n][n] = 0
            adj[r][c] = min(adj[r][c], adj[r][n]+adj[n][c])

answer = [0, 0, sys.maxsize]
for c in combinations(range(N), 2) :
    node1, node2 = c
    times = sum(map(lambda i : min(adj[node1][i], adj[node2][i]), range(N))) * 2
    if times < answer[2] :
        answer = [node1+1, node2+1, times]

print(answer[0], answer[1], answer[2])

"""
from itertools import combinations
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
# 인접 리스트 : 각 정점에 연결된 노드 정보를 저장
adj = [set() for _ in range(N+1)]
for _ in range(M):
    node1, node2 = map(int, input().split())
    adj[node1].add(node2)
    adj[node2].add(node1)

# 2개의 매장
combi = combinations(range(1, N+1), 2)
answer = [0, 0, sys.maxsize]
# 완전탐색
for c in combi :
    times = 0
    time = 1
    visited = set(c)
    current = adj[c[0]] | adj[c[1]]
    while current :
        next = set()
        for node in current:
            if not node in visited :
                # 아직 방문한 곳이 아니라면
                times += time*2
                visited.add(node)
                next.update(adj[node])
        time += 1
        current = next
    if times < answer[2] :
        answer = [c[0], c[1], times]

print(answer[0], answer[1], answer[2])
"""