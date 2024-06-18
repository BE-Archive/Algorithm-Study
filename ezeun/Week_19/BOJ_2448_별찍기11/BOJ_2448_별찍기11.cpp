#include <iostream>
using namespace std;
int N;
bool star[6500][6500]; //len 최대 3*1024, 밑변 최대 3*1024*2,  1-based

void draw(int x, int y, int len) {
    if(len==3) { //basecase
        //(x, y)가 꼭짓점인 삼각형 그리기
        star[x][y]=true;
        star[x+1][y-1]=true;
        star[x+1][y+1]=true;
        star[x+2][y-2]=true;
        star[x+2][y-1]=true;
        star[x+2][y]=true;
        star[x+2][y+1]=true;
        star[x+2][y+2]=true;
        return;
    }
    draw(x, y, len/2); //up
    draw(x+len/2, y-len/2, len/2); //left
    draw(x+len/2, y+len/2, len/2); //right
}

void bt(int N) {
    draw(1, N, N);
}

void print() {
    for(int i=1; i<=N; i++) {
        for(int j=1; j<=N*2; j++) {
            if(star[i][j]) cout<<"*";
            else cout<<" ";
        }
        cout<<'\n';
    }
}

int main() {
    cin>>N;

    bt(N);

    print();

    return 0;
}