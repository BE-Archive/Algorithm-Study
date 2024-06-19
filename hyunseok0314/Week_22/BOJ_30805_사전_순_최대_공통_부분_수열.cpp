#include <stdio.h>

#define MAX 100

int N, M;
int A[MAX];
int B[MAX];
int as, bs;
int seq[MAX];
int seq_size;

int main() {
  scanf("%d", &N);
  for (int i = 0; i < N; ++i) {
    scanf("%d", &A[i]);
    --A[i];
  }

  scanf("%d", &M);
  for (int i = 0; i < M; ++i) {
    scanf("%d", &B[i]);
    --B[i];
  }

  while (1) {
    int max = -1;
    int ii, jj;
    for (int i = as; i < N; i++) {
      for (int j = bs; j < M; j++) {
        if (A[i] == B[j] && A[i] > max) {
          max = A[i];
          ii = i + 1;
          jj = j + 1;
        }
      }
    }

    if (max == -1) {
      break;
    }

    seq[seq_size++] = max;
    as = ii;
    bs = jj;
  }

  printf("%d\n", seq_size);
  for (int i = 0; i < seq_size; ++i) {
    printf("%d ", seq[i] + 1);
  }

  return 0;
}