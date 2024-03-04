#include <iostream>
#include <queue>
#include <algorithm>
#include <memory.h>
#include <vector>

typedef std::pair<int, int> pii;
typedef std::pair<int, pii> T;

const int N_MAX = 10 + 1;
const int ISLAND_MAX = 7;

const int dx[4] = { 0, -1, 1, 0 };
const int dy[4] = { 1, 0, 0, -1 };

int N, M;
bool visited[N_MAX][N_MAX] = { false, };
int map[N_MAX][N_MAX] = { 0, };
int root[ISLAND_MAX];
std::vector<T> weight;

pii tmp;
int x, y;

bool isValid(const int x, const int y)
{
	if (x < 0 || y < 0 || x >= N || y >= M)
		return false;
	return true;
}

void show()
{
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < M; ++j)
		{
			std::cout << map[i][j] << " ";
		}
		std::cout << "\n";
	}
}

int find(const int x)
{
	if (root[x] < 0) return x;
	return root[x] = find(root[x]);
}

bool merge(const int x, const int y)
{
	int r1 = find(x);
	int r2 = find(y);

	if (r1 == r2) return false;

	if (root[r1] > root[r2])
	{
		root[r1] += root[r2];
		root[r2] = r1;
	}
	else
	{
		root[r2] += root[r1];
		root[r1] = r2;
	}
	return true;
}

void find_islands(const int _x, const int _y, const int idx, std::vector<pii>& output)
{
	std::queue<pii> q;
	q.emplace(_x, _y);

	output.push_back({ _x, _y });
	map[_x][_y] = idx;


	while (!q.empty())
	{
		tmp = q.front();
		q.pop();

		for (int d = 0; d < 4; ++d)
		{
			x = tmp.first + dx[d];
			y = tmp.second + dy[d];

			if (!isValid(x, y) || visited[x][y] || map[x][y] != 1)
				continue;

			visited[x][y] = true;
			q.emplace(x, y);
			output.push_back({ x, y });
			map[x][y] = idx;
		}
	}

}

int make_bridge(const int _x, const int _y, const int from)
{
	int to = 0;
	for (int d = 0; d < 4; ++d)
	{
		x = _x; y = _y;

		for (int len = 0; ; ++len)
		{
			x += dx[d]; y += dy[d];
			to = map[x][y];

			if (!isValid(x, y) || from == to) break;

			if (to != 0 && from != to)
			{
				if (len > 1)
					weight.push_back({ len, {to, from} });
				break;
			}

		}
	}

	return -1;
}

int getAnswer()
{
	int answer = 0;
	int idx = 0;

	// 1. 섬의 위치와 개수를 파악한다.
	std::vector<std::vector<pii>> islands;
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < M; ++j)
		{
			if (map[i][j] == 1 && !visited[i][j])
			{
				std::vector<pii> input;
				find_islands(i, j, ++idx, input);
				islands.push_back(input);
			}
		}
	}

	//2. 섬에서부터 다리를 찾아보자 -> bridge 연결할 때 최소 연결 다리 생성
	std::fill(root, root + islands.size()+1, -1);
	idx = 0;
	for (const auto& pos : islands) // 한 섬을 꺼내서
	{
		++idx;
		for (const auto&[x, y] : pos) // 그 섬의 모든 위치에서 다리를 만들어보자
		{
			make_bridge(x, y, idx);
		}
	}


	std::sort(weight.begin(), weight.end());
	for (int i = 0; i < weight.size(); ++i)
	{
		if (merge(weight[i].second.first, weight[i].second.second))
		{
			answer += weight[i].first;
		}
	}

	for (int i = 1; i <= islands.size(); ++i)
	{
		if (root[i] * -1 == islands.size())
			return answer;
	}

	return -1;
}


int main()
{
	std::cin >> N >> M;


	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < M; ++j)
		{
			std::cin >> map[i][j];
		}
	}

	std::cout << getAnswer();

	return 0;
}
