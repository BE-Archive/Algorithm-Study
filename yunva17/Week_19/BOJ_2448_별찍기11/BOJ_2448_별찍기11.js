let input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const n = Number(input);

// 2차원 배열 만들기
const map = Array.from(Array(n), () => Array(n * 2).fill(' '));

const printStar = (x, y, size) => {
  // 별그리기
  if (size === 3) {
    map[x][y] = '*';
    map[x + 1][y - 1] = '*';
    map[x + 1][y + 1] = '*';

    for (let i = 0; i < 5; i++) {
      map[x + 2][y - 2 + i] = '*';
    }
    return;
  }

  const half = size / 2;
  printStar(x, y, half);
  printStar(x + half, y - half, half);
  printStar(x + half, y + half, half);
};

printStar(0, n - 1, n);

for (let i = 0; i < n; i++) {
  console.log(map[i].join(''));
}
