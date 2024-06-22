#include <stdio.h>

#define MAX 1000
#define KIND 3

int N;
int board[MAX][MAX];

int dp[MAX][MAX][KIND];

int max(int x, int y) {
  if (x > y) {
    return x;
  } else {
    return y;
  }
}

int main() {
  scanf("%d", &N);
  for (int i = 0; i < N; ++i) {
    for (int j = 0; j < N; ++j) {
      scanf("%d", &board[i][j]);
      if (!board[i][j]) {
        ++dp[i][j][0];
      }
    }
  }

  for (int i = 1, p; i < N; ++i) {
    dp[i][0][0] = max(dp[i][0][0], dp[i - 1][0][0]);
    dp[i][0][1] = max(dp[i][0][1], dp[i - 1][0][1]);
    dp[i][0][2] = max(dp[i][0][2], dp[i - 1][0][2]);

    p = (board[i][0] + 2) % KIND;

    if (dp[i - 1][0][p]) {
      dp[i][0][board[i][0]] = max(dp[i][0][board[i][0]], dp[i - 1][0][p] + 1);
    }

    dp[0][i][0] = max(dp[0][i][0], dp[0][i - 1][0]);
    dp[0][i][1] = max(dp[0][i][1], dp[0][i - 1][1]);
    dp[0][i][2] = max(dp[0][i][2], dp[0][i - 1][2]);

    p = (board[0][i] + 2) % KIND;

    if (dp[0][i - 1][p]) {
      dp[0][i][board[0][i]] = max(dp[0][i][board[0][i]], dp[0][i - 1][p] + 1);
    }
  }

  for (int i = 1; i < N; ++i) {
    for (int j = 1, p; j < N; ++j) {
      dp[i][j][0] = max(dp[i][j][0], max(dp[i - 1][j][0], dp[i][j - 1][0]));
      dp[i][j][1] = max(dp[i][j][1], max(dp[i - 1][j][1], dp[i][j - 1][1]));
      dp[i][j][2] = max(dp[i][j][2], max(dp[i - 1][j][2], dp[i][j - 1][2]));

      p = (board[i][j] + 2) % KIND;

      if (dp[i - 1][j][p]) {
        dp[i][j][board[i][j]] = max(dp[i][j][board[i][j]], dp[i - 1][j][p] + 1);
      }

      if (dp[i][j - 1][p]) {
        dp[i][j][board[i][j]] = max(dp[i][j][board[i][j]], dp[i][j - 1][p] + 1);
      }
    }
  }

  --N;

  printf("%d", max(max(dp[N][N][0], dp[N][N][1]), dp[N][N][2]));

  return 0;
}