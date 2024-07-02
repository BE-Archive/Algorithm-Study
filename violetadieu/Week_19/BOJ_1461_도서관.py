N, M = map(int, input().split())
d = list(map(int, input().split()))

positive = sorted([x for x in d if x > 0], reverse=True)
negative = sorted([x for x in d if x <= 0])

result = 0

if positive and negative:
    if abs(negative[0]) > abs(positive[0]):
        result += abs(negative[0])
        result += positive[0] * 2
    else:
        result += positive[0]
        result += abs(negative[0]) * 2
elif positive:
    result += positive[0]
else:
    result += abs(negative[0])

for i in range(M, len(positive), M):
    result += positive[i] * 2

for i in range(M, len(negative), M):
    result += abs(negative[i]) * 2

print(result)
