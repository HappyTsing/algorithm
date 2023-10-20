package DivideConquer;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = { 8, 4, 5, 7, 1, 3, 6, 2 };
        // 提前定义临时数组，避免重复定义
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {

            // 分：将数组分为两个部分进行 sort
            int mid = (left + right) / 2;
            sort(arr, left, mid, tmp);
            sort(arr, mid + 1, right, tmp);

            // 治：将两个部分合起来，此时 arr 的两个部分已经排序完毕，只需对他们进行合并即可
            merge(arr, left, mid, right, tmp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 合并
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];

        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }

        // 赋值
        t=0;
        while(left <= right){
            arr[left++] = tmp[t++];
        }
    }
}