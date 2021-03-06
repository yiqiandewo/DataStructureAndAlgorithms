package com.yiqiandewo.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * 斐波那契(黄金分割法)原理:
 *    斐波那契查找原理与前两种相似，仅仅改变了中间结点（mid）的位置，mid不再是中间或插值得到，
 *    而是位于黄金分割点附近，即mid=low+F(k-1)-1 （F代表斐波那契数列）
 *
 * 对F(k-1)-1的理解：
 *    由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 
 *    该式说明：只要顺序表的长度为F[k]-1，则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两段，从而中间位置为mid=low+F(k-1)-1
 *
 * 类似的，每一子段也可以用相同的方式分割
 * 但顺序表长度n不一定刚好等于F[k]-1，所以需要将原来的顺序表长度n增加至F[k]-1。
 * 这里的k值只要能使得F[k]-1恰好大于或等于n即可，顺序表长度增加后，新增的位置（从n+1到F[k]-1位置），都赋为n位置的值即可。
 *
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 12, 15, 31, 42, 45, 56, 876, 1234};

        int index = fibonacciSearch(arr, 1234);

        System.out.println(index);
    }

    public static int fibonacciSearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length-1;
        if (arr[low] > value || arr[high] < value) {
            return -1;
        }
        int[] fi = getFibonacci();

        int k = 0;
        while (high > fi[k] -1) {
            k++;
        }

        int[] temp = Arrays.copyOf(arr, fi[k]);

        //长度增加后，新增的位置（从n+1到F[k]-1位置），都赋为n位置的值即可。
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            //因为分为两段 一段为f[k-1] 另一段位f[k-2] //所以在左边要k-1  右边k-2
            int mid = low+fi[k-1]-1;
            if (temp[mid] > value) {
                high = mid-1;
                k--;
            } else if (temp[mid] < value) {
                low = mid+1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public static int[] getFibonacci() {
        int[] fi = new int[25];
        fi[0] = 1;
        fi[1] = 1;

        for (int i = 2; i < 25; i++) {
            fi[i] = fi[i-1] + fi[i-2];
        }

        return fi;
    }
}
