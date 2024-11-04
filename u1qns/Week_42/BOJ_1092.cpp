#include <iostream>
#include <deque>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, M, x, answer = 0;
    vector<int> crain;
    vector<int> box;

    cin >> N;
    for(int i=0; i < N; ++i)
    {
        cin >> x;
        crain.push_back(x);
    }

    cin >> M;
    for(int i=0; i < M; ++i)
    {
        cin >> x;
        box.push_back(x);
    }

    sort(crain.rbegin(), crain.rend());
    sort(box.rbegin(), box.rend());

    if(crain[0] < box[0])
    {
        answer = -1;
    }
    else
    {
        while(!box.empty())
        {
            answer++;
            for(int i=0; i < N; ++i)
            {
                for(int j=0; j<box.size(); ++j)
                {
                    if(crain[i] >= box[j])
                    {
                        box.erase(box.begin() + j);
                        break;
                    }
                }
            }
        }
    }

    cout << answer;

    return 0;
}
