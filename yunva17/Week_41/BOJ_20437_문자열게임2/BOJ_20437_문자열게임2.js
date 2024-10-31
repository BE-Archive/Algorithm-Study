let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_41/BOJ_20437_문자열게임2/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const T = Number(input.shift());

for (let t = 0; t < T; t++) {
  let str = input.shift();
  let K = Number(input.shift());
  let map = {};
  // 각 문자열 마다 문자열 위치 추가
  str.split("").map((v, i) => {
    if (!map[v]) map[v] = [];
    map[v].push(i);
  });

  let min = Infinity;
  let max = -1;

  Object.values(map)
    .filter((v) => v.length >= K)
    .map((v) => {
      for (let i = 0; i <= v.length - K; i++) {
        const start = v[i];
        const end = v[i + K - 1];
        const length = end - start + 1;

        //최소 길이
        min = Math.min(min, length);

        // 최대 길이
        if (str[start] === str[end]) {
          max = Math.max(max, length);
        }
      }
    });

  if (min === Infinity || max === -1) {
    console.log(-1);
  } else {
    console.log(min, max);
  }
}
