let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_25/BOJ_2662_기업투자/input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
let invest = Array.from({ length: N + 1 }, () => new Array(M + 1).fill(0));

for (let i = 1; i <= N; i++) {
  let line = input[i].split(' ').map(Number);
  for (let j = 1; j <= M; j++) {
    invest[i][j] = line[j];
  }
}

let dp = Array.from({ length: N + 1 }, () => new Array(M + 1).fill(0));
let select = Array.from({ length: N + 1 }, () => new Array(M + 1).fill(0));

for (let j = 1; j <= M; j++) {
  for (let i = 1; i <= N; i++) {
    for (let k = 0; k <= i; k++) {
      if (dp[i][j] < dp[i - k][j - 1] + invest[k][j]) {
        dp[i][j] = dp[i - k][j - 1] + invest[k][j];
        select[i][j] = k;
      }
    }
  }
}

console.log(dp[N][M]); // 최대 이익

let result = new Array(M).fill(0);
let remain = N;
for (let i = M; i > 0; i--) {
  result[i - 1] = select[remain][i];
  remain -= result[i - 1];
}

// 투자 금액 출력
console.log(result.join(' '));
