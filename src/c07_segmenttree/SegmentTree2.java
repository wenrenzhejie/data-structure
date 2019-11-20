package c07_segmenttree;

public class SegmentTree2<E> {
    private E[] data;
    private E[] tree;
    Merger<E> merger;
    public SegmentTree2(E[] array,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[array.length];
        for (int i = 0 ;i < array.length;i++){
            data[i] = array[i];
        }
        tree = (E[]) new Object[4 * array.length];
        buildSegmentTree(0,0,data.length - 1);
    }
    //构建线段树
    private void buildSegmentTree(int treeIndex,int l,int r){
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int treeLeftChildIndex = leftChild(treeIndex);
        int treeRightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l )/2;
        buildSegmentTree(treeLeftChildIndex,l,mid);
        buildSegmentTree(treeRightChildIndex,mid + 1,r);
        tree[treeIndex] = merger.merge(tree[treeLeftChildIndex],tree[treeRightChildIndex]);
    }
    //查询某个区间的值
    public E query(int queryL,int queryR){
        return query(0,0,data.length - 1,queryL,queryR);
    }
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (queryR <= mid){
            return query(leftChildIndex,l,mid,queryL,queryR);
        }else if (queryL >= mid+1){
            return query(rightChildIndex,mid + 1,r,queryL,queryR);
        }else {
            E left = query(leftChildIndex, l, mid, queryL, mid);
            E right = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(left,right);
        }
    }
    //更新某个区间的值
    public void set(int index,E e){
        data[index] = e;
        set(0,0,data.length - 1,index,e);
    }
    private void set(int treeIndex,int l,int r,int index,E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (index <= mid){
            set(leftChildIndex,l,mid,index,e);
        }else {
            set(rightChildIndex,mid+1,r,index,e);
        }
        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }
    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }
    public int getSize(){
        return data.length;
    }
    public E get(int index){
        return data[index];
    }
}
