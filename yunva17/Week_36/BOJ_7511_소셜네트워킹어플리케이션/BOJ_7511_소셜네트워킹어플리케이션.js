const { default: test } = require("node:test");

let input = require("fs")
  .readFileSync(
    "c:/Algorithm_Study/yunva17/Week_36/BOJ_7511_소셜네트워킹어플리케이션/input.txt"
  )
  // .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split("\n");

const test_case = Number(input[0]);
let line = 1;
let answers = [];
let parents = [];

const makeSet = (num) => {
  parents = Array.from({ length: num }, (_, i) => i);
};

const find = (a) => {
  if (parents[a] === a) return a;

  return (parents[a] = find(parents[a]));
};

const union = (a, b) => {
  const rootA = find(a);
  const rootB = find(b);

  if (rootA !== rootB) {
    parents[rootB] = rootA;
  }
};

for (let t = 0; t < test_case; t++) {
  answers.push(`Scenario ${t + 1}:`);

  const n = Number(input[line++]);
  const k = Number(input[line++]);

  makeSet(n);

  for (let i = 0; i < k; i++) {
    const [a, b] = input[line++].split(" ").map(Number);
    union(a, b);
  }

  const m = Number(input[line++]);

  for (let i = 0; i < m; i++) {
    const [a, b] = input[line++].split(" ").map(Number);
    if (find(a) === find(b)) {
      answers.push("1");
    } else {
      answers.push("0");
    }
  }

  if (t < test_case - 1) {
    answers.push("");
  }
}

console.log(answers.join("\n"));
