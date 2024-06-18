N,M=map(int,input().split())
password=dict()
for i in range(N):
    site,pw=map(str,input().split())
    password[site]=pw
for i in range(M):
    tgt=input()
    print(password[tgt])
