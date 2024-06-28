let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_23/BOJ_2179_비슷한단어/input.txt')
  .toString()
  .trim()
  .split('\n');

let slist = input.slice(1).map((v, i) => [v.trim(), i]);

slist.sort();

const findLength = (s1, s2) => {
  let length = 0;
  for (let i = 0; i < Math.min(s1.length, s2.length); i++) {
    if (s1[i] !== s2[i]) break;
    length++;
  }
  return length;
};

let maxLength = 0;
let answer = [];

for (let i = 0; i < slist.length - 1; i++) {
  for (let j = i + 1; j < slist.length; j++) {
    let nowLength = findLength(slist[i][0], slist[j][0]);

    if (nowLength > maxLength) {
      maxLength = nowLength;
      answer = [slist[i], slist[j]];
    } else if (nowLength === maxLength) {
      if (
        answer.length === 0 ||
        slist[i][1] < answer[0][1] ||
        (slist[i][1] === answer[0][1] && slist[j][1] < answer[1][1])
      ) {
        answer = [slist[i], slist[j]];
      }
    }
  }
}

console.log(answer[0][0]);
console.log(answer[1][0]);
