# 메모이제이션을 위한 딕셔너리
length_memo = {0: 3}


# S(k)의 길이를 계산하는 함수
def length(k):
  if k in length_memo:
    return length_memo[k]

  result = 2 * length(k - 1) + k + 3
  length_memo[k] = result
  return result


# S(k)수열에서 n번째 문자를 찾는 함수
def find_char(n, k):
  if k == 0:
    return 'm' if n == 1 else 'o'

  if n <= length(k - 1):
    return find_char(n, k - 1)
  elif n <= length(k - 1) + k + 3:
    return 'm' if n == length(k - 1) + 1 else 'o'
  else:
    return find_char(n - length(k - 1) - k - 3, k - 1)


# 주어진 n에 대해 적절한 k를 찾고 find_char를 호출하는 함수
def moo(n):
  k = 0
  while length(k) < n:
    k += 1
  return find_char(n, k)


###############################################################

print(moo(int(input())))
