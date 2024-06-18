import copy
from itertools import combinations
from collections import deque

def bfs(thisArchers):
    global answer

    tmp = copy.deepcopy(myMap)
    thisAnswer = 0

    for archerX in range(n - 1, -1, -1):
        killSet = set()

        for archerY in thisArchers:
            que = deque()
            que.append([archerX, archerY, 1])

            while que:  # 특정 궁수에 대해서 bfs
                x, y, thisD = que.popleft()

                if tmp[x][y] == 1:
                    killSet.add((x, y))
                    break

                if thisD < d:
                    for j in range(3):
                        nx = x + dx[j]
                        ny = y + dy[j]

                        if 0 <= nx < n and 0 <= ny < m:
                            que.append([nx, ny, thisD + 1])

        for enemyX, eneymyY in killSet:
            tmp[enemyX][eneymyY] = 0
            thisAnswer += 1

    answer = max(answer, thisAnswer)


n, m, d = map(int, input().split())

myMap = []
for _ in range(n):
    myMap.append(list(map(int,input().split())))

tmp = [i for i in range(m)]
archerList = list(combinations(tmp, 3))

dx = [0, -1, 0]
dy = [-1, 0, 1]

answer = 0
for thisArchers in archerList:
    bfs(thisArchers)

print(answer)
