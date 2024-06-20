#include <iostream>

using namespace std;

int main()
{
  int N, M;

  cin >> N;
  int A[N];
  int A_idx = 0;
  for (int i = 0; i < N; i++)
  {
    cin >> A[i];
  }

  cin >> M;
  int B[M];
  int B_idx = 0;
  for (int i = 0; i < M; i++)
  {
    cin >> B[i];
  }

  // logic start

  int ans[101];
  int ans_size = 0;

  while (A_idx < N && B_idx < M)
  {
    int max = -1;
    int a_max_idx = N;
    int b_max_idx = M;
    // find local max
    for (int i = A_idx; i < N; i++)
    {
      for (int j = B_idx; j < M; j++)
      {
        if (A[i] == B[j])
        {
          if (A[i] > max)
          {
            max = A[i];
            a_max_idx = i + 1;
            b_max_idx = j + 1;
          }
        }
      }
    }

    // memo max
    A_idx = a_max_idx;
    B_idx = b_max_idx;
    if (max != -1)
    { // exception catch
      ans[ans_size++] = max;
    }
  }

  // stdout
  cout << ans_size << endl;
  for (int i = 0; i < ans_size; i++)
  {
    cout << ans[i] << " ";
  }
  return 0;
}