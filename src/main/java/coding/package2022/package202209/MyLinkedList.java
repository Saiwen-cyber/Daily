package coding.package2022.package202209;

class MyLinkedList {
    Node head;
    int length;

    public MyLinkedList() {
        head = new Node(-1);
    }

    public int get(int index) {
        if (index > length - 1 || index < 0) {
            return -1;
        }
        Node node = head.next;
        int point = 0;
        while (node != null && point < index) {
            node = node.next;
            point++;
        }
        assert node != null;
        return node.val;
    }

    public void addAtHead(int val) {
        addAtIndex(-1, val);
    }

    public void addAtTail(int val) {
        addAtIndex(length, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        }
        Node node = head;
        Node tmp = new Node(val);
        int point = -1;
        while (point < index - 1) {
            node = node.next;
            point++;
        }
        tmp.next = node.next;
        node.next = tmp;
        length++;
    }

    public void deleteAtIndex(int index) {
        if (index > length - 1 || index < 0) {
            return;
        }
        Node node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        node.next = node.next.next;
        length--;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int sortArray(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high + 1;
    }

    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        int re = l.sortArray(new int[]{5,5,5,5,5},-1);
        System.out.println(re);
        l.addAtHead(7);
        l.addAtHead(2);
        l.addAtHead(1);

        l.addAtIndex(3, 0);
        l.deleteAtIndex(2);

        l.addAtHead(6);
        l.addAtTail(4);
    }
}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */