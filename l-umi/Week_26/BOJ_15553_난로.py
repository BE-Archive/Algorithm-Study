import sys
input = sys.stdin.readline
from queue import PriorityQueue

N, K = map(int, input().split())
pq = PriorityQueue()

##########################################

before = int(input())
answer = 1-before

for _ in range(N-1):
  now = int(input())
  pq.put(1-now+before)
  before = now

answer += before

##########################################

for k in range(K-1):
  if(answer<=0):
    break
  answer += pq.get()

print(answer)