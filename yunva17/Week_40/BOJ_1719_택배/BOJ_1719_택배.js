let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_40/BOJ_1719_택배/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [n, m] = input[0].split(" ").map(Number);
let list = input.slice(1).map((v) => v.split(" ").map(Number));
let graph = Array.from({ length: n + 1 }, () => Array(n + 1).fill(Infinity));
let area = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

for (let i = 0; i < m; i++) {
  const [a, b, c] = list[i];

  graph[a][b] = c;
  graph[b][a] = c;

  area[a][b] = b; // 일단은 제일 먼저 b번 집하장으로 이동하는 게 최단경로
  area[b][a] = a;
}

for (let i = 1; i <= n; i++) {
  graph[i][i] = 0;
}

for (let k = 1; k <= n; k++) {
  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= n; j++) {
      if (k === i || k === j) continue;

      if (graph[i][j] > graph[i][k] + graph[k][j]) {
        graph[i][j] = graph[i][k] + graph[k][j];
        area[i][j] = area[i][k]; // 제일 먼저 k 집하장으로 이동(k를 거치는게 최단경로이기 때문)
      }
    }
  }
}

for (let i = 1; i <= n; i++) {
  let str = "";

  for (let j = 1; j <= n; j++) {
    if (i === j) str += "- ";
    else str += area[i][j] + " ";
  }
  console.log(str);
}
