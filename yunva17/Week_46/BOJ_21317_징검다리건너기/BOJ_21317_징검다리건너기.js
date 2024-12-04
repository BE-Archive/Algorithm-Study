let input = require('fs')
  .readFileSync(
    'e:/ssafy/Algorithm_Study/yunva17/Week_46/BOJ_21317_징검다리건너기/input.txt'
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);
const stones = input.slice(1, N).map((v) => v.split(' ').map(Number));
const K = Number(input[N]);

let dp = Array.from({ length: N + 1 }, () => [Infinity, Infinity]);

dp[1][0] = 0; // 첫 번째 돌에서 시작
if (N >= 2) dp[2][0] = stones[0][0];
if (N >= 3) dp[3][0] = Math.min(stones[0][0] + stones[1][0], stones[0][1]);

// 매우 큰 점프 X
for (let i = 4; i <= N; i++) {
  dp[i][0] = Math.min(
    dp[i - 1][0] + stones[i - 2][0],
    dp[i - 2][0] + stones[i - 3][1]
  );
}

// 매우 큰 점프 O
for (let i = 4; i <= N; i++) {
  dp[i][1] = Math.min(
    dp[i - 1][1] + stones[i - 2][0],
    dp[i - 2][1] + stones[i - 3][1],
    dp[i - 3][0] + K // 매우 큰 점프 사용
  );
}

console.log(Math.min(dp[N][0], dp[N][1]));
