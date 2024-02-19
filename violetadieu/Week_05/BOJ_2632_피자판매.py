from collections import defaultdict
import sys

pizza = int(input())
input = sys.stdin.readline
A, B = map(int, input().split())

PA = list()
PB = list()

answer = 0

CA = defaultdict(int)
CB = defaultdict(int)

for i in range(0, A):
    tmp = int(input())
    PA.append(tmp)

for i in range(0, B):
    tmp = int(input())
    PB.append(tmp)

CA[0] = 1
CB[0] = 1
CA[sum(PA)] = 1
CB[sum(PB)] = 1

# A 경우의 수
for i in range(A):
    total = PA[i]
    CA[total]+=1
    for p in range(1, A - 1):
        total += PA[(i + p) % A]
        CA[total]+=1

# B 경우의 수
for i in range(B):
    total = PB[i]
    CB[total]+=1
    for p in range(1, B - 1):
        total += PB[(i + p) % B]
        CB[total]+=1

for num in CA:
    if pizza - num in CB:
        answer += CA[num] * CB[pizza - num]

print(answer)
