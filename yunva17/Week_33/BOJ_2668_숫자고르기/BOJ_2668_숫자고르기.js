let input = require('fs')
  .readFileSync(
    'E:/ssafy/Algorithm_Study/yunva17/Week_33/BOJ_2668_숫자고르기/input.txt'
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const list = input.map(Number);
let answer = [];
let visited = Array(list[0] + 1).fill(false);

const dfs = (start, now, arr) => {
  if (visited[now]) {
    if (start === now) {
      answer.push(...arr);
    }
    return;
  }

  visited[now] = true;
  arr.push(now);

  dfs(start, list[now], arr);

  // 백트래킹
  visited[now] = false;
  arr.pop();
};

for (let i = 1; i <= list.length; i++) {
  dfs(i, i, []);
}

answer = [...new Set(answer)];
console.log(answer.length);
answer.sort((a, b) => a - b).forEach((v) => console.log(v));
