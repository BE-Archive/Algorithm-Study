let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_24/BOJ_21606_아침산책/input.txt')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);
const inside = input[1].split('').map(Number);

let arr = input.slice(2).map((v) => v.split(' ').map(Number));

let graph = Array.from({ length: N + 1 }, () => []);

arr.forEach(([a, b]) => {
  graph[a].push(b);
  graph[b].push(a);
});

let answer = 0;
let visited = Array(N + 1).fill(false);

const solve = (start) => {
  let stack = [start];
  visited[start] = true;

  let count = 0;

  while (stack.length > 0) {
    let now = stack.pop();

    visited[now] = true;

    for (let next of graph[now]) {
      if (visited[next]) continue;

      visited[next] = true;

      if (inside[next - 1] === 0) {
        stack.push(next);
      } else if (inside[next - 1] === 1) {
        count++;
      }
    }
  }

  return count;
};

for (let i = 0; i < inside.length; i++) {
  // 시작점이 실내여야 함
  if (inside[i] === 1) {
    visited.fill(false);
    // 탐색 시작!
    answer += solve(i + 1);
  }
}

console.log(answer);
