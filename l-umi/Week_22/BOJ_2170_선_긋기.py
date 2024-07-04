import sys
input = sys.stdin.readline

N = int(input())
line = []
for n in range(N):
    x, y = map(int, input().split())
    line.append((x,y))
line.sort()
# print(line)
####################

start = line[0][0]
end = line[0][1]
answer = 0
for i in range(1,N):
    if end < line[i][0]:
        answer += end-start
        start, end = line[i]
    else:
        end = max(end, line[i][1])
    # print(start, end, answer)

answer += end-start
print(answer)
