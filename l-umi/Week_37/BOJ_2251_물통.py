from collections import deque
import sys
input = sys.stdin.readline

A, B, C = map(int, input().split())
visited = set()  # 방문 기록
result = set()  

###########################################
def bfs():
    queue = deque([(0, 0, C)])  
    
    while queue:
        x, y, z = queue.popleft()  
        
        if (x, y, z) in visited:
            continue
        visited.add((x, y, z))  
        
        if x == 0:  # 첫 번째 물통이 비어 있을 때
            result.add(z)  # 세 번째 물통에 담긴 물의 양을 기록
        
        # 물 옮기는 6가지 경우의 수
        move = min(x, B - y)
        queue.append((x - move, y + move, z))  # x -> y

        move = min(x, C - z)
        queue.append((x - move, y, z + move))  # x -> z

        move = min(y, A - x)
        queue.append((x + move, y - move, z))  # y -> x

        move = min(y, C - z)
        queue.append((x, y - move, z + move))  # y -> z

        move = min(z, A - x)
        queue.append((x + move, y, z - move))  # z -> x

        move = min(z, B - y)
        queue.append((x, y + move, z - move))  # z -> y

###########################################
bfs()

print(' '.join(map(str, sorted(result))))
