let input = require("fs")
  // .readFileSync('/dev/stdin')
  .readFileSync("Week_28/BOJ_1254_팰린드롬만들기/input.txt")
  .toString()
  .trim()
  .split("\n");

const s = input[0];

const isPal = (str) => {
  for (let i = 0; i < Math.floor(str.length / 2); i++) {
    if (str[i] !== str[str.length - i - 1]) {
      return false;
    }
  }
  return true;
};

let answer = s.length;
for (let i = 0; i < s.length; i++) {
  if (isPal(s.slice(i))) {
    answer = s.length + i;
    break;
  }
}

console.log(answer);
