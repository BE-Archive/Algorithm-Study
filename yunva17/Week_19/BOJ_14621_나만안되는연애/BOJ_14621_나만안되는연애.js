let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_19/BOJ_14621_나만안되는연애/input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const gender = input[1].split(' ');

const arr = input
  .slice(2)
  .map((x) => x.split(' ').map(Number))
  .sort((a, b) => a[2] - b[2]);

const parents = Array.from(Array(N + 1), () => -1);

// 유니온 파인드
const find = (a) => {
  if (parents[a] < 0) return a;

  return (parents[a] = find(parents[a]));
};

const union = (a, b) => {
  let rootA = find(a);
  let rootB = find(b);

  if (rootA === rootB) {
    return false;
  }

  if (rootA < rootB) {
    parents[rootA] += parents[rootB];
    parents[rootB] = rootA;
  } else {
    parents[rootB] += parents[rootA];
    parents[rootA] = rootB;
  }

  return true;
};

let answer = 0;
let count = 0;

for (const [u, v, d] of arr) {
  if (gender[u - 1] !== gender[v - 1]) {
    if (union(u, v)) {
      answer += d;

      if (++count === N - 1) break;
    }
  }
}

console.log(count === N - 1 ? answer : -1);
