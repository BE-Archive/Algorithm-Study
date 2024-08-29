from collections import deque

#initial_state 초기 상태
#goal_state 정리된 상태
#visited 최소 이동 횟수 계산을 위한 딕셔너리(맵)

def solve_puzzle(initial_state):
    goal_state = "123456780"
    queue = deque([(initial_state, 0)])
    visited = {initial_state: 0}

    while queue:
        current_state, moves = queue.popleft()
        
        if current_state == goal_state:
            return moves
        
        # 빈 칸 찾기
        zero_index = current_state.index('0')
        row, col = divmod(zero_index, 3)
        
        # 빈 칸으로 수 이동시키기
        for dr, dc in ((0, 1), (1, 0), (0, -1), (-1, 0)):
            new_row, new_col = row + dr, col + dc
            if 0 <= new_row < 3 and 0 <= new_col < 3:
                new_index = new_row * 3 + new_col
                new_state = list(current_state)
                new_state[zero_index], new_state[new_index] = new_state[new_index], new_state[zero_index]
                new_state = ''.join(new_state)
                
                if new_state not in visited or visited[new_state] > moves + 1:
                    visited[new_state] = moves + 1
                    queue.append((new_state, moves + 1))
    
    return -1

#############################################
initial_board = []
for _ in range(3):
    initial_board.extend(input().split())
initial_state = ''.join(initial_board)

result = solve_puzzle(initial_state)
print(result)