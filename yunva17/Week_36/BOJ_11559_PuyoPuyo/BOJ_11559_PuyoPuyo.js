let input = require("fs")
  .readFileSync(
    "c:/Algorithm_Study/yunva17/Week_36/BOJ_11559_PuyoPuyo/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const map = input.map((v) => v.split(""));
const dir = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

// 떨어진다
const drop = () => {
  for (let i = 0; i < 6; i++) {
    let row = 11;
    for (let j = 11; j >= 0; j--) {
      if (map[j][i] !== ".") {
        // 떨어져요
        [map[row][i], map[j][i]] = [map[j][i], map[row][i]];
        row--;
      }
    }
  }
};

// 이어지나?
const isLinked = (x, y) => {
  let queue = [];
  let visited = Array.from({ length: 12 }, () => new Array(6).fill(false));

  visited[x][y] = true;
  queue.push([x, y]);

  // 없어져야할 좌표들
  let pointList = [[x, y]];

  while (queue.length > 0) {
    let [nx, ny] = queue.pop();

    for (const d of dir) {
      let [dx, dy] = [nx + d[0], ny + d[1]];

      if (dx < 0 || dy < 0 || dx >= 12 || dy >= 6 || visited[dx][dy]) continue;

      if (map[dx][dy] === map[x][y]) {
        visited[dx][dy] = true;
        queue.push([dx, dy]);
        pointList.push([dx, dy]);
      }
    }
  }
  if (pointList.length >= 4) {
    // 4개 이상 연결
    for (const [px, py] of pointList) {
      map[px][py] = ".";
    }
    return true;
  }
  return false;
};

// 게임 진행
let count = 0;
while (true) {
  // 뿌요야 터지니
  let isBreak = false;

  for (let i = 0; i < 12; i++) {
    for (let j = 0; j < 6; j++) {
      if (map[i][j] !== "." && isLinked(i, j)) {
        isBreak = true;
      }
    }
  }

  if (!isBreak) break; // 뿌요 없어요

  count++;
  drop();
}

console.log(count);
