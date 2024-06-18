#include <iostream>

using namespace std;

int main()
{
  int N, d, k, c; // N: 접시의 수, d: 초밥의 가짓수, k: 연속해서 먹는 접시의 수, c: 쿠폰 번호
  cin >> N >> d >> k >> c;

  int dishes[N] = {
      0,
  }; // watching sushi list
  int sushi[3001] = {
      0,
  }; // counting sort
  for (int i = 0; i < N; i++)
  {
    cin >> dishes[i];
  }
  // initialize end

  int max = 0;
  int count = 0;

  int pointer = 0;
  for (int i = 0; i < N + k; i++)
  {
    pointer = i % N;

    sushi[dishes[pointer]]++; // check duplicated
    if (sushi[dishes[pointer]] == 1)
      count++; // count the number of different sushi

    if (i >= k)
    {
      pointer = (i - k) % N;
      sushi[dishes[pointer]]--; // remove the last sushi
      if (sushi[dishes[pointer]] == 0)
        count--; // count the number of different sushi
    }

    // cout << "count: " << count << endl;
    if (sushi[c] == 0)
      count++;
    if (count > max)
      max = count;

    if (sushi[c] == 0)
      count--;
  }
  cout << max << endl;
  return 0;
}
