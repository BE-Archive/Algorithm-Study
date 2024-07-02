let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_19/BOJ_21278_호석이두마리치킨/input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);

const arr = input.slice(1).map((v) => v.split(' ').map(Number));

const INF = Infinity;

// 그래프 초기화
const graph = Array.from(Array(N + 1), () => Array(N + 1).fill(INF));
arr.forEach(([a, b]) => {
  graph[a].push(b);
  graph[b].push(a);
});

// 자기 자신 거리 0
for (let i = 1; i <= N; i++) {
  graph[i][i] = 0;
}

// 도로 정보 입력
arr.forEach(([a, b]) => {
  graph[a][b] = 1;
  graph[b][a] = 1;
});

// 플로이드 워셜
for (let k = 1; k <= N; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= N; j++) {
      if (graph[i][j] > graph[i][k] + graph[k][j]) {
        graph[i][j] = graph[i][k] + graph[k][j];
      }
    }
  }
}

// 가능한 치킨집의 조합 중 최소 비용 계산
let minSum = INF;
let answer = [];

const getSum = (a, b) => {
  let sum = 0;

  for (let i = 1; i <= N; i++) {
    if (i !== a && i !== b) {
      sum += Math.min(graph[i][a], graph[i][b]) * 2;
    }
  }

  if (sum < minSum) {
    minSum = sum;
    answer = [a, b];
  }
};

const check = () => {
  for (let i = 1; i <= N; i++) {
    for (let j = i + 1; j <= N; j++) {
      getSum(i, j);
    }
  }
};

check();

console.log(answer.join(' '), minSum);
