#include <iostream>
#include <string>
#include <vector>

using namespace std;

void solve(const string& W, int K)
{
    vector<vector<int>> alpha(26);
    for(int i=0; i<W.length(); ++i)
    {
        int idx = W[i] - 'a';
        alpha[idx].push_back(i);
    }

    bool answer = false;
    int shortest = W.size();
    int longest = K-1; // 의문
  
    for(int i=0; i<26; ++i)
    {
        if(alpha[i].size() >= K)
        {
            for(int start = 0; start<=alpha[i].size()-K; ++start)
            {
                int end = start + K -1;
                if(end >= alpha[i].size()) break;

                answer = true;
                int tmp = alpha[i][end] - alpha[i][start];
                shortest = (shortest < tmp ? shortest : tmp);
                longest = (longest > tmp ? longest : tmp);
            }
        }
    }

    if(answer)
        cout << shortest+1 << " " << longest+1 << "\n";
    else
        cout << "-1\n";
}
int main()
{
    int T, K;
    string W;

    cin >> T;
    while(T--)
    {
        cin >> W >> K;
        if(K==1)
            cout << "1 1\n";
        else
            solve(W, K);
    }

    return 0;
}
