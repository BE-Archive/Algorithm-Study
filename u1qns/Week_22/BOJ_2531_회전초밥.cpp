#include <iostream>
#define MAX 30001
using namespace std;

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);
  
    int n, d, k, c;
    int arr[MAX];
    int cnt[MAX];

    cin >> n >> d >> k >> c;

    for(int i=0; i<n; ++i)
        cin >> arr[i];

    int answer = 0;

    // 맨 처음 k개 골랐음
    for(int i=0; i<k; ++i)
    {
        if(cnt[arr[i]]++ == 0)
            ++answer;
    }

    int prev=0, cur = k;
    int tmp = answer;
    for(int i=0; i<n; ++i)
    {
        if(cnt[arr[cur]]++ == 0) ++tmp;
        if(--cnt[arr[prev]] == 0) --tmp;

        answer = std::max(answer, tmp + (cnt[c] ? 0 : 1));
        
        if(answer == k + 1)
            break;

        cur = (cur + 1) % n;
        prev = (prev + 1 + n) % n;
    }

    cout << answer;
    return 0;
}
