class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        List<Integer> resArr = new ArrayList<>();

        int left = 0, right = n - 1, top = 0, bottom = m - 1;

        while (left <= right && top <= bottom) {
            
            for (int i = left; i <= right; i++)
                resArr.add(matrix[top][i]);
            top++;

            for (int j = top; j <= bottom; j++)
                resArr.add(matrix[j][right]);
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    resArr.add(matrix[bottom][i]);
                bottom--;
            }

            if (left <= right) {
                for (int j = bottom; j >= top; j--)
                    resArr.add(matrix[j][left]);
                left++;
            }
        }

        return resArr;
    }
}
