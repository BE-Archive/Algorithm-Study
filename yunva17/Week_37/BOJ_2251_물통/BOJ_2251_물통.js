let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_37/BOJ_2251_물통/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const limit = input[0].split(" ").map(Number);
const [A, B, C] = limit;
const answer = new Set();
let visited = Array.from({ length: A + 1 }, () =>
  Array.from({ length: B + 1 }, () => Array(C + 1).fill(false))
);

// 물 이동
const moveWater = (from, to, arr) => {
  if (arr[from] === 0) return;

  // 도착 물통 가득 참
  if (arr[from] + arr[to] > limit[to]) {
    arr[from] -= limit[to] - arr[to];
    arr[to] = limit[to];
  } else {
    // 도착 물통 가득 안참
    arr[to] += arr[from];
    arr[from] = 0;
  }
};

const solve = (a, b, c) => {
  if (visited[a][b][c]) return;
  visited[a][b][c] = true;

  if (a === 0) {
    // 첫 번째 물통 비어있으면 C 추가
    answer.add(c);
  }

  for (let from = 0; from < 3; from++) {
    for (let to = 0; to < 3; to++) {
      if (from !== to) {
        const water = [a, b, c];
        moveWater(from, to, water); // 물 이동
        solve(water[0], water[1], water[2]); // 재귀
      }
    }
  }
};

solve(0, 0, C);
console.log([...answer].sort((a, b) => a - b).join(" "));
