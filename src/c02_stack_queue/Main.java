package c02_stack_queue;

import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args){
      /*  ArrayStack<Integer> integerArrayStack = new ArrayStack<>();
        integerArrayStack.push(1);
        integerArrayStack.push(2);
        integerArrayStack.push(3);
        System.out.println(integerArrayStack);
        System.out.println(integerArrayStack.getSize());
        System.out.println(integerArrayStack.isEmpty());
        System.out.println(integerArrayStack.peek());
        integerArrayStack.pop();
        System.out.println(integerArrayStack);*/

        /*ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>();
        integerArrayQueue.enqueue(1);
        integerArrayQueue.enqueue(2);
        integerArrayQueue.enqueue(3);
        System.out.println(integerArrayQueue);
        integerArrayQueue.dequeue();
        System.out.println(integerArrayQueue);
        Integer front = integerArrayQueue.getFront();
        System.out.println(front);*/

        LoopQueue<Integer> integerLoopQueue = new LoopQueue<>();
      /*  for (int i = 0;i < 10;i++){
            integerLoopQueue.enqueue(i);
        }
        System.out.println(integerLoopQueue);*/
    /*  integerLoopQueue.enqueue(1);
      integerLoopQueue.enqueue(2);
      integerLoopQueue.enqueue(3);
      integerLoopQueue.enqueue(4);
      integerLoopQueue.enqueue(5);
      integerLoopQueue.enqueue(6);
      integerLoopQueue.enqueue(7);
      integerLoopQueue.enqueue(8);
      integerLoopQueue.enqueue(9);
      integerLoopQueue.enqueue(10);
      integerLoopQueue.enqueue(11);
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
      integerLoopQueue.dequeue();
        System.out.println(integerLoopQueue);*/
    }
}
