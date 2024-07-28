let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_27/BOJ_15684_사다리조작/input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, M, H] = input[0].split(" ").map(Number);
const widthList = input.slice(1).map((v) => v.split(" ").map(Number));

const ladder = Array.from({ length: H + 1 }, () => Array(N + 1).fill(0));
widthList.forEach(([a, b]) => {
  ladder[a][b] = 1;
});

// 가로선을 선택하고 ~ 안선택하고 ~ 백트래킹

// 가로선 선택

// 사다리 타기
const downLadder = (start, selectedWidth) => {
  let now = start;
  selectedWidth.sort((a, b) => a[0] - b[0]);

  for (let i = 1; i <= H; i++) {
    const leftLine = selectedWidth.find((v) => v[0] === i && v[1] === now - 1);
    const rightLine = selectedWidth.find((v) => v[0] === i && v[1] === now);

    if (leftLine) now--;
    else if (rightLine) now++;
  }

  return now;
};
