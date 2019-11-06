package c03_linkedlist;

public class Main {
    public static void main(String[] args){
       /* LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0;i < 5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        linkedList.addLast(777);
        boolean contains = linkedList.contains(666);
//        System.out.println(contains);
        Integer integer = linkedList.get(2);
//        System.out.println(integer);
        linkedList.set(2,888);
        System.out.println(linkedList);
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);*/

       /* LinkedListStack<Integer> integerLinkedListStack = new LinkedListStack<>();
        integerLinkedListStack.push(1);
        integerLinkedListStack.push(2);
        integerLinkedListStack.push(3);
        System.out.println(integerLinkedListStack);
        integerLinkedListStack.pop();
        System.out.println(integerLinkedListStack);
        Integer peek = integerLinkedListStack.peek();
        System.out.println(peek);*/

        LinkedListQueue<Integer> integerLinkedListQueue = new LinkedListQueue<>();
        integerLinkedListQueue.enqueue(1);
        integerLinkedListQueue.enqueue(2);
        integerLinkedListQueue.enqueue(3);
        Integer dequeue = integerLinkedListQueue.dequeue();
        System.out.println(dequeue);
        System.out.println(integerLinkedListQueue);
    }
}
