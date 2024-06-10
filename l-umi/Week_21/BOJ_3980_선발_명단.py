import sys
input = sys.stdin.readline
# 31120KB 64ms
C = int(input())
visited = [False] * 11
result = [0]*11

def recursive(position):
    global maxValue

    if position == 11 :
        value = sum(abilities[result[p]][p] for p in range(11))
        if maxValue < value :
            maxValue = value
        # print(result, value)
        return

    for m in possible[position] :
        if not visited[m] :
            visited[m] = True
            result[position] = m
            recursive(position+1)
            visited[m] = False


for _ in range(C):
    maxValue = 0
    # abilities: 각 선수들의 포지션 능력 S_ij
    abilities = []
    # possible: 가능한 포지션과 선수들 (key:포지션 넘버, value:선수들 번호)
    possible = {i: [] for i in range(11)}

    # 입력받기
    for member in range(11) :
        ability = list(map(int, input().rstrip().split()))
        for position in range(11):
            if ability[position] != 0 :
                possible[position].append(member)
        abilities.append(ability)

    # 재귀 호출
    recursive(0)

    # 정답 출력
    print(maxValue)
