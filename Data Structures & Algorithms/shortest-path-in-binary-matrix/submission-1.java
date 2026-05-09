class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] != 0 || grid[rows - 1][cols - 1] != 0)
            return -1;

        int[][] dir = {
            {1, 0},
            {0, 1},
            {1, 1},
            {-1, 0},
            {0, -1},
            {-1, -1},
            {-1, 1},
            {1, -1}
        };
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        grid[0][0] = 1;

        int len = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int it = 0; it < size; it++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];

                if (row == rows - 1 && col == cols - 1)
                    return len;

                for (int i = 0; i < 8; i++) {
                    int nRow = row + dir[i][0];
                    int nCol = col + dir[i][1];

                    if ((nRow >= 0 && nRow < rows) &&
                    (nCol >= 0 && nCol < cols) &&
                    grid[nRow][nCol] == 0) {
                        q.add(new int[]{nRow, nCol});
                        grid[nRow][nCol] = 1;
                    }
                }
            }
            len++;
        }
        return -1;
    }
}