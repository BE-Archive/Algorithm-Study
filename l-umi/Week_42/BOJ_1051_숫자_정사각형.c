#include <stdio.h>

#define MAX_SIZE 50

// size의 정사각형이 가능한지 확인하는 함수
int checkSize(int board[MAX_SIZE][MAX_SIZE], int N, int M, int size) {
    for(int i = 0; i <= N-size; i++) {
        for(int j = 0; j <= M-size; j++) {
            if(board[i][j] == board[i][j+size-1] && 
               board[i][j] == board[i+size-1][j] && 
               board[i][j] == board[i+size-1][j+size-1]) {
                return 1;
            }
        }
    }
    return 0;
}

int main() {
    int N, M;
    scanf("%d %d", &N, &M);

    int board[MAX_SIZE][MAX_SIZE];
    char line[MAX_SIZE + 1]; // 문자열 입력을 위한 배열
    
    for(int i = 0; i < N; i++) {
        scanf("%s", line);
        for(int j = 0; j < M; j++) {
            board[i][j] = line[j] - '0';
        }
    }
    
    /////////////////////////////////////////
    
    int maxSize = N < M ? N : M;
    
    for(int size = maxSize; size >= 1; size--) {
        if(checkSize(board, N, M, size)) {
            printf("%d", size * size);
            break;
        }
    }
    
    return 0;
}