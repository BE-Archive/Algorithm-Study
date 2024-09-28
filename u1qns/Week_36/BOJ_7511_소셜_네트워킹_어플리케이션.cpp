#include <iostream>
#include <vector>

using namespace std;

class UnionFind {
public:
    UnionFind(int size)
    {
        parent.resize(size, -1);
    }

    int find(int x)
    {
        if (parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    bool merge(int x, int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY)
            return false;
        
        if(parent[rootX] < parent[rootY])
        {
            parent[rootY] += parent[rootX];
            parent[rootX] = rootY;
        }
        else
        {
            parent[rootX] += parent[rootY];
            parent[rootY] = rootX;
        }
        return true;
    }

private:
    vector<int> parent;
};

int main() 
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for(int tc = 1; tc <= T; ++tc)
    {
        cout << "Scenario " << tc << ":" << "\n";

        int n, k, a, b, m;
        cin >> n >> k;
        UnionFind uf(n + 1);
        
        for(int i = 0; i < k; ++i)
        {
            cin >> a >> b;
            uf.merge(a, b);
        }
        
        cin >> m; 
        for(int i = 0; i < m; ++i)
        {
            cin >> a >> b;
            cout << ((uf.find(a) == uf.find(b)) ? "1\n" : "0\n");
        }
        cout << "\n";
    }
    return 0;
}
