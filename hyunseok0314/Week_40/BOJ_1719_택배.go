package main

import (
    "fmt"
    "bufio"
    "os"
)

func min(x int, y int) int {
	if x < y{
	    return x
	}
	return y
}

func main() {
    reader := bufio.NewReader(os.Stdin)
    writer := bufio.NewWriter(os.Stdout)
        defer writer.Flush()
    
	var n, m int
	fmt.Fscan(reader, &n, &m)
	edges := make([][][]int, n)
	dist := make([][]int, n)
	start := make([][] int, n)
	for i:= 0; i < n; i++ {
	    edges[i] = make([][]int, n)
	    dist[i] = make([]int, n)
	    start[i] = make([]int, n)
	    for j:=0; j < n; j++{
	        dist[i][j] = 100000000
	    }
	    dist[i][i] = 0
	}
	
	for i:= 0; i < m; i++ {
	    var u, v, w int
	    fmt.Fscan(reader, &u, &v, &w)
	    u, v = u - 1, v - 1
	    edges[u] = append(edges[u], []int{v, w})
	    edges[v] = append(edges[v], []int{u, w})
	    dist[u][v] = w
	    dist[v][u] = w
	    start[u][v] = v
	    start[v][u] = u
	}
	
	for k:=0; k < n; k++{
	    for i:=0; i < n; i++{
	        for j:=0; j < n; j++{
	            d := dist[i][k] + dist[k][j]
	            if d < dist[i][j]{
	                dist[i][j] = d
	                start[i][j] = start[i][k]
	            }
	        }
	    }
	}
	
	for i := 0; i < n; i++ {
	    for j := 0; j < n; j++{
	        if i == j{
	            fmt.Fprint(writer, "-")
	        }else{
	            fmt.Fprint(writer, start[i][j] + 1)
	        }
	        fmt.Fprint(writer, " ")
	    }
	    fmt.Fprintln(writer)
	}
}
