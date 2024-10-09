let input = require("fs")
  .readFileSync("c:/algo/Algorithm_Study/yunva17/Week_38/BOJ_1063_킹/input.txt")
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [king, rock, N] = input[0].split(" ");

let kingPoint = [king[0].charCodeAt(), Number(king[1])];
let rockPoint = [rock[0].charCodeAt(), Number(rock[1])];

const list = input.slice(1).map((v) => v.replace(/\r/g, ""));
const dir = {
  R: [1, 0],
  L: [-1, 0],
  B: [0, -1],
  T: [0, 1],
  RT: [1, 1],
  LT: [-1, 1],
  RB: [1, -1],
  LB: [-1, -1],
};

const isMap = ([x, y]) => {
  return x >= 65 && x <= 72 && y >= 1 && y <= 8;
};

for (let i = 0; i < list.length; i++) {
  let [dx, dy] = dir[list[i]];
  let [nx, ny] = [kingPoint[0] + dx, kingPoint[1] + dy];

  if (!isMap([nx, ny])) continue;

  if (nx === rockPoint[0] && ny === rockPoint[1]) {
    let rockMove = [dx + rockPoint[0], dy + rockPoint[1]];
    if (!isMap(rockMove)) continue;

    rockPoint = rockMove;
  }

  kingPoint = [nx, ny];
}

console.log(String.fromCharCode(kingPoint[0]) + kingPoint[1]);
console.log(String.fromCharCode(rockPoint[0]) + rockPoint[1]);
