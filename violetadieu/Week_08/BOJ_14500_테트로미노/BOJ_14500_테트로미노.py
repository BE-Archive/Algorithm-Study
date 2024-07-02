mf=[0,0,-1,1]
ms=[-1,1,0,0]
N,M=map(int,input().split())
paper=[list(map(int,input().split())) for _ in range(N)]
visit = [[False for _ in range(M)] for _ in range(N)]
ans=-1
def dfs(depth,f,s,cost,visit):
    global ans
    cost+=paper[f][s]
    if depth==4:
        ans=max(ans,cost)
        return
    for i in range(len(mf)):
        nf=f+mf[i]
        ns=s+ms[i]
        if 0<=nf<N and 0<=ns<M and visit[nf][ns]!=True:
            visit[nf][ns]=True
            dfs(depth+1,nf,ns,cost,visit)
            visit[nf][ns]=False
for f in range(N):
    for s in range(M):
        visit[f][s]=True
        dfs(1,f,s,0,visit)
        visit[f][s]=False
        #ㅗ 모양만 따로
        try:
            ans=max(ans,paper[f][s]+paper[f][s+1]+paper[f][s+2]+paper[f-1][s+1])
        except:
            ans=ans
        try:
            ans=max(ans,paper[f][s] + paper[f][s + 1] + paper[f][s + 2] + paper[f + 1][s + 1])
        except:
            ans=ans
        try:
            ans=max(ans,paper[f][s] + paper[f + 1][s] + paper[f + 2][s] + paper[f + 1][s + 1])
        except:
            ans=ans
        try:
            ans=max(ans,paper[f][s] + paper[f + 1][s] + paper[f + 2][s] + paper[f + 1][s - 1])
        except:
            ans=ans
print(ans)