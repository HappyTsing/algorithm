package DivideConquer;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int max = maxSubArray(nums);
        System.out.println(max);

    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        return dcSolve(nums, 0, len - 1);
    }

    public static int dcSolve(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // 情况一：左侧最大或右侧最大
        int maxLR = Math.max(dcSolve(nums, left, mid), dcSolve(nums, mid + 1, right));

        // 情况二：最大连续子序列横跨左右两个区间
        // 不断向左走
        int tmpSum = 0;
        int leftSum = 0;
        for (int i = mid - 1; i >= left; i--) {
            tmpSum += nums[i];
            if (tmpSum > leftSum) {
                leftSum = tmpSum;
            }
        }
        // 不断向右走
        tmpSum = 0;
        int rightSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            tmpSum += nums[i];
            if (tmpSum > rightSum) {
                rightSum = tmpSum;
            }
        }

        // 横跨左右区间，则必定包含 nums[mid]
        int maxCross = nums[mid] + rightSum + leftSum;

        return Math.max(maxCross, maxLR);
    }

}