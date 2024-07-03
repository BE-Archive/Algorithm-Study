#include <stdio.h>

#define MAX_N 100000

int N;
int b[MAX_N];
int b_count[MAX_N];
int b_closest[MAX_N];

int s[MAX_N][2];
int s_size;

int main() {
  scanf("%d", &N);

  for (int i = 0; i < N; ++i) {
    scanf("%d", &b[i]);
  }

  s_size = 0;

  for (int i = 0; i < N; ++i) {
    while (s_size > 0 && s[s_size - 1][0] <= b[i]) {
      --s_size;
    }

    if (s_size) {
      b_closest[i] = s[s_size - 1][1];
      b_count[i] += s_size;
    }

    s[s_size][0] = b[i];
    s[s_size][1] = i;
    ++s_size;
  }

  s_size = 0;

  for (int i = N - 1; i >= 0; --i) {
    while (s_size > 0 && s[s_size - 1][0] <= b[i]) {
      --s_size;
    }

    if (s_size) {
      if (!b_count[i] || s[s_size - 1][1] - i < i - b_closest[i]) {
        b_closest[i] = s[s_size - 1][1];
      }
      b_count[i] += s_size;
    }

    s[s_size][0] = b[i];
    s[s_size][1] = i;
    ++s_size;
  }

  for (int i = 0; i < N; ++i) {
    if (b_count[i]) {
      printf("%d %d\n", b_count[i], b_closest[i] + 1);
    } else {
      printf("%d\n", b_count[i]);
    }
  }

  return 0;
}