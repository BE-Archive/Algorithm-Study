import sys

input_func = sys.stdin.readline
directions_x = [0, 0, -1, 1]
directions_y = [1, -1, 0, 0]

def move_chess_piece(chess_piece_num):
    current_x, current_y, current_direction = chess_pieces[chess_piece_num]
    
    if chess_piece_num != chessboard[current_x][current_y][0]:
        return 0

    new_x = current_x + directions_x[current_direction]
    new_y = current_y + directions_y[current_direction]

    if not 0 <= new_x < board_size or not 0 <= new_y < board_size or board[new_x][new_y] == 2:
        if 0 <= current_direction <= 1:
            new_direction = (current_direction + 1) % 2
        else:
            new_direction = (current_direction - 1) % 2 + 2
        chess_pieces[chess_piece_num][2] = new_direction
        new_x = current_x + directions_x[new_direction]
        new_y = current_y + directions_y[new_direction]
        if not 0 <= new_x < board_size or not 0 <= new_y < board_size or board[new_x][new_y] == 2:
            return 0

    moved_chess_pieces = []
    moved_chess_pieces.extend(chessboard[current_x][current_y])
    chessboard[current_x][current_y] = []

    if board[new_x][new_y] == 1:
        moved_chess_pieces = moved_chess_pieces[-1::-1]

    for piece in moved_chess_pieces:
        chessboard[new_x][new_y].append(piece)
        chess_pieces[piece][:2] = [new_x, new_y]

    if len(chessboard[new_x][new_y]) >= 4:
        return 1
    return 0

board_size, num_chess_pieces = map(int, input_func().split())
board = [list(map(int, input_func().split())) for _ in range(board_size)]
chessboard = [[[] for _ in range(board_size)] for _ in range(board_size)]
chess_pieces = [0 for _ in range(num_chess_pieces)]

for i in range(num_chess_pieces):
    initial_x, initial_y, initial_direction = map(int, input_func().split())
    chessboard[initial_x - 1][initial_y - 1].append(i)
    chess_pieces[i] = [initial_x - 1, initial_y - 1, initial_direction - 1]

move_count = 1
while move_count <= 1000:
    for i in range(num_chess_pieces):
        flag = move_chess_piece(i)
        if flag:
            print(move_count)
            sys.exit()
    move_count += 1
print(-1)
