let input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const searchNum = parseInt(input[0].split(' ')[1]);
let sites = {};
let search = input.slice(-searchNum);
search = search.map((v) => v.replace(/\r/g, ''));

input.slice(1, input.length - searchNum).map((v, i) => {
  let list = v.split(' ');
  sites[list[0]] = list[1].replace(/\r/g, '');
});

search.forEach((v, i) => {
  if (sites[v]) {
    console.log(sites[v]);
  }
});
