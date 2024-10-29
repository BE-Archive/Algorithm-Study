let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_41/BOJ_1972_놀라운문자열/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const list = input.slice(0, -1).map((v) => v.replace(/\r/g, ""));

for (let t = 0; t < list.length; t++) {
  let str = list[t];
  let isSurprising = true;

  for (let i = 0; i < str.length - 1; i++) {
    let strList = [];

    for (let j = 0; j < str.length - i - 1; j++) {
      let pair = str[j] + str[j + i + 1];

      if (strList.indexOf(pair) !== -1) {
        isSurprising = false;

        break;
      } else {
        strList.push(pair);
      }
    }
    if (!isSurprising) break;
  }

  if (isSurprising) {
    console.log(`${str} is surprising.`);
  } else {
    console.log(`${str} is NOT surprising.`);
  }
}
