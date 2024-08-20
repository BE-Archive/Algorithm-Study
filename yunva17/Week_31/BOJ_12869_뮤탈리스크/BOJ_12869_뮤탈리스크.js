let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_31/BOJ_12869_뮤탈리스크/input.txt')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);
const scv = input[1].split(' ').map(Number);

while (scv.length < 3) scv.push(0);

const dp = Array.from({ length: 61 }, () =>
  Array.from({ length: 61 }, () => Array(61).fill(Infinity))
);

const solve = (a, b, c, count) => {
  if (a <= 0 && b <= 0 && c <= 0) return 0;

  if (a < 0) a = 0;
  if (b < 0) b = 0;
  if (c < 0) c = 0;

  if (dp[a][b][c] !== Infinity) return dp[a][b][c];

  dp[a][b][c] = Math.min(
    1 + solve(a - 9, b - 3, c - 1),
    1 + solve(a - 9, b - 1, c - 3),
    1 + solve(a - 3, b - 9, c - 1),
    1 + solve(a - 3, b - 1, c - 9),
    1 + solve(a - 1, b - 9, c - 3),
    1 + solve(a - 1, b - 3, c - 9)
  );

  return dp[a][b][c];
};

console.log(solve(scv[0], scv[1], scv[2]));
