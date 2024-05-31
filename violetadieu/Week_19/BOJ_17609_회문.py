def ispel(s):

    left=0
    right=len(s)-1
    while left<int(len(s)/2)+1 and left<right:
        if s[left]==s[right]:
            left+=1
            right-=1
        else:
            t=s[:left]+s[left+1:]
            t2=s[:right]+s[right+1:]
            if t == t[::-1] or t2 == t2[::-1]:
                return 1
            else:
                return 2
    return 0

T=int(input())

for _ in range(0,T):
    now=input()
    r=ispel(now)
    print(r)
