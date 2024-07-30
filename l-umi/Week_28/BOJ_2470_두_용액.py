import sys
input = sys.stdin.readline

N = int(input())
PHs = list(map(int, input().split()))
PHs.sort()

#######################################

left = 0
right = N-1
best_sum = sys.maxsize
answer = (0, 0)

while left < right :
    current_sum = PHs[left] + PHs[right]

    if abs(current_sum) < abs(best_sum) :
        best_sum = current_sum
        answer = (PHs[left], PHs[right])

    if current_sum < 0 :
        left += 1
    elif current_sum > 0 :
        right -= 1
    else :
        break
#######################################
print(answer[0], answer[1], sep=" ")