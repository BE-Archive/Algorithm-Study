#include <stdio.h>

#define ll long long
#define MAX 6

int N;
int dice[MAX];

int min(int x, int y) {
  if (x < y) {
    return x;
  }
  return y;
}

int max(int x, int y) {
  if (x < y) {
    return y;
  }
  return x;
}

int main() {
  scanf("%d", &N);

  for (int i = 0; i < MAX; ++i) {
    scanf("%d", &dice[i]);
  }

  ll ans = 0;

  if (N == 1) {
    int m = 0;
    for (int i = 0; i < MAX; ++i) {
      ans += dice[i];
      m = max(m, dice[i]);
    }

    printf("%lld", ans - m);

    return 0;
  }

  ll m1 = 50;
  ll m2 = 100;
  ll m3 = 150;

  for (int i = 0; i < MAX; ++i) {
    m1 = min(m1, dice[i]);
    for (int j = i + 1; j < MAX; ++j) {
      if ((i == 0 && j == 5) || (i == 1 && j == 4) || (i == 2 && j == 3)) {
        continue;
      }

      m2 = min(m2, dice[i] + dice[j]);
    }
  }

  m3 = min(m3, dice[0] + dice[1] + dice[2]);
  m3 = min(m3, dice[0] + dice[1] + dice[3]);
  m3 = min(m3, dice[0] + dice[4] + dice[2]);
  m3 = min(m3, dice[0] + dice[4] + dice[3]);
  m3 = min(m3, dice[5] + dice[1] + dice[2]);
  m3 = min(m3, dice[5] + dice[1] + dice[3]);
  m3 = min(m3, dice[5] + dice[4] + dice[2]);
  m3 = min(m3, dice[5] + dice[4] + dice[3]);

  int N1 = N - 1;
  int N2 = N - 2;
  int max2 = MAX - 2;

  ans += m1 * N2 * N2;
  ans += m1 * N2 * N1 * max2;
  ans += m2 * N1 * max2;
  ans += m2 * N2 * max2;
  ans += m3 * max2;

  printf("%lld", ans);

  return 0;
}