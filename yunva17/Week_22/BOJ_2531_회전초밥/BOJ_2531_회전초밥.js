let input = require('fs')
  //  .readFileSync('/dev/stdin')
  .readFileSync('Week_22/BOJ_2531_회전초밥/input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, d, k, c] = input[0].split(' ').map(Number);

let list = input.slice(1);
list = list.map(Number);
list = list.concat(list); // 리스트 두배

let answer = 0;
let sushi = Array(d + 1).fill(0);
let count = 0;

for (let i = 0; i < k; i++) {
  if (sushi[list[i]] === 0) {
    count++;
  }
  sushi[list[i]]++;
}

answer = count + (sushi[c] === 0 ? 1 : 0);

let start = 0;
let end = k;

while (end < list.length) {
  sushi[list[start]]--;

  if (sushi[list[start]] === 0) {
    count--;
  }
  start++;

  if (sushi[list[end]] === 0) {
    count++;
  }
  sushi[list[end++]]++;

  let now = count + (sushi[c] === 0 ? 1 : 0);
  answer = Math.max(answer, now);
}

console.log(answer);
