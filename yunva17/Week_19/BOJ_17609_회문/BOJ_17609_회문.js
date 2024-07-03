let input = require('fs')
  // .readFileSync('/dev/stdin')
  .readFileSync('Week_19/BOJ_17609_회문/input.txt')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);

const arr = input.slice(1).map((v) => v.replace(/\r/g, ''));

// 비교
const compare = (word) => {
  let len = word.length;
  let isPal = true;
  //회문
  for (let i = 0; i < Math.floor(len / 2); i++) {
    if (word[i] !== word[len - i - 1]) {
      isPal = false;
      break;
    }
  }

  return isPal;
};

const isCheck = (word) => {
  let left = 0;
  let right = word.length - 1;
  while (left < right) {
    if (word[left] !== word[right]) {
      // 왼쪽 문자 제거 후 나머지 확인
      if (compare(word.slice(left + 1, right + 1))) {
        return true;
      }
      // 오른쪽 문자 제거 후 나머지 확인
      if (compare(word.slice(left, right))) {
        return true;
      }
      return false;
    }
    left++;
    right--;
  }
  return false;
};

for (let i = 0; i < arr.length; i++) {
  let answer = 2;
  // 회문
  if (compare(arr[i])) {
    answer = 0;
    // 유사회문
  } else if (isCheck(arr[i])) {
    answer = 1;
  }

  console.log(answer);
}
