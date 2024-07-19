let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_26/BOJ_15553_난로/input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, K] = input[0].split(" ").map(Number);

const times = input.slice(1).map(Number);

let minus = [];
for (let i = 1; i < N; i++) {
  minus.push(times[i] - times[i - 1]);
}

minus.sort((a, b) => a - b);

let answer = 0;
if (N - K > 0) {
  answer = minus.slice(0, N - K).reduce((acc, cur) => acc + cur, 0) + K;
} else {
  answer = N;
}

console.log(answer);
