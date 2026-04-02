class Solution {
    public int longestConsecutive(int[] nums) {
        int result = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int it : set) {
            if (!set.contains(it - 1)) {
                int count = 1;

                int curr = it;
                while (set.contains(curr + 1)) {
                    count++;
                    curr++;
                }

                result = Math.max(result, count);
            }
        }

        return result;
    }
}
