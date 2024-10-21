let input = require("fs")
  .readFileSync(
    "c:/algo/Algorithm_Study/yunva17/Week_40/BOJ_17175_피보나치는지겨웡/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const n = Number(input);
let dp = Array.from({ length: n + 1 }, () => 0);

const fibonacci = (n) => {
  dp[0] = 1;
  dp[1] = 1;

  for (let i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2] + 1;
  }

  return dp[n];
};

console.log(fibonacci(n) % 1000000007);
