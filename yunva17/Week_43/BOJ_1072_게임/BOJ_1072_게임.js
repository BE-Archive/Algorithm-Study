let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_43/BOJ_1072_게임/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

let [X, Y] = input[0].split(" ").map(Number);
let nowRate = Math.floor((Y * 100) / X);

if (nowRate >= 99) {
  console.log(-1);
  return;
}

let left = 1;
let right = 1000000000;

while (left <= right) {
  let mid = Math.floor((left + right) / 2);
  let rate = Math.floor(((Y + mid) * 100) / (X + mid));

  if (rate > nowRate) {
    right = mid - 1;
  } else {
    left = mid + 1;
  }
}

console.log(left);
