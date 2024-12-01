#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, dest;
int answer = 1e9;
vector<int> adj[11];
vector<int> start;
bool check[11];

void dfs(int x, int time)
{
    check[x] = true;
    for(int i=0; i<adj[x].size(); ++i)
    {
        if(adj[x][i] == dest) // x와 같은 호선에서 end 발견
        {
            answer = min(answer, time);
            check[x] = false;
            return;
        }

        // 다른 호선 j 로 갈 수 있는지 본다. 
        for(int j=0; j<N; ++j)
        {
            if(j == x) continue; // 기존 호선이라면 패스!
          
            for(int k=0; k<adj[j].size(); ++j) // j 역에서 갈 수 있는 곳에서 
            {
                // 1. 아직 j 호선 탐색 안 했음
                // 2. j 호선에 k번째 역이랑 x호선의 i번째랑 같은 역이면 갈 수 있음
                if(check[j] || adj[j][k] != adj[x][i]) continue; 
              
                dfs(j, time+1);
            }
        }
    }
    check[x] = false; // dfs 로 다른 경로로 확인하기 위해 false
}

int main() {

    // input
    cin >> N;
    int K, station;
    for(int i=0; i<N; i++)
    {
        cin >> K;
        for(int j=0; j<K; j++)
        {
            cin >> station;
            adj[i].push_back(station);

            if(station == 0)
                start.push_back(i);
        }
    }
    cin >> dest;

    for(int i=0; i<start.size(); ++i)
        dfs(start[i],0);

  	cout << (answer == 1e9 ? -1 : answer);
    return 0;

}
