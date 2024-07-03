import sys
input = sys.stdin.readline

N = int(input())
wordList = [input().rstrip() for n in range(N)]
words = sorted(wordList)

max_prefix_len = 0
prefixs = set()

for n in range(1, N):
    prefix_len = 0

    for i in range(min(len(words[n-1]), len(words[n]))):
        if words[n-1][i] == words[n][i] :
            prefix_len = i+1
        else:
            break

    if prefix_len > max_prefix_len :
        prefixs = set()
        prefixs.add(words[n-1][:prefix_len])
        max_prefix_len = prefix_len
    elif max_prefix_len > 0 and prefix_len == max_prefix_len :
        prefixs.add(words[n-1][:prefix_len])

if not prefixs :
    print(wordList[0])
    print(wordList[1])
    exit()

prefix = None
for word in wordList :
    if len(word) < max_prefix_len : continue
    w = word[:max_prefix_len]
    if prefix == None and w in prefixs:
        prefix = w
        print(word)
    elif w == prefix :
        print(word)
        break




''' trie 알고리즘
import sys
input = sys.stdin.readline

N = int(input())
wordList = []
t = dict() #딕셔너리안에 딕셔너리안에 딕셔너리..
max_prefix = ""
first_index = sys.maxsize
second_index = -1


def find_index(p):
    for k in p:
        if k == "end":
            return p["end"]
        else:
            return find_index(p[k])

for i in range(N) :
    word = input().rstrip()
    wordList.append(word)

    prefix = ""
    prefix_t = None
    flag = True

    temp = t
    for c in word:
        if c in temp: #이미 존재한 접두사라면
            if flag: #계속 들어가기
                prefix += c
                prefix_t = temp[c]
        else: #존재하지 않는 접두사라면
            flag = False
            temp[c] = dict()

        temp = temp[c]


    if len(prefix) > len(max_prefix):
        max_prefix = prefix
        first_index = find_index(prefix_t)
        second_index = i

    elif len(max_prefix) > 0 and len(prefix) == len(max_prefix):
        index = find_index(prefix_t)
        if first_index > index:
            max_prefix = prefix
            first_index = index
            second_index = i

    temp["end"]=i

print(wordList[first_index])
print(wordList[second_index])
'''

''' 처음 풀이 (시간초과)
import sys
input = sys.stdin.readline

N = int(input())

wordList = [input().rstrip() for _ in range(N)]

answer = (0, "", "")
for i in range(N) :
    for j in range(i+1, N):
        #접두사 길이 계산
        w1, w2 = wordList[i], wordList[j]
        cnt = 0
        while cnt < len(w1) and cnt < len(w2) :
            if w1[cnt] == w2[cnt] :
                cnt+=1
            else:
                break
        if cnt > answer[0] and w1 != w2 :
            answer = (cnt, w1, w2)
        # print(wordList[i], wordList[j], cnt)

print(answer[1])
print(answer[2])
'''