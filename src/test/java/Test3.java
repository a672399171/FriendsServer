import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test3 {
    @Test
    public void test() {
        // ysf(3, 5);
        int[] gifts = {1, 2, 3, 2, 2};
        // System.out.println(gift(gifts, gifts.length));

        // System.out.println(Arrays.toString(getGray(3)));

        // System.out.println(maxLen("google", "daogel"));
        System.out.println(rand7());
    }

    // 约瑟夫环问题
    private void ysf(int m, int n) {
        if (m <= 0 || n <= 0) {
            return;
        }
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr, false);
        for (int i = 1, j = 1, k = 0; k < n; ) {
            j = j % n + 1;
            if (!arr[j] && ++i % m == 0) {
                System.out.print(j);
                k++;
                arr[j] = true;
            }
        }
    }

    // 微信红包
    private int gift(int[] gifts, int n) {
        if (gifts == null) {
            return -1;
        }
        Arrays.sort(gifts);
        int mid = gifts.length / 2;
        int num = 0;
        for (int i = 0; i < gifts.length; i++) {
            if (gifts[i] == gifts[mid]) {
                num++;
            }
        }
        if (num > n / 2) {
            return gifts[mid];
        } else {
            return 0;
        }
    }

    // 格雷码
    private String[] getGray(int n) {
        if (n <= 0) {
            return null;
        } else if (n == 1) {
            return new String[]{"0", "1"};
        } else {
            String[] result = new String[1 << n];
            String[] arr = getGray(n - 1);
            if (arr != null) {
                for (int i = 0; i < arr.length; i++) {
                    result[i] = "0" + arr[i];
                }
                int index = arr.length;
                for (int i = arr.length - 1; i >= 0; i--) {
                    result[index++] = "1" + arr[i];
                }
            }

            return result;
        }
    }

    // 删除字符构成回文串(求最长子串)
    // 求回文，只需求原字符串和其逆串的最长子串
    private int maxLen(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 <= 0 || len2 <= 0) {
            return 0;
        }
        int[][] maxLen = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            maxLen[i][0] = 0;
        }
        for (int i = 0; i < len2; i++) {
            maxLen[0][i] = 0;
        }
        int max = -1;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    maxLen[i][j] = maxLen[i - 1][j - 1] + 1;
                } else {
                    // 如果是公共子序列
                    // maxLen[i][j] = Math.max(maxLen[i - 1][j], maxLen[i][j - 1]);
                    // 如果是公共子串
                    maxLen[i][j] = 0;
                }
                if (maxLen[i][j] > max) {
                    max = maxLen[i][j];
                }
            }
        }

        return max;
    }

    // 用rand7()产生rand10()
    private int rand10() {
        int n = 0;
        do {
            n = 7 * (rand7() - 1) + rand7();
        } while (n > 40);

        return n % 10 + 1;
    }

    // 产生1-7随机数（概率相等）
    private int rand7() {
        int n = (int) (Math.random() * 8);
        while (n < 1) {
            n = (int) (Math.random() * 8);
        }
        return n;
    }
}