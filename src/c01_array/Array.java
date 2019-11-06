package c01_array;

public class Array<E> {
    private E[] data;
    //数组中元素的个数
    private int size;

    public Array(int capacity){
//        java中不支持直接new泛型数组
//        data = new E[capacity];
        data = (E[]) new Object[capacity];
    }
    public Array(){
        //默认声明一个容量为10的数组
        this(10);
    }
    //数组容量
    public int getCapacity(){
        return data.length;
    }
    //数组中元素的个数
    public int getSize(){
        return this.size;
    }
    //数组是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }

    //向末尾增加一个元素
    public void addLast(E o){
       add(this.size,o);
    }
    //向开头添加一个元素
    public void addFirst(E o){
        add(0,o);
    }
    //向指定索引处添加一个元素
    public void add(int index,E o){
        if (index <0 || index >this.size){
             throw new IllegalArgumentException("您给出的索引不合法");
        }
        //数组满，对数组进行扩容
        if (this.size == data.length){
            resize(this.size * 2 +1);
        }
        for (int i = this.size - 1;i >= index;i --){
            data[i+1] = data[i];
        }
        data[index] = o;
        this.size ++;
    }
    //根据索引获取值
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        return this.data[index];
    }

    //获取最后一个值
    public E getLast(){
        return this.get(this.size -1);
    }
    public E getFirst(){
        return this.get(0);
    }
    //根据索引修改值
    public void set(int index,E o){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        this.data[index] = o;
    }
    //判断一个元素是否在数组中
    public boolean contains(E o){
        for (int i = 0;i < this.size;i++){
            if (this.data[i].equals(o)){
                return true;
            }
        }
        return false;
    }
    //查找某一元素的索引
    public int find(E o){
        for (int i = 0;i < this.size;i++){
            if (this.data[i].equals(o)){
                return i;
            }
        }
        return -1;
    }
    //根据索引删除元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        E old = this.data[index];
        for (int i = index +1 ;i < this.size;i++){
            this.data[i -1] = this.data[i];
        }
        this.size --;
        data[this.size] = null;
        //缩容 lazy方式
        if (this.size == this.data.length / 4 && data.length / 2 != 0){
            resize(this.data.length/2);
        }
        return old;
    }

    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(this.size - 1);
    }

    public void removeElement(E o){
        int i = find(o);
        if (i != -1){
            remove(i);
        }
    }
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0;i < this.size;i++){
            newData[i] = this.data[i];
        }
        this.data = newData;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("数组中元素个数为%d,数组容量为%d \n",this.size,this.data.length));
        stringBuilder.append("[");
        for (int i = 0;i < this.size;i++){
            if (i == this.size -1){
                stringBuilder.append(this.data[i]+"]");
            }else {
                stringBuilder.append(this.data[i]+",");
            }
        }
        return stringBuilder.toString();
    }
}
