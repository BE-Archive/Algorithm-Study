import sys
input = sys.stdin.readline

king_pos, stone_pos, N = input().split()
moves = [input().strip() for _ in range(int(N))]

directions = {
        'R': (1, 0), 'L': (-1, 0), 'B': (0, -1), 'T': (0, 1),
        'RT': (1, 1), 'LT': (-1, 1), 'RB': (1, -1), 'LB': (-1, -1)
    }
##########################################

#처음 위치
king_x, king_y = ord(king_pos[0]) - 65 + 1, int(king_pos[1])
stone_x, stone_y = ord(stone_pos[0]) - 65 + 1, int(stone_pos[1])

for move in moves:
    dx, dy = directions[move]
    new_king_x, new_king_y = king_x + dx, king_y + dy

    # 킹 움직임
    if 1 <= new_king_x <= 8 and 1 <= new_king_y <= 8:
        # 돌과 같은 곳으로 이동할 때
        if new_king_x == stone_x and new_king_y == stone_y:
            new_stone_x, new_stone_y = stone_x + dx, stone_y + dy
            # 범위 확인
            if 1 <= new_stone_x <= 8 and 1 <= new_stone_y <= 8:
                king_x, king_y = new_king_x, new_king_y
                stone_x, stone_y = new_stone_x, new_stone_y
        else:
            king_x, king_y = new_king_x, new_king_y
##########################################

final_king_pos = chr(king_x + 65 - 1) + str(king_y)
final_stone_pos = chr(stone_x + 65 - 1) + str(stone_y)


print(final_king_pos)
print(final_stone_pos)
