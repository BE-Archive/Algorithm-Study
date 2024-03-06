#include<iostream>
#include<queue>
#include<vector>
using namespace std;
int INF = 100000;
vector<pair<int, int>>a1[1001];
vector<pair<int, int>>a2[1001];
int d[1001];
int ans[1001];
int n, m, x, u, v, w;
void dijkstra(int start, vector<pair<int, int>>arr[1001]);
int main()
{
	cin.tie(0);
	cin.sync_with_stdio(false);
	cin >> n >> m >> x;
	for (int i = 0; i < m; i++)
	{
		cin >> u >> v >> w;
		a1[u].push_back({ v,w });
		a2[v].push_back({ u,w });
	}
	dijkstra(x, a1);
	dijkstra(x, a2);
	int max = 0;
	for (int i = 1; i <= n; i++)
	{
		if (max < ans[i])
			max = ans[i];
	}
	cout << max << '\n';
}
void dijkstra(int start, vector<pair<int, int>>arr[1001])
{
	for (int i = 1; i <= n; i++)
		d[i] = INF;
	d[start] = 0;
	priority_queue<pair<int, int>>pq;
	pq.push({ 0,start });
	while (!pq.empty())
	{
		int current = pq.top().second;
		int distance = -pq.top().first;
		pq.pop();
		if (d[current] < distance)
			continue;
		for (int i = 0; i < arr[current].size(); i++)
		{
			int next = arr[current][i].first;
			int next_distance = arr[current][i].second + distance;
			if (d[next] > next_distance)
			{
				d[next] = next_distance;
				pq.push(make_pair(-next_distance, next));
			}
		}
	}
	for (int i = 1; i <= n; i++)
		ans[i] += d[i];
}