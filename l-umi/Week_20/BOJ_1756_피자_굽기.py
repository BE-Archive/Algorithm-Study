import sys
input = sys.stdin.readline
# 120084KB 416ms
D, N = map(int, input().rstrip().split())
oven = list(map(int, input().rstrip().split()))
pizza = list(map(int, input().rstrip().split()))

# ovens : 해당 깊이까지에 올수있는 피자반죽의 최소 반지름을 포함한 배열
# ovens[i] = (깊이, 최소 반지름)
# ovens는 최소 반지름을 기준으로 오름차순 정렬, 깊이를 기준으로 내림차순 정렬 되어있다
ovens = []
minR = sys.maxsize
for i in range(D):
    if minR > oven[i] :
        minR = oven[i]
    ovens.append((i, minR))
ovens.sort(key=lambda x : (x[1], -x[0]))
# print(ovens)

i = 0
for p in pizza :
    while True:
        # 피자가 모두 오븐에 들어가지 않으면
        if i >= D :
            print(0)
            exit()
        i = i+1
        # 피자 반죽이 떨어질 위치
        if p <= ovens[i-1][1]:
            break

print(ovens[i-1][0]+1)
