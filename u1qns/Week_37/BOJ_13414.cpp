#include <iostream>
#include <queue>
#include <map>
#include <string>
using namespace std;

map<string, int> order;
queue<string> q;
int main() 
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int limit, students;
    string num;
    
    cin >> limit >> students;
    for(int i=0; i<students; ++i)
    {
        cin >> num;
        order[num] = i;
        q.push(num);
    }

    int i = 0;
    while(limit > 0 && !q.empty())
    {
        string n = q.front(); q.pop();
        if(order[n] == i)
        {
            cout << n << "\n";
            --limit;
        }
        ++i;
    }
    
    return 0;
}
