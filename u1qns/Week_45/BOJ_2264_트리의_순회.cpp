#include <iostream>
using namespace std;
const int MAX_N = 100000 + 1;
int N;
int inorder[MAX_N] = {0, };
int postorder[MAX_N] = {0, };
int preorder[MAX_N] = {0, };
int idx = -1;

void solve(const int start, const int end, const int ps, const int pe)
{

    if(start > end || end < 0)
        return;

    preorder[++idx] = postorder[pe];
    //cout << start << "\t" << end << "\t" << ps << "\t" << pe << "\n";

    if(start == end)
        return;

    // 현재 범위 내에서 root 위치를 찾고 그 기준으로 partitioning
    int mid = start;
    for(; mid<=end; ++mid)
    {
        if(inorder[mid] == preorder[idx])
            break;
    }

    solve(start, mid-1, ps, ps + (mid-1 - start));
    solve(mid+1, end, ps + (mid-start) , pe-1);
}

int main()
{
    cin >> N;

    for(int i=0; i<N; ++i)
        cin >> inorder[i];
    for(int i=0; i<N; ++i)
        cin >> postorder[i];

    solve(0, N-1, 0, N-1);

    for(int i=0; i<N; ++i)
        cout << preorder[i] << " ";

    return 0;
}
