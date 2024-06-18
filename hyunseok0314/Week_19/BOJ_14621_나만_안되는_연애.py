import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())

gender = input().rstrip().split()

adj = [[] for _ in range(N)]
edges = []

for _ in range(M):
    u, v, w = map(int, input().rstrip().split())
    u, v = u - 1, v - 1
    if gender[u] != gender[v]:
        adj[u].append(v)
        adj[v].append(u)
        edges.append((w, u, v))

q = [0]
visited = [False for _ in range(N)]
visited[0] = True

count = 1

while q:
    n = q.pop()
    for nn in adj[n]:
        if not visited[nn]:
            q.append(nn)
            visited[nn] = True
            count += 1

if count != N:
    print(-1)
    exit()

parent = [i for i in range(N)]


def find(u):
    if u != parent[u]:
        parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    u, v = find(u), find(v)
    parent[v] = u


edges.sort()

total = 0

for w, u, v in edges:
    if find(u) != find(v):
        union(u, v)
        total += w

print(total)
