let input = require('fs')
  .readFileSync('dev/stdin')
  .toString()
  .trim()
  .split('\n');

const TC = input.shift();

for (let t = 0; t < TC; t++) {
  // 지점 수 N, 도로 개수 M, 웜홀 개수 W
  const [N, M, W] = input.shift().split(' ').map(Number);
  const INF = Number.MAX_VALUE;
  let list = [];

  for (let i = 0; i < M; i++) {
    let [S, E, T] = input.shift().split(' ').map(Number);
    // 양방향
    list.push({ S, E, T });
    list.push({ S: E, E: S, T: T });
  }

  for (let i = 0; i < W; i++) {
    let [S, E, T] = input.shift().split(' ').map(Number);
    // 음의 가중치
    list.push({ S, E, T: -T });
  }

  let dist = Array(N + 1).fill(INF);
  dist[1] = 0; //시작

  let isMinus = false;
  for (let v = 1; v <= N; v++) {
    for (let i = 0; i < list.length; i++) {
      let { S, E, T } = list[i];
      if (dist[S] !== INF && dist[S] + T < dist[E]) {
        dist[E] = dist[S] + T;

        if (v === N) {
          isMinus = true;
          break;
        }
      }
    }

    if (isMinus) break;
  }

  console.log(isMinus ? 'YES' : 'NO');
}
