#include <iostream>
#include <string>
#include <map>

using namespace std;
//2032kb  	4ms
bool solve(const string& str, const int offset)
{
    map<string, int> map;
    string tmp = "";
    for(int i=0; i<str.length()-offset; ++i)
    {
        tmp = string(1, str[i]) + string(1, str[i+offset]);
        //cout << tmp << "\n";
        if(map[tmp] !=0) return false;
        map[tmp]++;
    }
    return true;
}

int main()
{
    while(1)
    {
        bool answer = true;
        std::string str, tmp;
        cin >> str;
        
        if(str == "*") break;

        for(int offset=1; offset<str.length()-1; ++offset)
        {
            if(!solve(str, offset))
            {
                answer = false;
                break;
            }
        }
        
        cout << str << " is " << (answer ? "" : "NOT ") << "surprising.\n";
    }
    return 0;
}
