#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin >> N;
    
    char A[N];
    for(int i = 0; i < N; i++) {
        cin >> A[i]; 
    }
    vector<int> adj[N];
    long long ans = 0;

    for(int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        if(A[u - 1] == '1' && A[v - 1] == '1') {
            ans += 2;
            continue;
        }
        adj[u - 1].push_back(v - 1);
        adj[v - 1].push_back(u - 1);
    }

    bool visited[N] = {};
    for(int i = 0; i < N; i++) {
        if(A[i] == '1' || visited[i]) continue;
        visited[i] = true;
        long long temp = 0;
        queue<int> q;
        q.push(i);
        while(!q.empty()) {
            int now = q.front();
            q.pop();
            for(int next : adj[now]) {
                if(visited[next]) continue;
                if(A[next] == '1') temp++;
                else {
                    visited[next] = true;
                    q.push(next);
                }
            }
        }
        ans += temp * (temp - 1);
    }
    cout << ans;
}