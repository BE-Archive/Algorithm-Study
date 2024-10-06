import sys

K, L = map(int, sys.stdin.readline().split())
waiting_list = {}

for _ in range(L):
    student_id = sys.stdin.readline() #줄바꿈포함
    if student_id in waiting_list:
        del waiting_list[student_id]
    waiting_list[student_id] = None

print("".join(list(waiting_list.keys())[:K]))
