let input = require("fs")
  .readFileSync(
    "c:/Algorithm_Study/yunva17/Week_35/BOJ_28017_게임을클리어하자/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map(Number);
const time = input.slice(1).map((v) => v.split(" ").map(Number));

let dp = Array.from({ length: N }, () => new Array(M).fill(0));

for (let j = 0; j < M; j++) {
  dp[0][j] = time[0][j];
}

for (let i = 1; i < N; i++) {
  for (let j = 0; j < M; j++) {
    let min = Infinity;

    for (let k = 0; k < M; k++) {
      if (k !== j) {
        min = Math.min(min, dp[i - 1][k]);
      }
    }
    dp[i][j] = time[i][j] + min;
  }
}

let answer = Math.min(...dp[N - 1]);
console.log(answer);
