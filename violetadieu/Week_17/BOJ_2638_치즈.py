from collections import deque

N, M = map(int, input().split())
MF = [-1, 1, 0, 0]
MS = [0, 0, -1, 1]
map_grid = [[0 for _ in range(M)] for _ in range(N)]
visit = [[False for _ in range(M)] for _ in range(N)]
melt = [[False for _ in range(M)] for _ in range(N)]
cheese = []
air_q = deque()

def bfs_air():
    # Classify external air
    while air_q:
        x, y = air_q.popleft()
        map_grid[x][y] = -1

        for i in range(4):
            nx, ny = x + MF[i], y + MS[i]
            if 0 <= nx < N and 0 <= ny < M and map_grid[nx][ny] == 0 and not visit[nx][ny]:
                air_q.append((nx, ny))
                visit[nx][ny] = True

def is_melt(target):
    cnt = 0
    for i in range(4):
        nx, ny = target[0] + MF[i], target[1] + MS[i]
        if map_grid[nx][ny] == -1:
            cnt += 1

    if cnt >= 2:
        map_grid[target[0]][target[1]] = 0
        return True
    else:
        return False

# Input
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(M):
        map_grid[i][j] = row[j]
        if row[j] == 1:
            cheese.append((i, j))

# Initial air setting
air_q.append((0, 0))
bfs_air()

ans = 0
checked = []
while len(cheese) != len(checked):
    for c in cheese:
        if not melt[c[0]][c[1]] and is_melt(c):
            air_q.append(c)
            melt[c[0]][c[1]] = True
            checked.append(c)
    bfs_air()
    ans += 1

print(ans)

