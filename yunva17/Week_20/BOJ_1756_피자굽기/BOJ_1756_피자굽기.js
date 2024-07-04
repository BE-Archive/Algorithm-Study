let input = require('fs')
  //  .readFileSync('/dev/stdin')
  .readFileSync('Week_20/BOJ_1756_피자굽기/input.txt')
  .toString()
  .trim()
  .split('\n');

const [D, N] = input[0].split(' ').map(Number);

let oven = input[1].split(' ').map(Number);
let pizza = input[2].split(' ').map(Number);

for (let i = 1; i < D; i++) {
  if (oven[i - 1] < oven[i]) {
    oven[i] = oven[i - 1];
  }
}

let now = 0;
for (let i = D - 1; i >= 0; i--) {
  if (oven[i] >= pizza[now]) {
    now++;
  }
  if (now === N) {
    console.log(i + 1);
    break;
  }
  if (i === 0) {
    console.log(0);
    break;
  }
}
