#include <iostream>
#include <queue>

using namespace std;

const int MAX_N = 10000 + 1;
int N, from, to, stone[MAX_N];
bool visited[MAX_N] = {false, };

int bfs()
{
    queue<int> q;
    q.push(from);

    int distance = 0;
    visited[from] = true;

    while(!q.empty())
    {
        int qSize = q.size();
        distance++;
        while(qSize--)
        {
            int now = q.front();  q.pop();

            // 앞으로 감
            for(int i = 1; ; ++i)
            {
                int next = now + (stone[now] * i);
                if(next == to) return distance;

                if(next < 1 || next > N) break;
                if(visited[next]) continue;

                visited[next] = true;
                q.push(next);
            }

            // 뒤로 감
            for(int i = 1; ; ++i)
            {
                int next = now - (stone[now] * i);
                if(next == to) return distance;

                if(next < 1 || next > N) break;
                if(visited[next]) continue;

                visited[next] = true;
                q.push(next);
            }

        }
    }

    return -1;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    for(int i=1; i<=N; ++i)
        cin >> stone[i];

    cin >> from >> to;
    cout << bfs();

    return 0;
}
