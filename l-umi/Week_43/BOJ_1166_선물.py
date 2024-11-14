import sys
input = sys.stdin.readline

n, l, w, h = map(int, input().split())

######################################################################

def can_fit_boxes(n, l, w, h, a):
    
    boxes_per_length = l // a
    boxes_per_width = w // a
    boxes_per_height = h // a
    
    total_boxes = boxes_per_length * boxes_per_width * boxes_per_height
    
    return total_boxes >= n

def find_max_box_size(n, l, w, h):
    
    left = 0
    right = min(l, w, h)  
    epsilon = 1e-9 

    for _ in range(40): 
        mid = (left + right) / 2
        
        if can_fit_boxes(n, l, w, h, mid):
            left = mid
        else:
            right = mid
    
    return left

######################################################################

result = find_max_box_size(n, l, w, h)

print(result)