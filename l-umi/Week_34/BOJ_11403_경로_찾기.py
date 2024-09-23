def floyd_warshall(graph):
    N = len(graph)
    
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if graph[i][k] and graph[k][j]:
                    graph[i][j] = 1
    
    return graph

##################################################################
N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]

result = floyd_warshall(graph)


##################################################################
for row in result:
    print(' '.join(map(str, row)))
