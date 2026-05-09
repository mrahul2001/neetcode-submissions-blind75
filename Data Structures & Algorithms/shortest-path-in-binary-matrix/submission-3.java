class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1)
            return -1;
        
        if (rows == 1)
            return 1;

        int[][] distances = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        distances[0][0] = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0, 0});

        int[][] nextValues = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},   
            { 1, -1}, { 1, 0}, { 1, 1} 
        };

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int dist = curr[0];
            int currRow = curr[1];
            int currCol = curr[2];

            if (currRow == rows - 1 && currCol == cols - 1)
                return dist;

            for (int i = 0; i < 8; i++) {
                int newRow = currRow + nextValues[i][0];
                int newCol = currCol + nextValues[i][1];

                if ((newRow >= 0 && newRow < rows) && 
                    (newCol >= 0 && newCol < cols) &&
                    grid[newRow][newCol] == 0 &&
                    dist + 1 < distances[newRow][newCol]) {
                    distances[newRow][newCol] = dist + 1;
                    
                    q.add(new int[]{dist + 1, newRow, newCol});
                }
            }
        }
        return -1;
    }
}