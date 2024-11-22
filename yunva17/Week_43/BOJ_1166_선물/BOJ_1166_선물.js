let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_43/BOJ_1166_선물/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

let [N, L, W, H] = input[0].split(" ").map(Number);

let left = 0;
let right = Math.max(L, W, H);

while (left < right) {
  let mid = (left + right) / 2;

  if (Math.floor(L / mid) * Math.floor(W / mid) * Math.floor(H / mid) >= N) {
    if (left === mid) break;
    left = mid;
  } else {
    if (right === mid) break;

    right = mid;
  }
}

console.log(left.toFixed(9));
