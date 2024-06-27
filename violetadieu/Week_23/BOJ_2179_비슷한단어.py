N=int(input())

same=dict()

for i in range(N):
    now=input()
    idx=1
    while idx<=len(now):
        try:
            same[now[:idx]].append(now)
        except:
            same[now[:idx]]=[now]
        idx+=1
res1 = dict({key: value for key, value in same.items() if len(value) != 1})
res=sorted(res1,key=lambda x:(len(x)),reverse=True)


print(same[res[0]][0])
print(same[res[0]][1])
