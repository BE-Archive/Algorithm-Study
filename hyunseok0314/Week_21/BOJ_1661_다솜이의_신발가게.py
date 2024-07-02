import sys

input = sys.stdin.readline

N, P = map(int, input().rstrip().split())

price = [[],[],[]]
discount = [0.99, 0.98, 0.97]
  
for _ in range(N):
    p, d = map(int, input().rstrip().split())
    d -= 1
    price[d].append(p)
    
for p in price:
    p.sort()
    p.insert(0, 0)
    for i in range(1, len(p)):
        p[i] += p[i - 1]
        
ans = P
    
for i in range(len(price[0])):
    for j in range(len(price[1])):
        for k in range(len(price[2])):
            ans = min(ans, P * (discount[0]**i) * (discount[1]**j) * (discount[2]**k) + price[0][i] + price[1][j] + price[2][k])

print(ans)