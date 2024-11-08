from collections import deque

N, K = map(int, input().split())

dist = [0] * 100001  
move = [0] * 100001 
found = False

###############################################
q = deque([N])

while q and not found:
    x = q.popleft()
    
    if x == K:
        found = True
        break
        
    for nx in (x+1, x-1, x*2):
        if 0 <= nx <= 100000 and dist[nx] == 0:
            q.append(nx)
            dist[nx] = dist[x] + 1
            move[nx] = x
###############################################
print(dist[K])

path = []
curr = K
for _ in range(dist[K] + 1):
    path.append(str(curr))
    curr = move[curr]
    
print(' '.join(path[::-1]))