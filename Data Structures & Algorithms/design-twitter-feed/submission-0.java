class Twitter {
    private int count;
    private Map<Integer, List<int[]>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
        this.count = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new int[]{count, tweetId});

        if (tweetMap.get(userId).size() > 10) {
            tweetMap.get(userId).remove(0);
        }
        count--;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        for (int followeeId : followMap.get(userId)) {
            if (tweetMap.containsKey(followeeId)) {
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                pq.offer(new int[]{tweet[0], tweet[1], followeeId, index});
            }
        }

        while (!pq.isEmpty() && res.size() < 10) {
            int[] curr = pq.poll();
            int count = curr[0];
            int tweetId = curr[1];
            int followeeId = curr[2];
            int index = curr[3];

            res.add(curr[1]);

            if (index > 0) {
                int[] tweet = tweetMap.get(followeeId).get(index - 1);
                pq.add(new int[]{tweet[0], tweet[1], followeeId, index - 1});
            }
        }
        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId))
            followMap.get(followerId).remove(followeeId);
    }
}
