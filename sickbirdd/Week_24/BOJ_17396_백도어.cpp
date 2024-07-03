#include <bits/stdc++.h>
using namespace std;
long long INF = 1e13;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, M;
    cin >> N >> M;

    int A[N];
    long long dist[N];
    for(int i = 0; i < N; i++) {
        cin >> A[i];
        dist[i] = INF;
    }
    A[N - 1] = 0;
    vector<pair<long long, int>> adj[N];
    for(int i = 0; i < M; i++) {
        int S, E;
        long long T;
        cin >> S >> E >> T;
        if(A[S] || A[E]) continue;
        adj[S].push_back({T, E});
        adj[E].push_back({T, S});
    }

    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    dist[0] = 0;
    pq.push({dist[0], 0});

    while(!pq.empty()) {
        pair<long long, int> now = pq.top();
        pq.pop();
        if(now.first > dist[now.second] || now.second == N - 1) continue;
        for(pair<long long, int> next: adj[now.second]) {
            if(dist[next.second] > dist[now.second] + next.first) {
                dist[next.second] = dist[now.second] + next.first;
                pq.push({dist[next.second], next.second});
            }
        }
    }

    cout << (dist[N - 1] == INF ? -1 : dist[N - 1]);
}