package coding.package2022.package202205;

/**
 * @author fang
 */
public class Main20220530 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int[] re = new int[m + n];
        for (int k = 0; k < m + n; k++) {
            if (i >= m) {
                re[k] = nums2[j];
                j++;
                continue;
            }
            if (j >= n) {
                re[k] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] > nums2[j]) {
                re[k] = nums2[j];
                j++;
            } else {
                re[k] = nums1[i];
                i++;
            }
        }
        for (int index = 0; index < m + n; index++) {
            nums1[index] = re[index];
        }
    }

    public static void main(String[] args) {

        Main20220530 main = new Main20220530();
        int[] nums = new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11};
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        main.merge(nums1, m, nums2, n);
    }
}