#include <stdio.h>

#define MAX 100
#define MAX_W 3000001

int pq[(MAX * MAX) << 2][4];
int pq_size;

void insert(int x, int y, int w, int b) {
  pq[pq_size][0] = x;
  pq[pq_size][1] = y;
  pq[pq_size][2] = w;
  pq[pq_size][3] = b;
  int curr = pq_size++;

  while (curr > 0) {
    int p = (curr - 1) >> 1;
    if (pq[p][2] < pq[curr][2]) {
      break;
    }

    pq[p][0] ^= pq[curr][0];
    pq[curr][0] ^= pq[p][0];
    pq[p][0] ^= pq[curr][0];

    pq[p][1] ^= pq[curr][1];
    pq[curr][1] ^= pq[p][1];
    pq[p][1] ^= pq[curr][1];

    pq[p][2] ^= pq[curr][2];
    pq[curr][2] ^= pq[p][2];
    pq[p][2] ^= pq[curr][2];

    pq[p][3] ^= pq[curr][3];
    pq[curr][3] ^= pq[p][3];
    pq[p][3] ^= pq[curr][3];

    curr = p;
  }
}

void remove() {
  --pq_size;
  pq[0][0] = pq[pq_size][0];
  pq[0][1] = pq[pq_size][1];
  pq[0][2] = pq[pq_size][2];
  pq[0][3] = pq[pq_size][3];

  int curr = 0;
  while (curr < pq_size) {
    int m = curr;
    int l = (m << 1) + 1;
    int r = (m << 1) + 2;

    if (l < pq_size && pq[l][2] < pq[m][2]) {
      m = l;
    }

    if (r < pq_size && pq[r][2] < pq[m][2]) {
      m = r;
    }

    if (m != curr) {
      pq[curr][0] ^= pq[m][0];
      pq[m][0] ^= pq[curr][0];
      pq[curr][0] ^= pq[m][0];

      pq[curr][1] ^= pq[m][1];
      pq[m][1] ^= pq[curr][1];
      pq[curr][1] ^= pq[m][1];

      pq[curr][2] ^= pq[m][2];
      pq[m][2] ^= pq[curr][2];
      pq[curr][2] ^= pq[m][2];

      pq[curr][3] ^= pq[m][3];
      pq[m][3] ^= pq[curr][3];
      pq[curr][3] ^= pq[m][3];

      curr = m;
    } else {
      break;
    }
  }
}

int N, M, sx, sy, ex, ey;
int board[MAX][MAX];
int dist[MAX][MAX][3];

int d0[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
int d1[2][2] = {{1, 0}, {-1, 0}};
int d2[2][2] = {{0, 1}, {0, -1}};

int main() {
  scanf("%d %d", &N, &M);
  scanf("%d %d %d %d", &sx, &sy, &ex, &ey);
  --sx;
  --sy;
  --ex;
  --ey;

  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < M; ++j) {
      scanf("%d", &board[i][j]);
      dist[i][j][0] = MAX_W;
      dist[i][j][1] = MAX_W;
      dist[i][j][2] = MAX_W;
    }
  }

  dist[sx][sy][1] = 0;
  insert(sx, sy, 0, 1);
  while (pq_size > 0) {
    int x = pq[0][0];
    int y = pq[0][1];
    int w = pq[0][2];
    int b = pq[0][3];
    remove();

    if (dist[x][y][b] < w) {
      continue;
    }

    int nb = (b + 1) % 3;

    switch (b) {
      case 0:
        for (int i = 0; i < 4; i++) {
          int nx = x + d0[i][0];
          int ny = y + d0[i][1];

          if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == -1) {
            continue;
          }

          int nw = w + board[nx][ny];
          if (dist[nx][ny][nb] > nw) {
            dist[nx][ny][nb] = nw;
            insert(nx, ny, nw, nb);
          }
        }
        break;
      case 1:
        for (int i = 0; i < 2; i++) {
          int nx = x + d1[i][0];
          int ny = y + d1[i][1];

          if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == -1) {
            continue;
          }

          int nw = w + board[nx][ny];
          if (dist[nx][ny][nb] > nw) {
            dist[nx][ny][nb] = nw;
            insert(nx, ny, nw, nb);
          }
        }
        break;
      case 2:
        for (int i = 0; i < 2; i++) {
          int nx = x + d2[i][0];
          int ny = y + d2[i][1];

          if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == -1) {
            continue;
          }

          int nw = w + board[nx][ny];
          if (dist[nx][ny][nb] > nw) {
            dist[nx][ny][nb] = nw;
            insert(nx, ny, nw, nb);
          }
        }
        break;
    }
  }

  int ans = MAX_W;

  for (int i = 0; i < 3; i++) {
    if (dist[ex][ey][i] < ans) {
      ans = dist[ex][ey][i];
    }
  }

  if (ans == MAX_W) {
    printf("-1");
  } else {
    printf("%d", ans);
  }

  return 0;
}