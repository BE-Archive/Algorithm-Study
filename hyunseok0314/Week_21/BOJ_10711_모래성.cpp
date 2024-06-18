#include <stdio.h>

#define MAX 1000
#define MAX_SQR 1000000
#define MAX_D 8

int q[MAX_SQR][2];
int head = 0;
int tail = 0;
int q_size = 0;

void insert(int x, int y) {
  q[tail][0] = x;
  q[tail][1] = y;
  tail = (tail + 1) % MAX_SQR;
  ++q_size;
}

void remove() {
  head = (head + 1) % MAX_SQR;
  --q_size;
}

int H, W;
char board[MAX][MAX + 1];

int d[MAX_D][2] = {{0, 1}, {1, 0},  {0, -1}, {-1, 0},
                   {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

int main() {
  scanf("%d %d", &H, &W);
  for (int i = 0; i < H; ++i) {
    scanf("%s", board[i]);
  }

  for (int i = 0; i < H; ++i) {
    for (int j = 0; j < W; ++j) {
      if (board[i][j] == '.') {
        insert(i, j);
        board[i][j] = 0;
      } else {
        board[i][j] -= '0';
      }
    }
  }

  int count = 0;
  while (q_size > 0) {
    ++count;

    for (int i = q_size; i > 0; --i) {
      int x = q[head][0];
      int y = q[head][1];
      remove();

      for (int j = 0; j < MAX_D; ++j) {
        int nx = x + d[j][0];
        int ny = y + d[j][1];
        if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == 0) {
          continue;
        }

        if (--board[nx][ny] == 0) {
          insert(nx, ny);
        }
      }
    }
  }

  --count;

  printf("%d", count);

  return 0;
}