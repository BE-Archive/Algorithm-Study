tgt=str(input())
a_cnt=tgt.count("a")
min_val=9999
for idx in range(len(tgt)):
    now=[]
    if idx+a_cnt<=len(tgt):
        now=tgt[idx:idx+a_cnt]
    else:
        now=tgt[idx:]+tgt[0:(idx+a_cnt)%len(tgt)]
    min_val=min(min_val,now.count("b"))
print(min_val)