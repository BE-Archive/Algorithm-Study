let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_42/BOJ_1051_숫자정사각형/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map(Number);
const list = input.slice(1).map((v) => v.split("").map(Number));
let answer = -1;

for (let size = 1; size < Math.min(N, M); size++) {
  for (let i = 0; i + size < N; i++) {
    for (let j = 0; j + size < M; j++) {
      if (
        list[i][j] === list[i][j + size] &&
        list[i][j] === list[i + size][j] &&
        list[i][j] === list[i + size][j + size]
      ) {
        answer = Math.max(answer, size + 1);
      }
    }
  }
}

console.log(answer * answer);
