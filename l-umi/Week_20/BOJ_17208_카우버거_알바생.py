# 32768KB 5708ms
import sys
import copy
input = sys.stdin.readline

N, M, K = map(int, input().rstrip().split())

current = [[0]*(M+1) for _ in range(K+1)]
before = copy.deepcopy(current)

for _ in range(N):
    o1, o2 = map(int, input().rstrip().split())
    for i in range(o2, K+1):
        for j in range(o1, M+1):
            # 각 칸을 돌면서
            current[i][j] = max(before[i][j], before[i-o2][j-o1]+1)

    before = copy.deepcopy(current)
print(current[K][M])


# ====================================================================

# 1번. 냅색X 완탐 시간초과 

# # remaining[i] = 주문개수 i개 일때의 남은 치즈버거와 감자튀김의 양
# remaining = [set() for _ in range(N+1)]
# remaining[0].add((0, 0))
#
# # 첫번째 주문
# order = tuple(map(int, input().rstrip().split()))
# if order[0] <= M and order[1] <= K:
#     remaining[1].add((order[0], order[1]))
#
# for _ in range(N-1) :
#     order = tuple(map(int, input().rstrip().split()))
#     i = 0
#     before = set()
#     while i < N and remaining[i] :
#         current = set()
#         for r in remaining[i] :
#             if r[0]+order[0] <=M and r[1]+order[1] <= K :
#                 current.add((r[0]+order[0], r[1]+order[1]))
#         remaining[i]  |=  before
#         before = current
#         i += 1
#     remaining[i] |= before
# for n in range(N, 0, -1):
#     if remaining[n] :
#         print(n)
#         exit()
# print(0)

# ====================================================================

# 2번. 냅색O 공간효율을 위해 deepCopy이용 32,700KB 7,404ms

# import sys
# import copy
# input = sys.stdin.readline

# N, M, K = map(int, input().rstrip().split())

# current = [[0]*(M+1) for _ in range(K+1)]
# before = copy.deepcopy(current)

# for _ in range(N):
#     order = tuple(map(int, input().rstrip().split()))
#     for i in range(K+1):
#         for j in range(M+1):
#             # 각 칸을 돌면서
#             if order[0] <= j and order[1] <= i :
#                 current[i][j] = max(before[i][j], before[i-order[1]][j-order[0]]+1)
#             else :
#                 current[i][j] = before[i][j]
#     before = copy.deepcopy(current)
# print(current[K][M])

# ====================================================================

# 3번. 냅색O 메모리 최악 104,444KB 6,352ms

# import sys
# input = sys.stdin.readline

# N, M, K = map(int, input().rstrip().split())
# knapsack = [[[0]*(M+1) for _ in range(K+1)] for _ in range(N+1)]

# for n in range(1, N+1):
#     order = tuple(map(int, input().rstrip().split()))
#     for i in range(K+1):
#         for j in range(M+1):
#             if order[0] <= j and order[1] <= i :
#                 knapsack[n][i][j] = max(knapsack[n-1][i][j], knapsack[n-1][i-order[1]][j-order[0]]+1)
#             else :
#                 knapsack[n][i][j] = knapsack[n-1][i][j]
# print(knapsack[N][K][M])