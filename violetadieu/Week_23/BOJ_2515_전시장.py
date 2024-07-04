N,S=map(int,input().split())

picture=list()
DP=[-99999 for _ in range(N+1)]
for i in range(N):
    l,c=map(int,input().split())
    picture.append((l,c))
picture.sort(key=lambda x:(x[0],-x[1]))
picture=[(0,0)]+picture
DP[0]=0
tmp_idx=0
tmp_val=0
for idx1 in range(1,N+1):
    for idx2 in range(tmp_idx,idx1):
        if picture[idx1][0]-picture[idx2][0]<S:
            break
        tmp_idx=idx2
        tmp_val=max(DP[idx2],tmp_val)
    DP[idx1]=max(DP[idx1],tmp_val+picture[idx1][1])

print(max(DP))
