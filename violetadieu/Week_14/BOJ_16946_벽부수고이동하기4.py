from copy import deepcopy
from collections import deque

N, M = map(int, input().split())
mape = [list(input()) for _ in range(N)]
vi = [[False for _ in range(M)] for _ in range(N)]
wall=[]
blank = []
for idx1 in range(N):
    for idx2 in range(M):
        mape[idx1][idx2] = int(mape[idx1][idx2])
        if mape[idx1][idx2] == 0:
            blank.append((idx1, idx2))
        else:
            wall.append((idx1,idx2))
ans = deepcopy(mape)
MF = [0, 0, -1, 1]
MS = [-1, 1, 0, 0]
dic=dict()
for item in blank:
    if vi[item[0]][item[1]]:
        continue
    dic_num=len(dic)+2
    cnt = 1
    de = deque()
    de.append((item[0], item[1]))
    vi[item[0]][item[1]]=True
    mape[item[0]][item[1]]=dic_num
    while len(de) != 0:
        now = de.popleft()
        for idx in range(4):
            nf = now[0] + MF[idx]
            ns = now[1] + MS[idx]
            if nf < 0 or nf >= N or ns < 0 or ns >= M or mape[nf][ns] != 0 or vi[nf][ns] == True:
                continue
            vi[nf][ns] = True
            cnt+=1
            mape[nf][ns]=dic_num
            de.append((nf, ns))
    dic[dic_num] = cnt

for item in wall:
    c=1
    plus=list()
    for idx in range(4):
        nf=item[0]+MF[idx]
        ns=item[1]+MS[idx]
        if nf < 0 or nf >= N or ns < 0 or ns >= M or mape[nf][ns]==1 or mape[nf][ns] in plus:
            continue
        plus.append(mape[nf][ns])
        c+=dic[mape[nf][ns]]
    ans[item[0]][item[1]]=c
for item in ans:
    print("".join(list(map(str,item))))
