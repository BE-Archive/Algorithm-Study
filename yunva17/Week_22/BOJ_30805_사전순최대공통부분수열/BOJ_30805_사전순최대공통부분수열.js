let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_22/BOJ_30805_사전순최대공통부분수열/input.txt')
  .toString()
  .trim()
  .split('\n');

const a = Number(input[0]);
let alist = input[1].split(' ').map(Number);

const b = Number(input[2]);
let blist = input[3].split(' ').map(Number);

const searchlist = alist.slice().sort((a, b) => b - a);

let aIndex = 0,
  bIndex = 0;
const answer = [];

searchlist.forEach((v) => {
  if (alist.includes(v) && blist.includes(v)) {
    answer.push(v);
  }

  if (aIndex < a && bIndex < b && alist[aIndex] === v && blist[bIndex] === v) {
    aIndex++;
    bIndex++;
  }
});

// 결과 출력
console.log(answer.length);
console.log(answer.join(' '));
