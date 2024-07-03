import sys

DB = [
    "  *  ",
    " * * ",
    "*****"
]
MAP = [[' ' for _ in range(6500)] for _ in range(3500)]

def solve(n, y, x):
    if n == 1:
        for i in range(3):
            for j in range(5):
                MAP[y + i][x + j] = DB[i][j]
        return
    solve(n // 2, y, x + 3 * n // 2)
    solve(n // 2, y + 3 * n // 2, x)
    solve(n // 2, y + 3 * n // 2, x + 3 * n)

def main():
    n = int(input())
    solve(n // 3, 0, 0)
    for i in range(n):
        print(''.join(['*' if MAP[i][j] == '*' else ' ' for j in range(2 * n - 1)]))

if __name__ == "__main__":
    main()

