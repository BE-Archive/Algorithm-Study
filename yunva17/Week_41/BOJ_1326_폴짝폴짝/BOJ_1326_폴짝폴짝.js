let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_41/BOJ_1326_폴짝폴짝/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const N = Number(input[0]);
const stones = input[1].split(" ").map(Number);
const [a, b] = input[2]
  .split(" ")
  .map(Number)
  .map((v) => v - 1);
let visited = Array.from({ length: N }, () => false);
let answer = -1;

const bfs = (start) => {
  const queue = [];

  visited[start] = true;
  queue.push([start, 0]);

  while (queue.length > 0) {
    let [now, nowCount] = queue.shift();

    if (now === b) {
      answer = nowCount;
      return;
    }

    // 다음 이동(앞)
    for (let i = now + stones[now]; i < N; i += stones[now]) {
      if (visited[i]) continue;

      queue.push([i, nowCount + 1]);
      visited[i] = true;
    }

    // 다음 이동(전)
    for (let i = now - stones[now]; i >= 0; i -= stones[now]) {
      if (visited[i]) continue;

      queue.push([i, nowCount + 1]);
      visited[i] = true;
    }
  }
};

bfs(a);

console.log(answer);
