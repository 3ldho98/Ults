public class Problem {
    public int countIslands(int[][] grid) {
//        Given a 2D matrix of 1s and 0s, count the number of islands. An island is a group of
//        1s that are connected horizontally or vertically. Once a cell is part of an island, it
//        cannot be counted as part of another island.
//        Implement a method that counts the total number of islands in a given matrix.

        final int row = grid.length;
        final int column = grid[0].length;

        boolean[][] visited = new boolean[row][column];

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == 1 &&!visited[i][j]) {
                    store(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void store(int[][] matrix, int row, int col)
    {
        int H = matrix.length;
        int L = matrix[0].length;
        if (row < 0 || col < 0 || row >= H || col >= L || matrix[row][col] != '1')
            return;
        matrix[row][col] = '0';
        store(matrix, row+ 1, col);

        store(matrix, row- 1, col);

        store(matrix, row, col + 1);

        store(matrix, row, col - 1);
    }
}
