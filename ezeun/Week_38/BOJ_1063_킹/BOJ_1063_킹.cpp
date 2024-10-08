#include <iostream>
#include <deque>
#include <map>
#include <vector>
using namespace std;

struct pos {
    int x, y;
};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    string a,b; cin>>a>>b;

    pos king, stone;
    king.x = 8-(a[1]-'0')+1;
    king.y = a[0]-'A'+1;
    stone.x = 8-(b[1]-'0')+1;
    stone.y = b[0]-'A'+1;

    int n; cin>>n;
    while(n--) {
        string s; cin>>s;
        if(s=="R") {
            if(king.y+1<=8) { //킹 이동 가능하면
                king.y++; //옮겨줌
                if(king.x==stone.x && king.y==stone.y) { //이동한곳에 돌이있으면
                    if(stone.y+1<=8) stone.y++; //돌도 옮겨줌
                    else king.y--; //돌 이동 불가능하면 킹 옮긴 것도 원위치
                }
            }
        }
        else if(s=="L") {
            if(king.y-1>=1) {
                king.y--;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.y-1>=1) stone.y--;
                    else king.y++;
                }
            }
        }
        else if(s=="B") {
            if(king.x+1<=8) {
                king.x++;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x+1<=8) stone.x++;
                    else king.x--;
                }
            }
        }
        else if(s=="T") {
            if(king.x-1>=1) {
                king.x--;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x-1>=1) stone.x--;
                    else king.x++;
                }
            }
        }
        else if(s=="RT") {
            if(king.x-1>=1 && king.y+1<=8) {
                king.x--, king.y++;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x-1>=1 && stone.y+1<=8) stone.x--, stone.y++;
                    else king.x++, king.y--;
                }
            }
        }
        else if(s=="LT") {
            if(king.x-1>=1 && king.y-1>=1) {
                king.x--, king.y--;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x-1>=1 && stone.y-1>=1) stone.x--, stone.y--;
                    else king.x++, king.y++;
                }
            }
        }
        else if(s=="RB") {
            if(king.x+1<=8 && king.y+1<=8) {
                king.x++, king.y++;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x+1<=8 && stone.y+1<=8) stone.x++, stone.y++;
                    else king.x--, king.y--;
                }
            }
        }
        else if(s=="LB") {
            if(king.x+1<=8 && king.y-1>=1) {
                king.x++, king.y--;
                if(king.x==stone.x && king.y==stone.y) {
                    if(stone.x+1<=8 && stone.y-1>=1) stone.x++, stone.y--;
                    else king.x--, king.y++;
                }
            }
        }
    }

    cout<<(char)(king.y-1+'A');
    cout<<9-king.x;
    cout<<'\n';
    cout<<(char)(stone.y-1+'A');
    cout<<9-stone.x;
    return 0;
}
