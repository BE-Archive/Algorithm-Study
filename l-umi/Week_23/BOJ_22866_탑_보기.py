import sys
input = sys.stdin.readline

N = int(input())
buildings = [0] + list(map(int, input().rstrip().split()))

cnt = [[0,0] for _ in range(N+1)]
left_index = [0] * (N+1)
right_index = [0] * (N+1)

#################################################
# left_index 갱신
for i in range(1, N+1):
    temp = i-1
    while temp > 0 :
        if buildings[temp] > buildings[i] :
            left_index[i] = temp
            cnt[i][0] = cnt[temp][0]+1
            break
        else:
            temp = left_index[temp]

#right_index 갱신
for i in range(N, 1, -1):
    temp = i
    while temp > 0:
        if buildings[i-1] < buildings[temp] :
            right_index[i-1] = temp
            cnt[i-1][1] += cnt[temp][1]+1
            break
        else:
            temp = right_index[temp]
            
#################################################
for i in range(1, N+1):
    if sum(cnt[i]) == 0 :
        print(0)
    else:
        print(sum(cnt[i]), end=" ")
        if 0 in cnt[i]:
            print(left_index[i] if right_index[i] == 0 else right_index[i])
        else:
            print(left_index[i] if i-left_index[i] <= right_index[i]-i else right_index[i])
