import sys
input = sys.stdin.readline
# 31120KB 32ms
N, M = map(int, input().split())
books = list(map(int, input().split()))

left = list(filter(lambda x : x<0, books))
left.sort()
right = list(filter(lambda x : x>0, books))
right.sort(reverse=True)

# 마지막에 꽂을 책의 위치(왼쪽 오른쪽)
lastBook = ""

if not left: # 빈 배열일 때
    lastBook = "right"
elif not right or (-left[0] > right[0]): # 제일 먼 곳은 마지막에
    lastBook = "left"
else:
    lastBook = "right"

answer = right[0] if lastBook == "right" else -left[0]
answer += sum(-left[i]*2 for i in range( (M if lastBook == 'left' else 0), len(left), M))
answer += sum(right[i] * 2 for i in range( (M if lastBook == 'right' else 0), len(right), M))

print(answer)


""" 리팩토링!
N, M = map(int, input().split())
books = list(map(int, input().split()))

left = list(filter(lambda x : x<0, books))
left.sort()
right = list(filter(lambda x : x>0, books))
right.sort(reverse=True)


answer = sum(-left[i]*2 for i in range( 0, len(left), M))
answer += sum(right[i] * 2 for i in range( 0, len(right), M))

if not left: 
    answer -= right[0] 
elif not right or (-left[0] > right[0]): 
    answer += left[0]
else:
    answer -= right[0]
    
print(answer)
"""
