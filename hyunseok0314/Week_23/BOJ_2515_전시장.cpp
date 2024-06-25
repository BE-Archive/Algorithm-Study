#include <stdio.h>

#define MAX_N 300000

int N, S;

int pq[MAX_N][2];
int pq_size;

void insert(int x, int y) {
  pq[pq_size][0] = x;
  pq[pq_size][1] = y;
  int curr = pq_size++;

  while (curr > 0) {
    int p = (curr - 1) >> 1;
    if (pq[p][0] < pq[curr][0]) {
      break;
    }

    pq[p][0] ^= pq[curr][0];
    pq[curr][0] ^= pq[p][0];
    pq[p][0] ^= pq[curr][0];

    pq[p][1] ^= pq[curr][1];
    pq[curr][1] ^= pq[p][1];
    pq[p][1] ^= pq[curr][1];

    curr = p;
  }
}

void remove() {
  --pq_size;
  pq[0][0] = pq[pq_size][0];
  pq[0][1] = pq[pq_size][1];

  int curr = 0;
  while (curr < pq_size) {
    int m = curr;
    int l = (m << 1) + 1;
    int r = (m << 1) + 2;

    if (l < pq_size && pq[l][0] < pq[m][0]) {
      m = l;
    }

    if (r < pq_size && pq[r][0] < pq[m][0]) {
      m = r;
    }

    if (m != curr) {
      pq[curr][0] ^= pq[m][0];
      pq[m][0] ^= pq[curr][0];
      pq[curr][0] ^= pq[m][0];

      pq[curr][1] ^= pq[m][1];
      pq[m][1] ^= pq[curr][1];
      pq[curr][1] ^= pq[m][1];

      curr = m;
    } else {
      break;
    }
  }
}
int paints[MAX_N + 1][2];
int dp[MAX_N + 1];

int max(int x, int y) {
  if (x > y) {
    return x;
  }
  return y;
}

int bs(int i) {
  int left = 0;
  int right = i - 1;

  while (left <= right) {
    int mid = (left + right) >> 1;

    if (paints[mid][0] <= paints[i][0] - S) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  return right;
}

int main() {
  scanf("%d %d", &N, &S);

  for (int i = 0; i < N; ++i) {
    int H, C;
    scanf("%d %d", &H, &C);
    insert(H, C);
  }

  for (int i = 1; i <= N; ++i) {
    paints[i][0] = pq[0][0];
    paints[i][1] = pq[0][1];
    remove();
  }

  for (int i = 1; i <= N; ++i) {
    dp[i] = max(dp[i - 1], dp[bs(i)] + paints[i][1]);
  }

  printf("%d", dp[N]);

  return 0;
}