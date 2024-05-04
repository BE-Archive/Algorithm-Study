def summation(K):
    ans=0
    if K<=0:
        return 0
    last=0
    front=0
    start=1
    cnt=[0 for _ in range(10)]
    while K>0:
        last=K%(start*10)
        front=int(K/(start*10))
        for i in range(10):
            cnt[i]+=start*front
        for i in range(1,int(last/start)+1):
            cnt[i]+=start
        cnt[(int(last/start)+1)%10]+=last%start
        K-=9*start
        start*=10

    for i in range(10):
        ans+=i*cnt[i]

    return ans

L,U=map(int,input().split())

print(summation(U)-summation(L-1))
