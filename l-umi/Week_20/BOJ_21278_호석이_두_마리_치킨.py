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