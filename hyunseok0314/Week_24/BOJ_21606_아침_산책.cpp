#include <stdio.h>

#define MAX 200000

int N;
bool in[MAX];
int edges_size[MAX];
int parent[MAX];

int F(int u) {
  if (parent[u] != u) {
    parent[u] = F(parent[u]);
  }
  return parent[u];
}

void U(int u, int v) {
  u = F(u);
  v = F(v);

  if (u == v) {
    return;
  }

  if (u < v) {
    parent[v] = u;
    edges_size[u] += edges_size[v];
  } else {
    parent[u] = v;
    edges_size[v] += edges_size[u];
  }
}

int main() {
  scanf("%d\n", &N);

  for (int i = 0; i < N; ++i) {
    char c;
    scanf("%c", &c);
    in[i] = c - '0';
    parent[i] = i;
  }

  int ans = 0;

  for (int i = 0; i < N - 1; ++i) {
    int u, v;
    scanf("%d %d", &u, &v);
    --u;
    --v;

    if (in[u] && in[v]) {
      ans += 2;
      continue;
    }

    if (!in[u] && !in[v]) {
      U(u, v);
      continue;
    }

    u = F(u);
    v = F(v);

    if (!in[u]) {
      ++edges_size[u];
    } else {
      ++edges_size[v];
    }
  }

  for (int i = 0; i < N; ++i) {
    if (!in[i] && i == F(i)) {
      ans += edges_size[i] * (edges_size[i] - 1);
    }
  }

  printf("%d", ans);

  return 0;
}