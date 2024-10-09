#include <iostream>
#include <queue>
#include <string>
using namespace std;
typedef pair<int, int> pii;

const int dir[8][2] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
const string strDir[8] = {"LT", "T", "RT", "L", "R", "LB", "B", "RB"};
const char strCol[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

bool isValid(const int x, const int y)
{
    if(x<0 || y<0 || x>=8 || y>= 8)
        return false;
    return true;
}

pii convert(char col, int row)
{
    for(int i=0; i<8; ++i)
    {
        if(col == strCol[i])
        {
            return {8-row, i};
        }
    }
}

string revert(const pii& pos)
{
    return strCol[pos.second] + to_string(8-pos.first);
}

int main()
{
    char ch;
    int n, T;
    string str;
    pii king, stone;

    cin >> ch >> n;
    king = convert(ch, n);

    cin >> ch >> n;
    stone = convert(ch, n);

    cin >> T;
    while(T--)
    {
        cin >> str;
        for(int d=0; d<8; ++d)
        {
            if(str == strDir[d])
            {
                int x = king.first + dir[d][0];
                int y = king.second + dir[d][1];

                if(!isValid(x, y)) break;
                if(stone.first == x && stone.second == y)
                {
                    int sx = stone.first + dir[d][0];
                    int sy = stone.second + dir[d][1];
                    if(!isValid(sx, sy)) break;
                    stone = {sx, sy};
                }

                king = {x, y};
                break;
            }
        }
    }

    cout << revert(king) << "\n";
    cout << revert(stone) << "\n";

    return 0;
}
