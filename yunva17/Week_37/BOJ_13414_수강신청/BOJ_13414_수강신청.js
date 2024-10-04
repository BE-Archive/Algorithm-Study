let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_37/BOJ_13414_수강신청/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const [K, L] = input[0].split(" ").map(Number);
const student = new Map();

for (let i = 1; i <= L; i++) {
  student.set(input[i], i);
}

[...student.entries()]
  .sort((a, b) => a[1] - b[1])
  .slice(0, K)
  .map((v) => console.log(v[0]));
