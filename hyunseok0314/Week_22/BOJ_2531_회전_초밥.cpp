#include <stdio.h>

#define MAX_SUSHI 30000
#define MAX_D 3000

int N, d, k, c;
int sushi[MAX_SUSHI];
int kind[MAX_D];

int main() {
  scanf("%d %d %d %d", &N, &d, &k, &c);
  --c;

  for (int i = 0; i < N; ++i) {
    scanf("%d", &sushi[i]);
    --sushi[i];
  }

  int count = 0;

  for (int i = 0; i < k; ++i) {
    if (!kind[sushi[i]]++) {
      ++count;
    }
  }

  int ans;

  if (!kind[c]) {
    ans = count + 1;
  } else {
    ans = count;
  }

  int limit = N - 1;
  for (int i = 0; i < limit; ++i) {
    if (!--kind[sushi[i]]) {
      --count;
    }

    if (!kind[sushi[(i + k) % N]]++) {
      ++count;
    }

    if (!kind[c]) {
      int ncount = count + 1;
      if (ncount > ans) {
        ans = ncount;
      }
    } else {
      if (count > ans) {
        ans = count;
      }
    }
  }

  printf("%d", ans);

  return 0;
}