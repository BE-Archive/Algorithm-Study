let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_27/BOJ_1158_요세푸스문제/input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, K] = input[0].split(" ").map(Number);

let queue = Array.from({ length: N }, (_, i) => i + 1);
let answer = [];

let count = 1;

while (queue.length > 0) {
  if (count % K === 0) {
    answer.push(queue.shift());
  } else {
    queue.push(queue.shift());
  }

  count++;
}

console.log("<" + answer.join(", ") + ">");
