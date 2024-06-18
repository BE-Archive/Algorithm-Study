ip = input()
ips = ip.split(":")

if len(ips[0])==0:
    ips=ips[1:]
elif len(ips[-1])==0:
    ips=ips[:-1]

result = ""
for item in ips:
    if len(item)==0:
        result+="0000:"*(9-len(ips))
    else:
        result+=item.zfill(4)+":"

print(result[:-1])