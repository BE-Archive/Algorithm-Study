#include <stdio.h>

#define MAX_N 11
#define MAX_H 31
#define MAX 300

int N, M, H;
int lines[MAX_N][MAX_H];
int ans = MAX;

bool check() {
  for (int i = 1; i < N; ++i) {
    int curr = i;
    for (int j = 1; j <= H; ++j) {
      if (lines[curr][j] != 0) {
        curr = lines[curr][j];
      }
    }
    if (curr != i) {
      return false;
    }
  }
  return true;
}

int min(int x, int y) {
  if (x < y) {
    return x;
  }
  return y;
}

void find_min(int count) {
  if (check()) {
    ans = min(ans, count);
    return;
  }

  if (count >= ans || count == 3) {
    return;
  }

  for (int i = 1; i < N; ++i) {
    bool flag = false;
    for (int j = 1; j <= H; ++j) {
      if (flag && lines[i][j] == 0 && lines[i + 1][j] == 0) {
        continue;
      }

      flag = false;

      if (lines[i][j] == 0 && lines[i + 1][j] == 0) {
        lines[i][j] = i + 1;
        lines[i + 1][j] = i;
        find_min(count + 1);
        lines[i][j] = 0;
        lines[i + 1][j] = 0;
        flag = true;
      }
    }
  }
}

int main() {
  scanf("%d %d %d\n", &N, &M, &H);

  for (int i = 0; i < M; ++i) {
    int n, h;
    scanf("%d %d", &h, &n);

    lines[n][h] = n + 1;
    lines[n + 1][h] = n;
  }

  find_min(0);

  if (ans == MAX) {
    printf("-1");
  } else {
    printf("%d", ans);
  }

  return 0;
}