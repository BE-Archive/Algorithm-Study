R,C=map(int,input().split())
result=0
arr=[list(input()) for _ in range(R)]
tgt=[['' for _ in range(R)]for _ in range(C)]

for i in range(C):
    for j in range(R):
        tgt[i][j]= arr[j][i]

s=list()
for item in tgt:
    s.append("".join(item))

for k in range(R):
    se=set()
    for item in s:
        se.add(item[k+1:])
    if len(se)!=C:
        print(result)
        exit()
    result+=1
print(result)
