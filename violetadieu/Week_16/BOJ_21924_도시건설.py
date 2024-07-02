def find(t):
    global parent
    if parent[t]==t:
        return t
    else:
        parent[t]=find(parent[t])
        return parent[t]
def union(s,t):
    global parent
    t=find(t)
    s=find(s)

    parent[t]=s
N,M=map(int,input().split())
parent=[i for i in range(0,N+1)]
road=[]
originalCost=0
afterCost=0
for i in range(M):
    a,b,c=map(int,input().split())
    originalCost+=c
    road.append((c,(a,b)))

road=sorted(road)

for item in road:
    if find(item[1][0])==find(item[1][1]):
        continue
    union(item[1][0],item[1][1])
    afterCost+=item[0]
answer=originalCost-afterCost
tmp=0
for i in range(1,N+1):
    if parent[i]==i:
        tmp+=1
    if tmp==2:
        answer=-1
        break

print(answer)
