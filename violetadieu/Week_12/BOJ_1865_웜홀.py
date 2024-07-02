from sys import stdin

input = stdin.readline

def bf():
    D = [200000000] * (N+1)
    D[1] = 0

    for i in range(N):
        for rou in route:
            start, goal, time = rou
            if D[goal] > D[start] + time:
                D[goal] = D[start] + time
                if i == N-1:
                    return 'YES'
    return 'NO'


TC = int(input())

for _ in range(TC):
    N, M, W = map(int,input().split())
    route = []
    for _ in range(M):
        a, b, t = map(int,input().split())
        route.append([a,b,t])
        route.append([b,a,t])

    for _ in range(W):
        s, e, t = map(int,input().split())
        route.append([s,e,-t])


    print(bf())
