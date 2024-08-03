let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_28/BOJ_1743_음식물피하기/input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, M, K] = input[0].split(" ").map(Number);
const arr = input.slice(1).map((v) => v.split(" ").map(Number));

const dir = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];
const graph = Array.from({ length: N }, () => Array(M).fill(0));
let visited = Array.from({ length: N }, () => Array(M).fill(false));

arr.forEach(([a, b]) => (graph[a - 1][b - 1] = 1));

// 덩어리(인접한) 중 제일 큰 덩어리 찾기!

let answer = 0;

const bfs = (x, y) => {
  let queue = [];
  visited[x][y] = true;

  queue.push([x, y]);

  let count = 1;
  while (queue.length > 0) {
    let now = queue.shift();

    for (const d of dir) {
      let nx = now[0] + d[0];
      let ny = now[1] + d[1];
      if (!isGraph(nx, ny) || visited[nx][ny]) continue;

      if (graph[nx][ny] === 1) {
        queue.push([nx, ny]);
        visited[nx][ny] = true;
        count++;
      }
    }
  }

  answer = Math.max(answer, count);
};

const isGraph = (x, y) => {
  if (x < 0 || y < 0 || x >= N || y >= M) return false;
  else return true;
};

for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (graph[i][j]) {
      bfs(i, j);
    }
  }
}

console.log(answer);
