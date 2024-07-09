#include <stdio.h>

#define MAX 50
#define MAX_SQR 2500

int N, sx, sy, ex, ey, sd, ed, sc, ec;
char board[MAX][MAX];
bool visited[MAX][MAX][2];

int q[MAX_SQR][4];
int head = 0;
int tail = 0;
int q_size = 0;

void insert(int x, int y, int d, int c) {
  q[tail][0] = x;
  q[tail][1] = y;
  q[tail][2] = d;
  q[tail][3] = c;
  tail = (tail + 1) % MAX_SQR;
  ++q_size;
}

void remove() {
  head = (head + 1) % MAX_SQR;
  --q_size;
}

int bfs() {
  visited[sx][sy][sd] = true;

  while (q_size > 0) {
    int x = q[head][0];
    int y = q[head][1];
    int d = q[head][2];
    int c = q[head][3];
    remove();

    if (d) {
      if (y - 1 >= 0 && board[x - 1][y - 1] == '0' && board[x][y - 1] == '0' &&
          board[x + 1][y - 1] == '0' && !visited[x][y - 1][d]) {
        if (x == ex && y - 1 == ey && d == ed) {
          return c + 1;
        }

        visited[x][y - 1][d] = true;
        insert(x, y - 1, d, c + 1);
      }

      if (y + 1 < N && board[x - 1][y + 1] == '0' && board[x][y + 1] == '0' &&
          board[x + 1][y + 1] == '0' && !visited[x][y + 1][d]) {
        if (x == ex && y + 1 == ey && d == ed) {
          return c + 1;
        }
        visited[x][y + 1][d] = true;
        insert(x, y + 1, d, c + 1);
      }

      if (x - 2 >= 0 && board[x - 2][y] == '0' && !visited[x - 1][y][d]) {
        if (x - 1 == ex && y == ey && d == ed) {
          return c + 1;
        }
        visited[x - 1][y][d] = true;
        insert(x - 1, y, d, c + 1);
      }

      if (x + 2 < N && board[x + 2][y] == '0' && !visited[x + 1][y][d]) {
        if (x + 1 == ex && y == ey && d == ed) {
          return c + 1;
        }
        visited[x + 1][y][d] = true;
        insert(x + 1, y, d, c + 1);
      }

      if (y - 1 >= 0 && y + 1 < N && board[x - 1][y - 1] == '0' &&
          board[x][y - 1] == '0' && board[x + 1][y - 1] == '0' &&
          board[x - 1][y + 1] == '0' && board[x][y + 1] == '0' &&
          board[x + 1][y + 1] == '0' && !visited[x][y][d ^ 1]) {
        if (x == ex && y == ey && (d ^ 1) == ed) {
          return c + 1;
        }
        visited[x][y][d ^ 1] = true;
        insert(x, y, d ^ 1, c + 1);
      }
    } else {
      if (x - 1 >= 0 && board[x - 1][y - 1] == '0' && board[x - 1][y] == '0' &&
          board[x - 1][y + 1] == '0' && !visited[x - 1][y][d]) {
        if (x - 1 == ex && y == ey && d == ed) {
          return c + 1;
        }
        visited[x - 1][y][d] = true;
        insert(x - 1, y, d, c + 1);
      }

      if (x + 1 < N && board[x + 1][y - 1] == '0' && board[x + 1][y] == '0' &&
          board[x + 1][y + 1] == '0' && !visited[x + 1][y][d]) {
        if (x + 1 == ex && y == ey && d == ed) {
          return c + 1;
        }
        visited[x + 1][y][d] = true;
        insert(x + 1, y, d, c + 1);
      }

      if (y - 2 >= 0 && board[x][y - 2] == '0' && !visited[x][y - 1][d]) {
        if (x == ex && y - 1 == ey && d == ed) {
          return c + 1;
        }
        visited[x][y - 1][d] = true;
        insert(x, y - 1, d, c + 1);
      }

      if (y + 2 < N && board[x][y + 2] == '0' && !visited[x][y + 1][d]) {
        if (x == ex && y + 1 == ey && d == ed) {
          return c + 1;
        }
        visited[x][y + 1][d] = true;
        insert(x, y + 1, d, c + 1);
      }

      if (x - 1 >= 0 && x + 1 < N && board[x - 1][y - 1] == '0' &&
          board[x - 1][y] == '0' && board[x - 1][y + 1] == '0' &&
          board[x + 1][y - 1] == '0' && board[x + 1][y] == '0' &&
          board[x + 1][y + 1] == '0' && !visited[x][y][d ^ 1]) {
        if (x == ex && y == ey && (d ^ 1) == ed) {
          return c + 1;
        }
        visited[x][y][d ^ 1] = true;
        insert(x, y, d ^ 1, c + 1);
      }
    }
  }

  return 0;
}

int main() {
  scanf("%d\n", &N);

  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < N; ++j) {
      scanf("%c", &board[i][j]);
      if (board[i][j] == 'B') {
        board[i][j] = '0';
        if (sc < 2) {
          if (sc == 1) {
            if (i - sx == 1) {
              sd = 1;
            }
          }
          sx = i;
          sy = j;
        }
        ++sc;
      }

      if (board[i][j] == 'E') {
        board[i][j] = '0';
        if (ec < 2) {
          if (ec == 1) {
            if (i - ex == 1) {
              ed = 1;
            }
          }
          ex = i;
          ey = j;
        }
        ++ec;
      }
    }
    scanf("\n");
  }

  visited[sx][sy][sd] = true;
  insert(sx, sy, sd, 0);

  printf("%d", bfs());

  return 0;
}