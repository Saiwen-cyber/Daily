package jdk_practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPQ<E> {
    /**
     * 数组的默认容量
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    /**
     * 数组可以分配的最大长度
     * 一些虚拟机会存储一些头字在数组里
     * 企图分配更大的数组时会导致
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 优先级排序所依据的比较器，为空时根据自然顺序排序。
     */
    private final Comparator<? super E> comparator;
    /**
     * 优先级队列代表一个平衡二叉堆，
     * 下标为n的元素的两个左右孩子下标分别为2*n+1，2*(n+1)
     * 优先级队列根据比较器排序（初始化构造方法时传入），如果比较器为空，则根据自然顺序排序。
     * 当比较器为空的时候，此时堆为最小堆(堆顶元素为最小元素)
     */
    transient Object[] queue;
    /**
     * 数组结构修改的次数（插入，删除等操作执行时，需要更新）
     */
    transient int modCount = 0;
    /**
     * 队列元素的数量
     */
    private int size = 0;

    public TestPQ(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.offer(3);
        pq.offer(4);
        pq.offer(5);
        pq.offer(1);
        pq.offer(8);
        pq.offer(9);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // 溢出
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 插入元素至优先级队列（元素不能为null）
     *
     * @return 添加成功返回true
     * @throws ClassCastException   类型转换异常，当元素类型与队列类型不一致时。
     * @throws NullPointerException 元素为空时抛出
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            //数组满了，扩大数组容量
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            //向上调整，使queue符合”堆性质“
            siftUp(i, e);
        return true;
    }

    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        modCount++;
        //取出堆顶元素
        E result = (E) queue[0];
        //暂时“交换”last element 与 root
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            //向下调整元素x，使queue符合”堆性质“
            siftDown(0, x);
        return result;
    }

    /**
     * 扩大数组容量
     *
     * @param minCapacity 想要调整的最小容量，如果分配
     */
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // 如果需要的容量很小，则扩大至两倍，否则扩大50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // 如果容量大于最大可分配容量，则分配最大
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        //返回新数组
        queue = Arrays.copyOf(queue, newCapacity);
    }

    /**
     * 将元素x放置位置k，将x向上与父节点交换，直到它比父节点大或为root节点（维持“堆性质”）。
     * comparator不为空时，比较大小通过comparator的compareTo来比较
     * 为空时，根据自然顺序比较，比较大小通过x自己的compareTo方法来比较。
     * siftUpUsingComparator 与 siftUpComparable 区别在于比较大小时所用comparaTo方法。
     * （siftDown()同理）
     *
     * @param k 待填充位置
     * @param x 待插入元素
     */
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    /**
     * 通过向上与parent交换，调整堆，使堆合法 （堆为最小堆）
     *
     * @param x x是待调整元素
     * @param k k是x暂时放在的位置。
     */
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            //在堆中，下标为i的节点的父节点的下标是⌊floor((i − 1) ∕ 2)⌋
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            //如果当前元素大于父节点元素，符合堆性质，退出
            if (key.compareTo((E) e) >= 0)
                break;
            //不符合堆性质，与父交换。
            queue[k] = e;
            k = parent;
        }
        //如果k已经到了堆顶了，则不必交换，退出程序。
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    /**
     * 参考siftUp() 方法
     */
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    /**
     * 通过向下与children交换，调整堆，使堆合法。（堆为最小堆）
     *
     * @param x x是待调整元素
     * @param k k是空缺的位置（元素将被删除）。
     */
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        int half = size >>> 1;        // loop while a non-leaf
        //如果k不是叶子节点的位置。
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            //交换父节点与其中一个孩子节点。
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            //比较x和它的孩子，如果符合”堆性质“，结束。
            //此处为最小堆，则如果x小于孩子节点，跳出循环（如果是最大堆，则是大于为合法）。
            if (key.compareTo((E) c) <= 0)
                break;
            //不符合“堆性质”，交换
            queue[k] = c;
            k = child;
        }
        //如果k是叶子节点的位置，直接将last element赋值。
        queue[k] = key;
    }

    @SuppressWarnings("unchecked")
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }
}