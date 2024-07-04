#include <iostream>
#include <memory.h>
#include <queue>
#include <algorithm>
#include <vector>

typedef std::pair<int, int> pii;
typedef std::pair<pii, int> T;

const int SIZE = 125 + 1;
int N, answer = 0;
int map[SIZE][SIZE] = {0, };
bool visited[SIZE][SIZE] = {false, };

const int dx[4] = {0, 0, 1, -1};
const int dy[4] = {1, -1, 0, 0};
bool isValid(const int x, const int y)
{
	if(x<0 || y<0 || x>=N || y>=N)
		return false;
	return true;
}

struct cmp
{
	bool operator()(const T& a, const T& b)
	{
		return a.second > b.second;
	}
};

void solve()
{
	std::priority_queue<T, std::vector<T>, cmp> pq;
	pq.push({{0, 0}, map[0][0]});
	visited[0][0] = true;

	while(!pq.empty())
	{
		int _x = pq.top().first.first;
		int _y = pq.top().first.second;
		int _v = pq.top().second;
		pq.pop();

		for(int d=0; d<4; ++d)
		{
			int x = _x + dx[d];
			int y = _y + dy[d];

			if(!isValid(x, y) || visited[x][y])
				continue;

			if(x == N-1 && y == N-1)
			{
				answer = _v + map[x][y];
				return;
			}

			pq.push({{x, y}, _v + map[x][y]});
			visited[x][y] = true;
		}
	}
}

int main()
{
	for(int tc=1; ;++tc)
	{
		std::cin >> N;
		if(N == 0) break;
		
		memset(visited, false, sizeof(visited));
		for(int i=0; i<N; ++i)
		{
			for(int j=0; j<N; ++j)
				std::cin >> map[i][j];
		}

		solve();

		std::printf("Problem %d: %d\n", tc, answer);
	}

	return 0;
}
