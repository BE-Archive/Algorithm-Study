let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_39/BOJ_9996_한국이그리울땐서버에접속하지/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const pattern = input[1].split("*").map((v) => v.replace(/\r/g, ""));
const list = input.slice(2).map((v) => v.replace(/\r/g, ""));

for (let i = 0; i < list.length; i++) {
  const front = list[i].slice(0, pattern[0].length) === pattern[0];
  const back =
    list[i].slice(pattern[0].length).slice(-pattern[1].length) === pattern[1];

  if (front && back) {
    console.log("DA");
  } else {
    console.log("NE");
  }
}
