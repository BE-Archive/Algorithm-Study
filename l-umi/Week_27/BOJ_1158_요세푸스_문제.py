import sys
from collections import deque

input = sys.stdin.readline
N, K = map(int, input().split())

queue = deque(range(1, N+1))
result = []

while queue:
    queue.rotate(-(K-1))
    result.append(queue.popleft())

print("<" + ", ".join(map(str, result)) + ">", end="")



##########################################################33
# import sys
# input = sys.stdin.readline

# N, K = map(int, input().split())

# left= [n for n in range(1, N+1)]
# removed=[]

# i=0
# for _ in range(N):
#   i += K-1
#   while (i >= len(left)):
#     i -= len(left)
#   removed.append(left.pop(i))

# print("<" + ", ".join(map(str, removed)) + ">", end="")



