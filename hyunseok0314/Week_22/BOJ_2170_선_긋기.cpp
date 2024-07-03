#include <stdio.h>

#define MAX 1000000
#define MIN -1000000001

int pq[MAX][2];
int pq_size;

void insert(int x, int y) {
  pq[pq_size][0] = x;
  pq[pq_size][1] = y;
  int curr = pq_size++;

  while (curr > 0) {
    int p = (curr - 1) >> 1;
    if (pq[p][0] < pq[curr][0] ||
        (pq[p][0] == pq[curr][0] && pq[p][1] < pq[curr][1])) {
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

    if (l < pq_size && (pq[l][0] < pq[m][0] ||
                        (pq[l][0] == pq[m][0] && pq[l][1] < pq[m][1]))) {
      m = l;
    }

    if (r < pq_size && (pq[r][0] < pq[m][0] ||
                        (pq[r][0] == pq[m][0] && pq[r][1] < pq[m][1]))) {
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

int N;

int main() {
  scanf("%d", &N);
  for (int i = 0; i < N; ++i) {
    int x, y;
    scanf("%d %d", &x, &y);
    insert(x, y);
  }

  int len = 0;

  int start = MIN;
  int end = MIN;
  while (pq_size > 0) {
    if (end < pq[0][0]) {
      len += end - start;
      start = pq[0][0];
    }

    if (end < pq[0][1]) {
      end = pq[0][1];
    }

    remove();
  }

  len += end - start;

  printf("%d", len);

  return 0;
}