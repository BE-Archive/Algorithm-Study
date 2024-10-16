#include <iostream>
#include <string>
using namespace std;

int main()
{
    int N;
    string input;

    cin >> N >> input;

    int index = input.find('*');
    string left = input.substr(0, index);
    string right = input.substr(index + 1);

    while (N--)
    {
        string cmp;
        bool answer = false;
        cin >> cmp;

        if (left.size() + right.size()<= cmp.size() // 글자수 확인
            && cmp.find(left) == 0) //별표는 문자열의 시작과 끝에 있지 않다
        {
            if (cmp.substr(cmp.size() - (right.size())) == right)
                answer = true;
        }

        cout << (answer ? "DA\n" : "NE\n");
    }

    return 0;
}
