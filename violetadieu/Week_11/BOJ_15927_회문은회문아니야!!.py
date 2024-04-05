import math
str=list(input())
if len(set(str)) == 1:
  print(-1)
elif str[:math.trunc(len(str)/2)] != list(reversed(str[math.ceil(len(str)/2):])):
  print(len(str))
else:
  print(len(str)-1)
