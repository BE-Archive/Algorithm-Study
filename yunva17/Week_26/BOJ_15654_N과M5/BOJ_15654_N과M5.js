let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_26/BOJ_15654_N과M5/input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map(Number);
const Nlist = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);

let numbers = [];
let isVisited = Array.from({ length: N }, () => false);

const solve = (count) => {
  if (count === M) {
    console.log(numbers.join(" "));

    return;
  }

  for (let i = 0; i < N; i++) {
    if (isVisited[i]) continue; // 중복 패스

    numbers[count] = Nlist[i];
    isVisited[i] = true;
    solve(count + 1);
    isVisited[i] = false;
  }
};

solve(0);
