#include <stdio.h>

#define MAX_LEN 100
#define MAX_N 20000

struct Word {
  char* letters;
  int index;
};

int N;

char words[MAX_N][MAX_LEN + 1];
Word pq[MAX_N];
int len[MAX_N];
int pq_size;

int strcmp(char* s1, char* s2) {
  while (*s1 && (*s1 == *s2)) {
    s1++;
    s2++;
  }
  return *(unsigned char*)s1 - *(unsigned char*)s2;
}

bool strncmp(char* s1, char* s2, int n) {
  for (int i = 0; i < n; ++i) {
    if (s1[i] != s2[i]) {
      return false;
    }
  }

  return true;
}

void insert(char* l, int i) {
  pq[pq_size].letters = l;
  pq[pq_size].index = i;

  int curr = pq_size++;

  while (curr > 0) {
    int p = (curr - 1) >> 1;

    if (strcmp(pq[p].letters, pq[curr].letters) < 0) {
      break;
    }

    Word temp = pq[p];
    pq[p] = pq[curr];
    pq[curr] = temp;

    curr = p;
  }
}

void remove() {
  --pq_size;
  pq[0] = pq[pq_size];

  int curr = 0;
  while (curr < pq_size) {
    int m = curr;
    int l = (m << 1) + 1;
    int r = (m << 1) + 2;

    if (l < pq_size && strcmp(pq[l].letters, pq[m].letters) < 0) {
      m = l;
    }

    if (r < pq_size && strcmp(pq[r].letters, pq[m].letters) < 0) {
      m = r;
    }

    if (m != curr) {
      Word temp = pq[m];
      pq[m] = pq[curr];
      pq[curr] = temp;

      curr = m;
    } else {
      break;
    }
  }
}

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
    scanf("%s", words[i]);
    insert(words[i], i);
  }

  Word prev = pq[0];
  remove();

  int max_len = 0;

  for (int i = 1; i < N; ++i) {
    int c = 0;
    while (c < MAX_LEN && prev.letters[c] == pq[0].letters[c]) {
      ++c;
    }

    max_len = max(max_len, c);
    len[prev.index] = max(len[prev.index], c);
    len[pq[0].index] = max(len[pq[0].index], c);

    prev = pq[0];
    remove();
  }

  Word S = {NULL, -1};
  Word T = {NULL, -1};
  for (int i = 0; i < N; i++) {
    if (len[i] == max_len) {
      if (S.index == -1) {
        S.index = i;
        S.letters = words[i];
      } else {
        if (strncmp(S.letters, words[i], max_len)) {
          T.letters = words[i];
          break;
        }
      }
    }
  }

  printf("%s\n%s\n", S.letters, T.letters);

  return 0;
}