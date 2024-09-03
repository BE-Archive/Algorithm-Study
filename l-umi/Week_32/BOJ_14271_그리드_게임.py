from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
visits = [[False] * 3050 for _ in range(3050)]

for n in range(N):
    grid = input()
    for m in range(M):
        if grid[m] == 'o':
            visits[n+1500][m+1500] = True


K = int(input())
answer = 0

##########################################

def bfs():
    global answer, K
    queue = deque()

    for n in range(N):
        for m in range(M):
            if visits[n + 1500][m + 1500]:
                queue.append((n + 1500, m + 1500))

    dr = [0, 0, -1, 1]
    dc = [-1, 1, 0, 0]

    while queue and K >= 0:
        size = len(queue)
        answer += size

        for _ in range(size):
            r, c = queue.popleft()

            for i in range(4):
                newR, newC = r + dr[i], c + dc[i]
                if 0 <= newR < 3050 and 0 <= newC < 3050 and not visits[newR][newC]:
                    queue.append((newR, newC))
                    visits[newR][newC] = True

        K -= 1
        
##########################################
        
bfs()
print(answer)