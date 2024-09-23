let input = require("fs")
  .readFileSync(
    "c:/Algorithm_Study/yunva17/Week_34/BOJ_11403_경로찾기/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const N = Number(input[0]);
const graph = input.slice(1).map((v) => v.split(" ").map(Number));

for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (graph[i][k] && graph[k][j]) {
        graph[i][j] = 1;
      }
    }
  }
}

console.log(graph.map((v) => v.join(" ")).join("\n"));
