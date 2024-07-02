N=int(input())

src=list(map(int,input().split()))

tgt=list(map(int,input().split()))

start=tgt.index(src[0])

a1=tgt[start:]+tgt[:start]
a2=tgt[start+1:]+tgt[:start+1]
a2.reverse()

if a1==src or a2==src:
    print("good puzzle")
else:
    print("bad puzzle")
