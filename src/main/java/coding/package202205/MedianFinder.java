package coding.package202205;

import java.util.PriorityQueue;

/**
 * @author fang
 */
public class MedianFinder {
    /** initialize your data structure here. */
    PriorityQueue<Integer> a;
    PriorityQueue<Integer> b;
    public MedianFinder() {
        a = new PriorityQueue<>(); //排序从小到大
        b = new PriorityQueue<>((x, y) -> (y - x));//排序从大到小
    }

    public void addNum(int num) {
        if (a.size() != b.size()) {
            //这样倒 是为了保证元素顺序
            a.add(num);
            b.add(a.poll());
        }else {
            b.add(num);
            a.add(b.poll());
        }
    }

    public double findMedian() {
        return a.size() != b.size() ? a.peek() : (a.peek() +b.peek()) / 2;
    }

    public double myPow(double x, int n) {
        while(n != 0) {
            x = x*x;
            n--;
        }
        return x;
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    public static void main(String[] args) {
        int []arr = new int[]{12,121};
        int []parr = new int[]{2,3,5,8,3,4,9};
        new MedianFinder().myPow(2.000000, 10);
        //        new QuickSort().quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

}