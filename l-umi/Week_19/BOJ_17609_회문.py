import sys

#31308KB 140ms
T = int(input())

for _ in range(T):
    string = sys.stdin.readline().strip()
    halflength = (len(string)+1)//2

    # 회문
    if string == string[::-1] :
        print(0)
        continue

    # 유사회문
    for i in range(halflength):
        # 앞에서 i번째 문자와 뒤에서 i번째 문자가 다를 때
        if string[i] != string[-(i+1)] :
            # string[i] 삭제
            string1 = string[:i] + string[i+1:]
            # string[-i-1] 삭제
            string2 = string[:-i-1] + (string[-i:] if i>0 else "")

            if string1 == string1[::-1] or string2 == string2[::-1]:
                print(1)
            else:
                print(2)
            break
