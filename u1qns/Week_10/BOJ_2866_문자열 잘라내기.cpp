#include <iostream>
#include <vector>
#include <string>
#include <unordered_set>
using namespace std;

int R, C;
vector<std::string> str;

int getAnswer()
{
    int answer = 0;
    int start = 0;
    while (true)
    {
        if (++start >= C)
            return answer;
        unordered_set<string> set;
        for (int i = 0; i < C; ++i)
        {
            if (set.count(std::string(str[i].begin()+start, str[i].end() ) ) )
            {
                return answer;
            }
            set.insert(std::string(str[i].begin()+start, str[i].end()));
        }
        ++answer;
    }
}

int main()
{
    cin >> R >> C;
    vector<vector<char>> input(R, vector<char>(C));
    str.resize(C);

    for (int i = 0; i < R; ++i)
    {
        for (int j = 0; j < C; ++j)
        {
            cin >> input[i][j];
        }
    }

    for (int i = 0; i < C; ++i)
    {
        for (int j = 0; j < R; ++j)
        {
            str[i] += input[j][i];
        }
    }

    cout << getAnswer();

    return 0;
}
