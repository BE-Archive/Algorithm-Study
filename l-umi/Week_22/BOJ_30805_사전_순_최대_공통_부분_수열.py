import sys
input = sys.stdin.readline

N = int(input())
nList = list(map(int, input().split()))
M = int(input())
mList = list(map(int, input().split()))
nIndex, mIndex = 0, 0
answer = []
number=100
while number > 0 :
    nl = nList[nIndex:]
    ml = mList[mIndex:]
    if number in nl and number in ml :
        answer.append(number)
        nIndex = nl.index(number)+nIndex+1
        mIndex = ml.index(number)+mIndex+1
    else:
        number-=1

print(len(answer))
print(' '.join(map(str, answer)))
