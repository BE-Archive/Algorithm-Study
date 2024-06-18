#include <stdio.h>

#define MAX 4000
#define MAX_W 800000000

int pq[MAX * MAX][3];
int pq_size;

void insert(int x, int w, int b) {
  pq[pq_size][0] = x;
  pq[pq_size][1] = w;
  pq[pq_size][2] = b;
  register int curr = pq_size++;

  while (curr > 0) {
    register int p = (curr - 1) >> 1;
    if (pq[p][1] < pq[curr][1]) {
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

    curr = p;
  }
}

void remove() {
  --pq_size;
  pq[0][0] = pq[pq_size][0];
  pq[0][1] = pq[pq_size][1];
  pq[0][2] = pq[pq_size][2];

  register int curr = 0;
  while (curr < pq_size) {
    register int m = curr;
    register int l = (m << 1) + 1;
    register int r = (m << 1) + 2;

    if (l < pq_size && pq[l][1] < pq[m][1]) {
      m = l;
    }

    if (r < pq_size && pq[r][1] < pq[m][1]) {
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

      curr = m;
    } else {
      break;
    }
  }
}

int N, M;
int edges[MAX][MAX][2];
int edges_size[MAX];

int fox[MAX];
int wolf[MAX][2];

int main() {
  scanf("%d %d", &N, &M);
  for (register int i = 0; i < M; i++) {
    register int u, v, w;
    scanf("%d %d %d", &u, &v, &w);
    w <<= 1;
    --u;
    --v;
    edges[u][edges_size[u]][0] = v;
    edges[v][edges_size[v]][0] = u;
    edges[v][edges_size[v]][1] = w;
    edges[u][edges_size[u]][1] = w;
    ++edges_size[u];
    ++edges_size[v];
  }

  for (register int i = 0; i < N; i++) {
    fox[i] = MAX_W;
    wolf[i][0] = MAX_W;
    wolf[i][1] = MAX_W;
  }

  fox[0] = 0;
  insert(0, 0, 0);
  while (pq_size > 0) {
    register int u = pq[0][0];
    register int w = pq[0][1];
    register int b = pq[0][2];
    remove();

    if (fox[u] < w) {
      continue;
    }

    for (register int i = 0; i < edges_size[u]; i++) {
      register int v = edges[u][i][0];
      register int nw = w + edges[u][i][1];
      if (fox[v] > nw) {
        fox[v] = nw;
        insert(v, nw, 0);
      }
    }
  }

  wolf[0][0] = 0;
  insert(0, 0, 0);
  while (pq_size > 0) {
    register int u = pq[0][0];
    register int w = pq[0][1];
    register int b = pq[0][2];
    remove();

    if (wolf[u][b] < w) {
      continue;
    }

    register int nb = b ^ 1;

    for (int i = 0; i < edges_size[u]; i++) {
      register int v = edges[u][i][0];
      register int nw = edges[u][i][1];

      if (b) {
        nw = w + (nw << 1);
      } else {
        nw = w + (nw >> 1);
      }

      if (wolf[v][nb] > nw) {
        wolf[v][nb] = nw;
        insert(v, nw, nb);
      }
    }
  }

  register int count = 0;
  for (register int i = 1; i < N; i++) {
    if (fox[i] < wolf[i][0] && fox[i] < wolf[i][1]) {
      count++;
    }
  }
  printf("%d", count);

  return 0;
}