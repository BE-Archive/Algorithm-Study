def tracking(depth):
    global res
    if depth==11:
        res=max(res,sum(ans))
        return
    for k in range(11):
        if visit[k]:
            continue
        if players[depth][k]:
            visit[k]=True
            ans.append(players[depth][k])
            tracking(depth+1)
            ans.pop()
            visit[k]=False

C=int(input())

players=[]

for i in range(11):
    p=list(map(int,input().split()))
    players.append(p)

for i in range(C):
    res=0
    ans=[]
    visit = [False for _ in range(12)]
    tracking(0)
    print(res)
