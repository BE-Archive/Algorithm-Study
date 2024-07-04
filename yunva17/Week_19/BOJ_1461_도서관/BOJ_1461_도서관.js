let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_19/BOJ_1461_도서관/input.txt')
  .toString()
  .trim()
  .split('\n');

let answer = 0;

const [N, M] = input[0].split(' ').map((v) => Number(v));

const plus = input[1]
  .split(' ')
  .filter((v) => v > 0)
  .map((v) => Number(v))
  .sort((a, b) => b - a);

const minus = input[1]
  .split(' ')
  .filter((v) => v < 0)
  .map((v) => Number(v))
  .sort((a, b) => a - b);

// 가장 먼 거리의 책은 왕복 X
let max = 0;
if (plus.length > 0) {
  max = plus[0];
}
if (minus.length > 0) {
  max = Math.max(max, Math.abs(minus[0]));
}

for (let i = 0; i < minus.length; i += M) {
  answer +=
    max === Math.abs(minus[i]) ? Math.abs(minus[i]) : Math.abs(minus[i]) * 2;
}

for (let i = 0; i < plus.length; i += M) {
  answer += max === plus[i] ? plus[i] : plus[i] * 2;
}

console.log(answer);
