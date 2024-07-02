N = int(input())
arr = []
for i in range(N):
    start, end = map(int, input().split())
    arr.append((start, end))
arr.sort(key=lambda x: (x[1], x[0]))
now = 0
ans = 0
for i in range(N):
    if now <= arr[i][0]:
        now = arr[i][1]
        ans += 1
print(ans)