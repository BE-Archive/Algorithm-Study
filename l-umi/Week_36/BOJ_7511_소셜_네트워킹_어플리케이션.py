import sys
input = sys.stdin.readline

class UnionFind:
    def __init__(self, size):
        self.parent = list(range(size))
    
    def find(self, num):
        if num == self.parent[num]:
            return num  # root
        self.parent[num] = self.find(self.parent[num])
        return self.parent[num]
    
    def union(self, a, b):
        a, b = self.find(a), self.find(b)
        self.parent[b] = a

T = int(input())

for tc in range(1, T + 1):
    print(f"Scenario {tc}:")
    n = int(input()  
    k = int(input())
    uf = UnionFind(n)
    
    for _ in range(k):
        a, b = map(int, input().split()) 
        uf.union(a, b)
    
    m = int(input())  
    for _ in range(m):
        u, v = map(int, input().split())
        print("1" if uf.find(u) == uf.find(v) else "0")
    
    print()