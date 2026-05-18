class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int currPass = 0;

        for (int[] trip : trips) {
            int numPass = trip[0], start = trip[1], end = trip[2];

            while (!q.isEmpty() && q.peek()[0] <= start) {
                currPass -= q.poll()[1];
            }

            currPass += numPass;

            if (currPass > capacity)
                return false;
            
            q.add(new int[]{end, numPass});
        }

        return true;
    }
}