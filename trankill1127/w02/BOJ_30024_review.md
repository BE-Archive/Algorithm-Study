## 접근방법
### 조건
* 민석이는 옥수수밭 바깥을 돌아다니면서 옥수수밭 바깥과 인접한 칸의 옥수수를 수확할 수 있다. 또는 옥수수밭 안에서 옥수수를 수확한 칸으로만 돌아다니면서 현재 위치한 칸에서 상하좌우로 인접한 칸의 옥수수를 수확할 수 있다.
* 항상 가장 큰 가치를 가지는 옥수수를 수확한다.
* 민석이는 밭의 바깥쪽에 있다.

### 결론
* 완전탐색을 해야겠다.
* 우선순위 큐를 사용해서 가치가 큰 옥수수를 먼저 꺼낼 수 있게 해야겠다.
* 큐를 초기화할 때 바깥쪽에 있는 옥수수를 모두 넣어줘야겠다.

</br>

## 오류 발생 지점
예제 케이스 테스트를 돌려볼 때 이미 큐에 옥수수 정보가 중복으로 들어가는 경우가 있다는 것을 확인했었고 수정까지 했었다. 결과가 잘 나오길래 해결한 줄 알았는데 k를 n*m으로 하고 돌렸을 때 중후반부에서 큐에 옥수수가 중복으로 추가되는 현상이 여전히 발생하고 있음을 알 수 있었다.
```
PriorityQueue<Corn> q = new PriorityQueue<>();
for (int i=1; i<n+1; i++) {
    q.add(new Corn(i,1,farm[i][1]));
    q.add(new Corn(i,m,farm[i][m]));
}
for (int i=2; i<=m-1; i++) {
    q.add(new Corn(1,i,farm[1][i]));
    q.add(new Corn(n,i,farm[n][i]));
}

int[] dx = {-1,0,1,0};
int[] dy = {0,1,0,-1};

StringBuilder sb = new StringBuilder();
while (k>0) {
    Corn now = q.poll();
    farm[now.x][now.y]=0;
    sb.append(now.x).append(" ").append(now.y).append("\n");

    for (int i=0; i<4; i++) {
        int nextX = now.x+dx[i];
        int nextY = now.y+dy[i];

        if (farm[nextX][nextY]==0 || nextX==1 || nextX==n || nextY==1 || nextY==m ) continue;

        q.add(new Corn(nextX, nextY, farm[nextX][nextY]));
    }

    k--;
}
```

![image](https://github.com/BE-Archive/Algorithm-Study/assets/73770924/4b037f16-2883-41da-9650-9b382acb7667)

당장 그림 그리면서 이게 어느 시점에 문제가 생기는지 확인해봤더니 17 주변을 검색할 때 14, 15가 이미 큐에 들어갔는데 19 주변을 검색할 때 또 14, 15가 큐에 들어가는 게 문제였다. 즉, 방문 여부를 업데이트하는 위치가 문제였던 것이었고, 아래처럼 수정해준 결과 통과할 수 있었다.

```
PriorityQueue<Corn> q = new PriorityQueue<>();
for (int i=1; i<n+1; i++) {
    q.add(new Corn(i,1,farm[i][1]));
    farm[i][1]=0;
    q.add(new Corn(i,m,farm[i][m]));
    farm[i][m]=0;
}
for (int i=2; i<=m-1; i++) {
    q.add(new Corn(1,i,farm[1][i]));
    farm[1][i]=0;
    q.add(new Corn(n,i,farm[n][i]));
    farm[n][i]=0;
}

////// 생략

while (k>0) {
    Corn now = q.poll();
    sb.append(now.x).append(" ").append(now.y).append("\n");
            
    for (int i=0; i<4; i++) {
        int nextX = now.x+dx[i];
        int nextY = now.y+dy[i];

        if (farm[nextX][nextY]==0 || nextX==1 || nextX==n || nextY==1 || nextY==m ) continue;

        q.add(new Corn(nextX, nextY, farm[nextX][nextY]));
        farm[nextX][nextY]=0;
    }

    k--;
}
```
