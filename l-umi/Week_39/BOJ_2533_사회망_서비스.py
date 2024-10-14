import sys
sys.setrecursionlimit(10 ** 9)
input = sys.stdin.readline


N = int(input())
graph = [[] for _ in range(N+1)]
dp = [0] * (N+1)
visited = [0] * (N+1)

for _ in range(N-1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)
###############################

def dfs(start):
    visited[start] = 1
    for child in graph[start]:
        if not visited[child]:
            dfs(child)
            if not dp[child]:
                dp[start] = 1
###############################

dfs(1)  
print(sum(dp))