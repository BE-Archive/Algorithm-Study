let input = require('fs')
  //  .readFileSync('/dev/stdin')
  .readFileSync('Week_21/BOJ_1661_다솜이의신발가게/input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, P] = input[0].split(' ').map(Number);
let product = input.slice(1).map((v) => v.split(' ').map(Number));
let dis = [0.99, 0.98, 0.97];

let answer = P;

product.sort((a, b) => {
  return a[0] === b[0] ? b[1] - a[1] : a[0] - b[0];
});

const solve = (index, cost, discount) => {
  if (index === N) {
    const price = P * discount + cost;
    answer = Math.min(answer, price);
    return;
  }

  // 현재 제품을 사지 않는 경우
  solve(index + 1, cost, discount);

  // 현재 제품을 사는 경우
  const newCost = cost + product[index][0];
  const newDiscount = discount * dis[product[index][1] - 1];
  solve(index + 1, newCost, newDiscount);
};

solve(0, 0, 1);

console.log(answer.toFixed(6));
