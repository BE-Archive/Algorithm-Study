N,M=map(int,input().split())

t=list(map(int,input().split()))
if len(t)>1:
    del t[0]
else:
    print(M)
    exit()
t=set(t)
party=list()
for i in range(M):
    tmp=set(list(map(int,input().split()))[1:])
    party.append(tmp)

for i in range(50):
    for item in party:
        if len(set(item).intersection(t))>=1:
            t=t.union(item)

for i in range(0,50,-1):
    for item in party:
        if len(set(item).intersection(t))>=1:
            t=t.union(item)

for item in party:
    if len(set(item).intersection(t))>=1:
        M-=1
print(M)