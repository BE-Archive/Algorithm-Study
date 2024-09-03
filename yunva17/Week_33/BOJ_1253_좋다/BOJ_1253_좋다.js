let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("c:/Algorithm_Study/yunva17/Week_33/BOJ_1253_좋다/input.txt")
  .toString()
  .trim()
  .split("\n");

const N = Number(input[0]);
const list = input[1].split(" ").map(Number);

let answer = 0;

list.sort((a, b) => a - b);

for (let i = 0; i < list.length; i++) {
  let left = 0;
  let right = N - 1;

  while (left < right) {
    // 현재 수 제외
    if (left === i) {
      left++;
      continue;
    }
    if (right === i) {
      right--;
      continue;
    }

    let sum = list[left] + list[right];

    if (sum === list[i]) {
      answer++;
      break;
    } else if (sum < list[i]) {
      left++;
    } else {
      right--;
    }
  }
}

console.log(answer);
