import sys
from collections import deque

input = sys.stdin.readline
N, M = map(int, input().split())

inorder = [0 for _ in range(N + 1)]
adj = [[] for _ in range(N + 1)]

for i in range(M):
    order = list(map(int, input().split()))[1:]
    for idx in range(len(order)):
        try:
            adj[order[idx]].append(order[idx + 1])
            inorder[order[idx + 1]] += 1
        except:
            break

de = deque()
ans=list()

for i in range(1,N + 1):
    if inorder[i] == 0:
        de.append(i)
        ans.append(i)

while len(de) > 0:
    now = de.popleft()
    for i in range(len(adj[now])):
        inorder[adj[now][i]]-=1
        if inorder[adj[now][i]]==0:
            de.append(adj[now][i])
            ans.append(adj[now][i])

if len(ans)!=N:
    print(0)
    exit()
for item in ans:
    print(item)