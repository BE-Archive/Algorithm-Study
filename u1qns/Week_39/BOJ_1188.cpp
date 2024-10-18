#include <iostream>

using namespace std;

int solve(const int a, const int b)
{
    if(b==0) return a;
    return solve(b, a%b);
}

int main() 
{
    int so, sa, answer = 0;
    cin >> so >> sa;

    // so, sa
    // 11 9 는 사실 2 9 임 d 안 쪼개도 되는 만큼은 걍 나눠주고 나머지를 쪼갬
    // 그리고 이건 뭔가를 so * sa만큼 잇다고 생각하고 쪼개야할때 칼질 횟수를 구ㅏㅎ는거다.
    
    // n개의 조각으로 나누고 싶다면 n-1번썰어야한다.
    // 나머지를 n-1번 썰어야하는데 썰다가 공약수에 걸리면 패스
    // 왜냐면 이미 썰려있으니까 칼질 안 해도 됨 sa - 공약수 개수
    // 공약수개수가 패스해도 되는 개수니까
    so = so % sa;
    
    cout << sa-solve(so, sa);
    return 0;
}
