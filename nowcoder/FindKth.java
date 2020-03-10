import javafx.scene.transform.Scale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/6 11:51
 * @type 华为面经
 * @question 寻找第k大的数
 */
//快排中partition思路
public class FindKth {
    void printNums(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    int partition(int[] array, int start, int end) { // 升序
        int low = start, high = end - 1;
        int pivot = array[low];
        while (low < high) {
            while (high > low && array[high] > pivot) high--;
            array[low] = array[high];
            while (high > low && array[low] < pivot) low++;
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }
    int findKth(int[] array, int start, int end, int k) {  // [start, end)
        if (k > end - start)
            return -1; // k大于数组长度
        int pos = partition(array, start, end); // 在实际数组中的位置
        int nth = end - pos;
        if(nth > k){
            return findKth(array, pos + 1, end, k);
        }
        else if(nth < k){
            return findKth(array, start, pos, k - nth);
        }
        else
            return array[pos];
    }

    public static void main(String[] args) {
        FindKth findKth = new FindKth();


        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int k = sc.nextInt();
            int[] nums = {5, 2, 66, 23, 11, 1, 4, 55};
            System.out.println(findKth.findKth(nums, 0, nums.length, k));
        }



    }

}
