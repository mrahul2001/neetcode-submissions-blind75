class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;

        int left = 0, right = 0;
        while (right < n) {
            nums[left] = nums[right];

            while (right < n && nums[right] == nums[left]) {
                right++;
            }
            left++;
        }

        return left;
    }
}