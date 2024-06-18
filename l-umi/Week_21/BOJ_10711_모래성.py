import sys
from collections import deque
input = sys.stdin.readline

H, W = map(int, input().split())
sand = [ list(input().rstrip()) for _ in range(H) ]

wave = deque()
for h in range(H) :
    for w in range(W):
        if sand[h][w] == '.' :
            sand[h][w] = 0
            wave.append((h,w))
        else :
            sand[h][w] = int(sand[h][w])

############################################################
dh, dw =(-1, -1, -1, 0, 1, 1, 1, 0), (-1, 0, 1, 1, 1, 0, -1, -1)
answer=0
while(wave) :
    for _ in range(len(wave)):
        water = wave.popleft()
        for i in range(8):
            h = water[0]+dh[i]
            w = water[1]+dw[i]
            if h>=0 and w>=0 and h < H and w < W  and sand[h][w] > 0:
                sand[h][w] -= 1
                if(sand[h][w] == 0) :
                    wave.append((h,w))
    answer+=1

print(answer-1 if answer>0 else 0)
