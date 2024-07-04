#include <iostream>

#define INT_MAX_VALUE 2140000000

using namespace std;

#include <queue>

struct Node
{
  int x;
  int y;
  int w;
  int dir;

  bool operator>(const Node &other) const
  {
    return w > other.w;
  }
};

// class PQ {
//  private:
//   Node* heap;
//   int capacity;
//   int size;

//   void ensureCapacity() {
//     if (size == capacity) {
//       Node* newHeap = new Node[capacity * 2];
//       for (int i = 0; i < size; ++i) {
//         newHeap[i] = heap[i];
//       }
//       delete[] heap;
//       heap = newHeap;
//       capacity *= 2;
//     }
//   }

//   void heapifyUp(int index) {
//     if (index == 0) return;  // 루트 노드인 경우 종료
//     int parentIndex = (index - 1) / 2;
//     if (heap[index] > heap[parentIndex]) {
//       swap(heap[index], heap[parentIndex]);
//       heapifyUp(parentIndex);
//     }
//   }

//   void heapifyDown(int index) {
//     int leftIndex = 2 * index + 1;
//     int rightIndex = 2 * index + 2;
//     int largest = index;

//     if (leftIndex < size && heap[leftIndex] > heap[largest]) {
//       largest = leftIndex;
//     }

//     if (rightIndex < size && heap[rightIndex] > heap[largest]) {
//       largest = rightIndex;
//     }

//     if (largest != index) {
//       swap(heap[index], heap[largest]);
//       heapifyDown(largest);
//     }
//   }

//  public:
//   PQ(int initialCapacity = 10) : capacity(initialCapacity), size(0) {
//     heap = new Node[capacity];
//   }

//   ~PQ() {
//     delete[] heap;
//   }

//   void push(const Node& value) {
//     ensureCapacity();
//     heap[size] = value;
//     heapifyUp(size);
//     ++size;
//   }

//   void pop() {
//     if (size == 0) throw runtime_error("Priority queue is empty");
//     heap[0] = heap[size - 1];
//     --size;
//     heapifyDown(0);
//   }

//   Node top() const {
//     if (size == 0) throw runtime_error("Priority queue is empty");
//     return heap[0];
//   }

//   bool empty() const {
//     return size == 0;
//   }

//   int getSize() const {
//     return size;
//   }
// };

int main()
{
  // initial start
  int N, M;
  cin >> N >> M;

  int sX, sY, eX, eY;
  cin >> sX >> sY >> eX >> eY;
  sX--;
  sY--;
  eX--;
  eY--;
  // cout << sX << sY << eX << eY << endl;

  int dx[3][4] = {{1, 0, -1, 0}, {0, 1}, {1, 0}};
  int dy[3][4] = {{0, 1, 0, -1}, {0, -1}, {-1, 0}};

  int map[N][M];
  int visited[N][M][3];

  for (int i = 0; i < N; i++)
  {
    for (int j = 0; j < M; j++)
    {
      cin >> map[i][j];
      visited[i][j][0] = INT_MAX_VALUE;
      visited[i][j][1] = INT_MAX_VALUE;
      visited[i][j][2] = INT_MAX_VALUE;
    }
  }
  // initial end

  priority_queue<Node, vector<Node>, greater<Node>> pq;
  visited[sX][sY][1] = 0;
  pq.push({sX, sY, 0, 1}); // 시작x, 시작y, 가중치, 방향

  while (!pq.empty())
  {
    Node node = pq.top();
    int x = node.x;
    int y = node.y;
    int w = node.w;
    int dir = node.dir;
    // cout << " (" << x << ", " << y << " )" << w << " " << dir << " " << visited[x][y][dir] << endl;
    pq.pop();
    if (w > visited[x][y][dir])
      continue;

    int ddir = (dir + 1) % 3;

    int cnt;
    switch (dir)
    {
    case 0: // 상하좌우
      cnt = 4;
      break;
    case 1: // 상하
    case 2: // 좌우
      cnt = 2;
      break;
    }
    for (int i = 0; i < cnt; i++)
    {
      int ddx = x + dx[dir][i];
      int ddy = y + dy[dir][i];

      // 범위를 벗어나거나, 벽이면 continue
      if (ddx < 0 || ddx >= N || ddy < 0 || ddy >= M || map[ddx][ddy] == -1)
        continue;

      int dw = w + map[ddx][ddy];
      if (visited[ddx][ddy][ddir] > dw)
      {
        visited[ddx][ddy][ddir] = dw;
        pq.push({ddx, ddy, dw, ddir});
      }
    }
  }
  int ans = INT_MAX_VALUE;
  for (int i = 0; i < 3; i++)
  {
    if (visited[eX][eY][i] < ans)
    {
      ans = visited[eX][eY][i];
    }
  }

  if (ans == INT_MAX_VALUE)
  {
    cout << -1 << endl;
  }
  else
  {
    cout << ans << endl;
  }

  return 0;
}