class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (int task : tasks)
            count[task - 'A']++;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int cnt : count) {
            if (cnt > 0)
                maxHeap.add(cnt);
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;

            if (!maxHeap.isEmpty()) {
                int cnt = maxHeap.poll() - 1;
                if (cnt > 0)
                    q.add(new int[]{cnt, time + n});
            } else {
               time = q.peek()[1];
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.add(q.peek()[0]);
                q.poll();
            }
        }
        return time;
    }
}
