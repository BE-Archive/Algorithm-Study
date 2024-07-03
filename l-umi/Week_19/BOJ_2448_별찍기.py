# 31120KB 488ms

T = int(input())

dot_x = [T]

for y in range(0, T//3):

    first_line = dot_x
    second_line = []
    third_line = []

    # 첫번째 줄
    for i in range(0, len(first_line)):
        if(i == 0):
            print(" " * (first_line[0] - 1), "*", sep="", end="")
        else :
            print(" "* (first_line[i] - first_line[i-1] -1), "*", sep="", end="")
        # second_dot_x랑 third_dot_x 갱신
        second_line += [first_line[i]-1, first_line[i]+1]
        third_line += [first_line[i]-2, first_line[i]+2]
    print(" " * (2*T-1 - first_line[-1]))

    # 두번째 줄
    for i in range(len(second_line)):
        if (i == 0):
            print(" " * (second_line[i] - 1), "*", sep="", end="")
        else:
            print(" " * (second_line[i] - second_line[i - 1] - 1), "*", sep="", end="")

    print(" " * (2*T-1 - second_line[-1]))

    # 세번째 줄
    for i in range(len(third_line)):
        if(i == 0):
            print(" "* (third_line[i] - 1), "*****", sep="", end="")
            dot_x = [third_line[i] - 1]
        elif(i % 2 == 0):
            print(" " * (third_line[i] - third_line[i-1] -1), "*****", sep="", end="")
            if (third_line[i] - 2 == third_line[i-1]):
                dot_x.pop()
            else:
                dot_x.append(third_line[i] - 1)
        else:
            dot_x.append(third_line[i] + 1)
    print(" " * (2*T-1 - third_line[-1]))
