import sys
sys.setrecursionlimit(10 ** 6)

def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return

    else:
        parent[y] = x
        visited[x] += visited[y]

def find(target):
    if target == parent[target]:
        return target

    else:
        parent[target] = find(parent[target])
        return parent[target]


t = int(sys.stdin.readline())
for _ in range(t):
    f = int(sys.stdin.readline())
    parent = dict()
    visited = dict()

    for i in range(f):
        a, b = map(str, sys.stdin.readline().split())

        if a not in parent:
            parent[a] = a
            visited[a] = 1

        if b not in parent:
            parent[b] = b
            visited[b] = 1

        union(a, b)

        print(visited[find(a)])
