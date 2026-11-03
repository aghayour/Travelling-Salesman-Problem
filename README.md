# Travelling Salesman Problem

An exact Travelling Salesman solver in Java, written in spring 2018 for my
_Design of Algorithms_ course.

## What it does

Given a cost matrix for a set of cities (directed or undirected, with
`Infinity` for missing edges), it finds the cheapest tour that starts at the
first city, visits every other city exactly once and returns to the start.

The solver is the Held-Karp dynamic-programming algorithm. `DynamicProgramming`
builds a table with one row per subset of the non-start cities and one column
per city, fills each cell with the minimum cost of finishing the tour from that
city through that subset, memoises the results, and then reconstructs the
optimal path from the table. It is exact, so its running time grows as
O(n² · 2ⁿ); the bundled examples go up to ten cities.

- `Main` holds the sample cost matrices and prints the full dynamic-programming
  table, the tour length and the tour.
- `DynamicProgramming` is the solver.
- `ArraysPlus` generates the city subsets and merges arrays.

`Graphs-p1.jpg` and `Graphs-p2.jpg` are the graphs used to build and test the
code.

## Run

```sh
javac -d bin src/*.java
java -cp bin Main
```

To solve a different instance, edit the `graph` matrix and `cityNames` in
`Main.java`. The matrix must be square and `cityNames` must have one entry per
row.
