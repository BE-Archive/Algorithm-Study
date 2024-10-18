import sys
input = sys.stdin.readline

N = int(input())  # 파일의 개수
pattern = input().strip()  # 패턴
files = [input().strip() for _ in range(N)]  # 파일 이름 리스트

##############################################

pre, post = pattern.split('*')

for file in files:
    if len(file) < len(pre) + len(post):
        print("NE")
    else:
        if file[:len(pre)] == pre and file[-len(post):] == post:
            print("DA")
        else:
            print("NE")
