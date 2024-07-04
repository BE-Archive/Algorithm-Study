import sys

input_func = sys.stdin.readline

def find_parent(parents, node):
    if parents[node] != node:
        parents[node] = find_parent(parents, parents[node])
    return parents[node]

def union(parents, a, b):
    a_root = find_parent(parents, a)
    b_root = find_parent(parents, b)
    if a_root < b_root:
        parents[b_root] = a_root
    else:
        parents[a_root] = b_root

num_schools, num_roads = map(int, input_func().split())
school_info = list(input_func().split())
edges = []

for _ in range(num_roads):
    u, v, distance = map(int, input_func().split())
    edges.append((distance, u - 1, v - 1))

edges.sort()

total_distance, count = 0, 0
parents = [i for i in range(num_schools)]

for distance, u, v in edges:
    if school_info[u] != school_info[v] and find_parent(parents, u) != find_parent(parents, v):
        union(parents, u, v)
        total_distance += distance
        count += 1
    if count == num_schools - 1:
        break

print(total_distance) if count == num_schools - 1 else print(-1)
