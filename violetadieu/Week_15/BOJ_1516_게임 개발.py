import queue
import sys

sys.stdin.readline()
sys.stdout.flush()
sys.stdout.reconfigure(line_buffering=True)

cost = [0]
original = []
adj = [[False for _ in range(501)] for _ in range(501)]
visit = [False] * 501
build = [0] * 501

N = int(sys.stdin.readline())
for i in range(1, N+1):
    t = int(sys.stdin.readline())
    cost.append(t)
    original.append(t)
    while True:
        tmp = int(sys.stdin.readline())
        if tmp == -1:
            break
        adj[tmp-1][i-1] = True
        build[i-1] += 1

pq = queue.PriorityQueue()
for i in range(N):
    if build[i] == 0:
        pq.put(i)

while not pq.empty():
    now = pq.get()
    visit[now] = True
    for i in range(N):
        if adj[now][i]:
            if cost[now] + original[i] > cost[i]:
                cost[i] = cost[now] + original[i]
            adj[now][i] = False
            build[i] -= 1
    for i in range(N):
        if build[i] == 0 and not visit[i]:
            pq.put(i)
            visit[i] = True

for c in cost[1:]:
    print(c)
