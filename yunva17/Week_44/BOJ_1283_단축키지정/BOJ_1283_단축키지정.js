let input = require('fs')
  .readFileSync(
    'e:/ssafy/Algorithm_Study/yunva17/Week_44/BOJ_1283_단축키지정/input.txt'
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const strList = input.slice(1).map((v) => v.replace(/\r/g, '').split(' '));
let shortcut = [];

for (let i = 0; i < strList.length; i++) {
  let option = strList[i];
  let found = false;
  for (let j = 0; j < option.length; j++) {
    let first = option[j][0].toUpperCase();

    // 앞 글자 확인
    if (!shortcut.includes(first)) {
      shortcut.push(first);
      option[j] = `[${option[j][0]}]${option[j].slice(1)}`;
      found = true;
      break;
    }
  }

  // 앞 글자가 모두 사용 중
  if (!found) {
    for (let j = 0; j < option.length; j++) {
      for (let k = 0; k < option[j].length; k++) {
        let str = option[j][k].toUpperCase();
        if (!shortcut.includes(str)) {
          shortcut.push(str);
          option[j] =
            option[j].slice(0, k) +
            `[${option[j][k]}]` +
            option[j].slice(k + 1);
          found = true;
          break;
        }
      }
      if (found) break;
    }
  }

  // 단축키 X
  strList[i] = option.join(' ');
}

strList.forEach((v) => console.log(v));
