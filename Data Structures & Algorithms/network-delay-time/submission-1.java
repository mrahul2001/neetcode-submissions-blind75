class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair<Integer, Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int wt = times[i][2];

            adjList.get(u).add(new Pair(v, wt));
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.add(new int[] {k, 0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int steps = curr[1];

            for (Pair<Integer, Integer> it : adjList.get(node)) {
                int adjNode = it.getKey();
                int adjSteps = it.getValue();
                
                if (adjSteps + steps < distances[adjNode]) {
                    distances[adjNode] = adjSteps + steps;
                    q.add(new int[]{adjNode, distances[adjNode]});
                }
            }
        }

        int maxTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, distances[i]);
        }

        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
}
