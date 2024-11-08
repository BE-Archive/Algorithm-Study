#include <stdio.h>
#define MAX_SIZE 100001
#define MAX_QUEUE_SIZE 200000

///////////////////////////// Queue /////////////////////////////
// Queue 구현을 위한 구조체
typedef struct {
    int data[MAX_QUEUE_SIZE];
    int front, rear;
} Queue;

// Queue 관련 함수들
void initQueue(Queue *q) {
    q->front = q->rear = 0; // front와 rear를 0으로 초기화
}

void push(Queue *q, int value) {
    q->data[q->rear++] = value; // rear 위치에 값을 넣고 rear 증가
}

int pop(Queue *q) {
    return q->data[q->front++];  // front 위치의 값을 반환하고 front 증가
}

int empty(Queue *q) {
    return q->front == q->rear; // front와 rear가 같으면 큐가 비어있음
}
/////////////////////////////////////////////////////////////////
int main() {
    int N, K;
    int dist[MAX_SIZE] = {0,}; // 다 0으로 초기화
    int move[MAX_SIZE] = {0,};
    int found = 0; // 목표 지점 도달 여부
    Queue q; 
    
    // 입력 받기
    scanf("%d %d", &N, &K);
    
    // BFS
    initQueue(&q);
    push(&q, N);
    
    while (!empty(&q) && !found) {
        int x = pop(&q);
        
        if (x == K) {
            found = 1;
            break;
        }
        
        int next[3];
        next[0] = x + 1;
        next[1] = x - 1;
        next[2] = x * 2;
        
        // 각 이동 위치에 대해
        for (int i = 0; i < 3; i++) {
            int nx = next[i];
            // 유효한 범위이고 방문하지 않은 위치라면
            if (nx >= 0 && nx <= 100000 && dist[nx] == 0) {
                push(&q, nx); // 큐에 추가
                dist[nx] = dist[x] + 1; // 거리 갱신
                move[nx] = x; // 이전 위치 저장
            }
        }
    }
    
    ////////////////////////////////////////////////////////
    printf("%d\n", dist[K]); // 최단 거리 출력
    
    int path[MAX_SIZE];
    int pathSize = 0;
    int curr = K;
    
    // 경로 역추적
    while (curr != N) {
        path[pathSize++] = curr;
        curr = move[curr];
    }
    path[pathSize++] = N;
    
    // 경로 출력
    for (int i = pathSize - 1; i >= 0; i--) {
        printf("%d", path[i]);
        if (i > 0) printf(" ");
    }
    printf("\n");
    
    return 0;
}