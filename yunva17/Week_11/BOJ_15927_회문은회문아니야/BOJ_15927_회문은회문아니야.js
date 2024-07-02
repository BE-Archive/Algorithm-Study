let input = require('fs')
  .readFileSync('dev/stdin')
  .toString()
  .trim()
  .split('\n');

const str = input.shift();

let isPal = true;

const len = str.length;
for (let i = 0; i < Math.floor(len / 2); i++) {
  if (str[i] !== str[len - i - 1]) {
    console.log(str.length);
    return;
  } else if (str[i] !== str[i + 1]) {
    isPal = false;
  }
}
if (!isPal) {
  console.log(str.length - 1);
} else {
  console.log(-1);
}
