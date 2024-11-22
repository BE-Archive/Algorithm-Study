import sys
input = sys.stdin.readline

n = int(input())

##############################################
for _ in range(n):
   nums = list(map(int, input().split()))
   total, arr = nums[0], nums[1:]
   
   cnt = {}
   for x in arr:
       cnt[x] = cnt.get(x, 0) + 1
   
   ans = -1
   for num, val in cnt.items():
       if val > total//2:
           ans = num
           break
           
   print("SYJKGW" if ans == -1 else ans)
