x = int(input())
line = list(input())

M = 0
idx = 0
while True:
    tmp = M
    try:
        if line[idx] == 'M' and M == x:
            line[idx], line[idx + 1] = line[idx + 1], line[idx]
        elif line[idx] == 'W' and M == x * (-1):
            line[idx], line[idx + 1] = line[idx + 1], line[idx]
    except:
        break
    now = line[idx]
    if now == 'M':
        tmp += 1
    else:
        tmp -= 1
    if abs(tmp) > x or idx == len(line):
        break
    else:
        idx += 1
        M = tmp
print(idx)
