from itertools import permutations

def generate_sequences(N, M, numbers):
    # 주어진 숫자들 중에서 M개를 선택하는 모든 순열을 생성
    sequences = permutations(sorted(numbers), M)
    
    # 각 순열을 정렬된 순서로 출력
    for seq in sequences:
        print(' '.join(map(str, seq)))

# 입력 받기
N, M = map(int, input().split())
numbers = list(map(int, input().split()))

# 수열 생성 및 출력
generate_sequences(N, M, numbers)
