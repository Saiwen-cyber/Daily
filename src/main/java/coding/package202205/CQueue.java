package coding.package202205;

import java.util.Stack;

class CQueue {

    Stack<Integer> stackA;
    Stack<Integer> stackB;
    public CQueue() {
         stackA = new  Stack<Integer>();
         stackB = new  Stack<Integer>();
    }

    public void appendTail(int value) {
            stackA.push(value);
                    System.out.println(stackA.size());
    }
    
    public int deleteHead() {
        int result = -1;
        int len = stackA.size();
        if(!stackA.isEmpty()){
              System.out.println(stackA.size());
            for(int i = 0; i < len - 1; i++){
                int tmp = stackA.pop();
                    stackB.push(tmp);
                    System.out.print(tmp + " ");
            }
            result = stackA.pop();
            len = stackB.size();
            for(int i = 0; i < len ; i++){
                    stackA.push(stackB.pop());
            }
        }
        System.out.println();
        return result;
        }

    public static void main(String[] args) {
          CQueue obj = new CQueue();
          obj.appendTail(1);
        obj.appendTail(8);
        obj.appendTail(20);
        obj.appendTail(1);
        obj.appendTail(11);
        obj.appendTail(2);


        for (int i = 0; i < 4; i++) {
            int param_2 = obj.deleteHead();
            System.out.println(param_2);
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */