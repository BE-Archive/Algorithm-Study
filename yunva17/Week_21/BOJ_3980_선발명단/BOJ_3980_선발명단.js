let input = require('fs')
  //  .readFileSync('/dev/stdin')
  .readFileSync('Week_21/BOJ_3980_선발명단/input.txt')
  .toString()
  .trim()
  .split('\n');

const C = Number(input[0]);
let arr = input.slice(1).map((v) => v.split(' ').map(Number));

let index = 0; // 테스트 케이스 인덱스
let ability, used;

const backtracking = (count, sum) => {
  // 배치 완료
  if (count === 11) {
    return sum;
  }

  let maxSum = 0;
  for (let i = 0; i < 11; i++) {
    if (!used[i] && ability[i][count] > 0) {
      used[i] = true;
      maxSum = Math.max(
        maxSum,
        backtracking(count + 1, sum + ability[i][count])
      );
      used[i] = false;
    }
  }
  return maxSum;
};

for (let t = 0; t < C; t++) {
  // 새로운 테스트 케이스 배열 설정
  ability = arr.slice(index, index + 11);
  index += 11;

  // 포지션 배치
  used = Array(11).fill(false);

  console.log(backtracking(0, 0));
}
