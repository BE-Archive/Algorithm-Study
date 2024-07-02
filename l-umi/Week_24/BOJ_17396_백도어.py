import sys
import heapq
input = sys.stdin.readline

N, M = map(int, input().split())

nodes = list(map(int, input().rstrip().split()))
nodes[N-1] = 0

adj = [[] for _ in range(N)]
for _ in range(M):
  start, end, cost = map(int, input().split())
  adj[start].append((cost, end))
  adj[end].append((cost, start))
  
#################################################

distance = [sys.maxsize]*N
distance[0] = 0

pq = []
heapq.heappush(pq, (0,0))

while pq:
  dis, node = heapq.heappop(pq)
  if dis > distance[node]:
    continue 

  for next_cost, next_node in adj[node]:
    newDis = dis + next_cost
    if distance[next_node] > newDis and nodes[next_node] == 0 :
      distance[next_node] = newDis
      heapq.heappush(pq, (newDis, next_node))

#################################################

print(distance[-1] if distance[-1] != sys.maxsize else -1)

