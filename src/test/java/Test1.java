import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhanglei53 on 2016/8/24.
 */
public class Test1 {
    @Test
    public void test1() {
        HttpSession session;
        List<String> list = new LinkedList<String>();
        list = new ArrayList<String>();
        String s = "dawda";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        map = new ConcurrentHashMap<String, Object>();
        map = Collections.synchronizedMap(new HashMap<String, Object>());

        Set<String> set = new HashSet<String>();
        map = new LinkedHashMap<String, Object>();
        map = new TreeMap<String, Object>();

        set = new TreeSet<String>();
    }

    @Test
    public void test2() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return 0;
            }
        });
    }

    @Test
    public void sortTest() {
        int[] arr = {5, 3, 2, 7, 1, 6, 4};
        int[] sorted = new int[arr.length];
        System.out.println("排序前：" + Arrays.toString(arr));
        // insertSort(arr);
        // chooseSort(arr);
        // mergeSort(arr, 0, arr.length - 1, sorted);
        // poSort(arr);
        // heapSort(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    // 快速排序
    private void quickSort(int[] arr, int low, int high) {
        if (arr == null || low >= high) {
            return;
        }

        // 找到标志位
        int loc = partition(arr, low, high);
        quickSort(arr, low, loc - 1);
        quickSort(arr, loc + 1, high);
    }

    // 分成两部分
    private int partition(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] > temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] < temp) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = temp;
        return low;
    }

    // 堆排序
    private void heapSort(int[] arr) {
        if (arr == null) {
            return;
        }
        buildHeap(arr);
        int heapSize = arr.length;

        for (int i = 0; i < arr.length; i++) {
            int temp = arr[0];
            arr[0] = arr[heapSize - 1];
            arr[heapSize - 1] = temp;

            heapSize--;
            adjustHeap(arr, 0, heapSize);
        }
    }

    // 构造堆
    private void buildHeap(int[] arr) {
        int heapSize = arr.length;
        // 最后的叶子节点的父节点
        int nonleaf = heapSize / 2 - 1;
        for (int i = nonleaf; i >= 0; i--) {
            adjustHeap(arr, i, heapSize);
        }
    }

    // 保持堆得性质
    private void adjustHeap(int[] heap, int root, int heapSize) {
        int smallest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        if (left < heapSize) {
            if (heap[smallest] < heap[left]) {
                smallest = left;
            } else {
                smallest = root;
            }
        }
        if (right < heapSize) {
            if (heap[smallest] < heap[right]) {
                smallest = right;
            }
        }
        if (smallest != root) {
            int temp = heap[root];
            heap[root] = heap[smallest];
            heap[smallest] = temp;
            adjustHeap(heap, smallest, heapSize);
        }
    }

    // 冒泡排序
    private void poSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    // 归并排序
    private void mergeSort(int[] arr, int start, int end, int[] sorted) {
        if (arr == null || sorted == null || arr.length > sorted.length) {
            return;
        }
        if (start < end) {
            int mid = (start + end) / 2;
            // 拆分左边
            mergeSort(arr, start, mid, sorted);
            // 拆分右边
            mergeSort(arr, mid + 1, end, sorted);
            // 合并
            merge(arr, start, mid, end, sorted);
        }
    }

    // 合并两数组
    private void merge(int[] arr, int start, int mid, int end, int[] sorted) {
        int i = start, j = mid + 1, index = start;
        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                sorted[index++] = arr[i++];
            }
            if (arr[i] > arr[j]) {
                sorted[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            sorted[index++] = arr[i++];
        }
        while (j <= end) {
            sorted[index++] = arr[j++];
        }
        for (i = start; i <= end; i++) {
            arr[i] = sorted[i];
        }
    }

    // 选择排序
    private void chooseSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    // 插入排序
    private void insertSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}