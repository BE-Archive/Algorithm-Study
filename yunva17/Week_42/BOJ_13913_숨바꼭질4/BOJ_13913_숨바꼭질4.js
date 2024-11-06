let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_42/BOJ_13913_숨바꼭질4/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [N, K] = input[0].split(" ").map(Number);
let answerList = [];
let visited = Array.from({ length: 100001 }, () => false);
let path = Array(100001).fill(-1);

const bfs = (start) => {
  let queue = [];

  queue.push(start);
  visited[start] = true;

  while (queue.length > 0) {
    let now = queue.shift();

    if (now === K) {
      for (let i = K; i !== -1; i = path[i]) {
        answerList.push(i);
      }
      return;
    }

    let dir = [now - 1, now + 1, now * 2];

    for (let i = 0; i < dir.length; i++) {
      let next = dir[i];
      if (next < 0 || next > 100000 || visited[next]) continue;

      queue.push(next);
      visited[next] = true;
      path[next] = now; // 이전 위치 저장
    }
  }
};

bfs(N);

console.log(answerList.length - 1);
console.log(answerList.reverse().join(" "));
