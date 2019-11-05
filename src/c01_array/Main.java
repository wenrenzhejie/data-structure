package c01_array;

import javax.naming.NamingEnumeration;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Array<Integer> array = new Array();
        for (int i = 0;i < array.getCapacity();i ++){
            array.addLast(i);
        }
        array.add(1,10);
        array.addFirst(666);
        System.out.println(array.toString());
        array.remove(1);
        System.out.println(array);
        array.removeElement(4);
        System.out.println(array);
        array.removeFirst();
        array.removeLast();
        System.out.println(array);

//        Array<Student> studentArray = new Array<>();
//        studentArray.addLast(new Student("zs","123"));
//        studentArray.addLast(new Student("ls","456"));
//        System.out.println(studentArray);

    }
}
