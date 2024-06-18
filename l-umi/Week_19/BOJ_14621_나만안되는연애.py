import sys

# 부모 노드 찾기
def getParent(x):
    global parent
    if x != parent[x]:
        parent[x] = getParent(parent[x])
    return parent[x]


# 트리 연결
def unionParent(x, y):
    global parent
    x_parent = getParent(x)
    y_parent = getParent(y)
    if x_parent < y_parent:
        parent[y_parent] = x_parent
    else:
        parent[x_parent] = y_parent


def kruskal():
    global edges
    cost = 0
    edgeCnt = 0
    for edge in edges:
        node_1, node_2, weight = edge
        # 서로 연결되어 있지 않는 경우
        if getParent(node_1) != getParent(node_2):
            edgeCnt += 1
            cost += weight
            unionParent(node_1, node_2)

    # 모두 연결 가능한지 판단 (방법 1)
    # root = getParent(1)
    # for i in range(2, N+1) :
    #     if root != getParent(i) :
    #         return -1
    # return cost

    # 모두 연결 가능한지 판단 (방법 2)
    # if len(set(map(getParent, parent))) == 2 :
    #     return cost
    # else :
    #     return -1

    # 모두 연결 가능한지 판단 (방법 3)
    if edgeCnt + 1 == N :
        return cost
    else :
        return -1



N, M = map(int, input().split(" "))
parent = [i for i in range(N+1)]
university = list(sys.stdin.readline().split())
edges = []
for _ in range(M):
    node_1, node_2, weight = map(int, input().split())
    if university[node_1 - 1] != university[node_2 - 1] :
        # 남초-남초, 여초-여초 연결하는 도로 넣지말기
        edges.append([node_1,node_2, weight])
edges.sort(key = lambda l : l[2])
print(kruskal())

