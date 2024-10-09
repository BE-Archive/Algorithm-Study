let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_38/BOJ_17845_수강과목/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [N, K] = input[0].split(" ").map(Number);
const list = input.slice(1).map((v) => v.split(" ").map(Number));
const dp = Array.from({ length: K + 1 }, () => Array(N + 1).fill(0));

for (let i = 0; i < K; i++) {
  const [importance, time] = list[i];

  for (let j = 1; j <= N; j++) {
    if (time <= j) {
      dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - time] + importance);
    } else {
      dp[i + 1][j] = dp[i][j];
    }
  }
}

console.log(dp[K][N]);
