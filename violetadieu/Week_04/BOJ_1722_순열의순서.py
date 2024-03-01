import math

n = int(input())
k = list(map(int, input().split()))
visit = list(range(1, n+1))
if k[0] == 1:
    val = k[1] - 1
    res = list(range(1, n + 1))
    idx = 0
    while idx != n:
        try:
            res[idx] = visit[int(val / math.factorial(n - idx - 1))]
        except:
            res[idx]=visit[-1]
        visit.remove(res[idx])
        val = val % math.factorial(n - idx - 1)
        idx += 1

    print(*res)
elif k[0] == 2:
    res = k[1:]
    total = 0
    for idx, item in enumerate(res):
        f = item - 1
        for i in range(0, idx):
            if res[i] < item:
                f -= 1
        if f > 0:
            total += f * math.factorial(n - idx - 1)
    print(total + 1)