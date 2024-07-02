#include <malloc.h>
#include <stdio.h>

#define MAX 2
#define MAX_N 100000
#define MAX_LL 1e18

long long** pq;
long long pq_size;

void insert(long long u, long long w) {
  if (!pq_size) {
    pq = (long long**)malloc(sizeof(long long*));
  } else {
    pq = (long long**)realloc(pq, sizeof(long long*) * (pq_size + 1));
  }

  pq[pq_size] = (long long*)malloc(sizeof(long long) * MAX);

  pq[pq_size][0] = u;
  pq[pq_size][1] = w;
  long long curr = pq_size++;

  while (curr > 0) {
    long long p = (curr - 1) >> 1;
    if (pq[p][1] < pq[curr][1]) {
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

  free(pq[pq_size]);

  if (!pq_size) {
    free(pq);
    return;
  }

  pq = (long long**)realloc(pq, sizeof(long long*) * pq_size);

  long long curr = 0;
  while (curr < pq_size) {
    long long m = curr;
    long long l = (m << 1) + 1;
    long long r = (m << 1) + 2;

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

      curr = m;
    } else {
      break;
    }
  }
}

long long N, M;
long long ward[MAX_N];
long long** edges[MAX_N];
long long edges_size[MAX_N];
long long dist[MAX_N];

int main() {
  scanf("%lld %lld", &N, &M);

  for (long long i = 0; i < N; ++i) {
    scanf("%lld", &ward[i]);
  }
  ward[N - 1] = 0;

  for (long long i = 0; i < M; ++i) {
    long long u, v, w;
    scanf("%lld %lld %lld", &u, &v, &w);

    if (!edges_size[u]) {
      edges[u] = (long long**)malloc(sizeof(long long*));
    } else {
      edges[u] = (long long**)realloc(edges[u],
                                      sizeof(long long*) * (edges_size[u] + 1));
    }

    edges[u][edges_size[u]] = (long long*)malloc(sizeof(long long) * 2);

    if (!edges_size[v]) {
      edges[v] = (long long**)malloc(sizeof(long long*));
    } else {
      edges[v] = (long long**)realloc(edges[v],
                                      sizeof(long long*) * (edges_size[v] + 1));
    }

    edges[v][edges_size[v]] = (long long*)malloc(sizeof(long long) * 2);

    edges[u][edges_size[u]][0] = v;
    edges[v][edges_size[v]][0] = u;
    edges[u][edges_size[u]++][1] = w;
    edges[v][edges_size[v]++][1] = w;
  }

  for (long long i = 0; i < N; i++) {
    dist[i] = MAX_LL;
  }

  insert(0, 0);

  while (pq_size > 0) {
    long long u = pq[0][0];
    long long w = pq[0][1];
    remove();

    if (dist[u] < w) {
      continue;
    }

    for (long long i = 0; i < edges_size[u]; ++i) {
      if (ward[edges[u][i][0]]) {
        continue;
      }

      long long nw = w + edges[u][i][1];
      if (nw < dist[edges[u][i][0]]) {
        dist[edges[u][i][0]] = nw;
        insert(edges[u][i][0], nw);
      }
    }
  }

  if (dist[N - 1] == MAX_LL) {
    printf("-1");
  } else {
    printf("%lld", dist[N - 1]);
  }

  return 0;
}