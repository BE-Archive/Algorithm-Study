def solution(n, tops):
    
    a = [0]*n # 정삼각형으로 끝나는 경우
    b = [0]*n # 마름모로 끝나는 경우
    
    a[0] = 2 + tops[0]
    b[0] = 1
    
    # 점화식
    # b[i] = a[i-1]+b[i-1]
    # n == 1일때
    # a[i] = 3*a + 2*b
    # n == 0일때
    # a[i] = 2*a + b
    for i in range(1, n):
        a[i] = ( a[i-1] * (2+tops[i]) + b[i-1] * (1+tops[i]) ) % 10007
        b[i] = ( a[i-1]+b[i-1] ) % 10007
        
    return ( a[-1]+b[-1] ) % 10007
