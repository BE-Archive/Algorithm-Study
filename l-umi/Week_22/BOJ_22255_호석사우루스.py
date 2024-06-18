import sys
from queue import PriorityQueue
input = sys.stdin.readline

N, M = map(int, input().split())
Sx, Sy, Ex, Ey = [i - 1 for i in map(int, input().split())]
map = [list(map(int, input().split())) for _ in range(N)]

##### 입력 끝

pq = PriorityQueue()
def dijkstra():
    # cost, (x, y), count
    pq.put((0, (Sx, Sy), 1))
    dist[Sx][Sy][1] = 0

    while not pq.empty():
        current = pq.get()
        # print("==========================")
        # print(current)
        # for n in range(N):
        #     print(dist[n])
        if dist[current[1][0]][current[1][1]][current[2]] < current[0]:
            continue
        if current[1] == (Ex, Ey):
            return

        if current[2] == 0:
            for i in (0,1,2,3):
                cal(i, current)
        elif current[2] == 1:
            for i in (0,2):
                cal (i,current)
        else:
            for i in (1,3):
                cal(i, current)
    return -1

dx = (-1, 0, 1, 0)
dy = (0, 1, 0, -1)
def cal(i, current):
    x = current[1][0] + dx[i]
    y = current[1][1] + dy[i]
    if x >= 0 and y >= 0 and x < N and y < M :
        if map[x][y] == -1 : return
        count = (current[2] + 1)%3
        if dist[x][y][count] <= map[x][y]+current[0] : return
        dist[x][y][count] = map[x][y]+current[0]
        pq.put((dist[x][y][count], (x,y), count))

##### 함수 끝

dist = [[[sys.maxsize for _ in range(3)] for _ in range(M)] for _ in range(N)]

print(-1 if dijkstra() == -1 else min(dist[Ex][Ey]))




