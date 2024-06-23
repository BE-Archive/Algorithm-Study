let input = require('fs')
  //  .readFileSync('/dev/stdin')
  .readFileSync('Week_22/BOJ_2170_선긋기/input.txt')
  .toString()
  .trim()
  .split('\n');

let lines = input.slice(1).map((v) => v.split(' ').map(Number));

lines = lines.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));

let answer = lines[0][1] - lines[0][0];
let [, maxEnd] = lines[0];

for (let i = 1; i < lines.length; i++) {
  let [start, end] = lines[i];
  if (start < maxEnd) {
    if (maxEnd < end) {
      answer += end - maxEnd;
    }
  } else {
    answer += end - start;
  }

  maxEnd = Math.max(maxEnd, end);
}

console.log(answer);
