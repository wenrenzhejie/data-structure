package c07_segmenttree;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0 ;i < arr.length;i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length - 1);
    }
    //创建线段树
    private void buildSegmentTree(int treeIndex,int l,int r) {
        if (l == r){
            tree[treeIndex] = data[l];
        }else {
            int mid = l + (r - l ) / 2;
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);
            buildSegmentTree(leftChildIndex,l,mid);
            buildSegmentTree(rightChildIndex,mid + 1,r);
            tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
        }
    }
    //获取某一区间的值
    public E query(int queryL,int queryR){
        return query(0,0,data.length - 1,queryL,queryR);
    }
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if (queryL == l && queryR == r){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (queryR <= mid){
            return query(leftChildIndex,l,mid,queryL,queryR);
        }else if (queryL >= mid + 1){
            return query(rightChildIndex,mid+1,r,queryL,queryR);
        }else {
            E leftResult = query(leftChildIndex, l, mid, queryL, mid);
            E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult,rightResult);
        }
    }
    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new RuntimeException("您给出的索引不合法");
        }
        return data[index];
    }
    //更新指定索引的值
    public void set(int index,E e){
        if (index < 0 || index >= data.length){
            throw new RuntimeException("您所给的索引不合法");
        }
        data[index] = e;
        set(0,0,data.length - 1,index,e);
    }
    private void set(int treeIndex,int l,int r,int index,E e){
        if (l == r){
            tree[treeIndex] = e;
        }else {
            int mid = l + (r - l)/2;
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);
            if (index <= mid){
                set(leftChildIndex,l,mid,index,e);
            }else {
                set(rightChildIndex,mid + 1,r,index,e);
            }
            tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
        }
    }
    //返回区间长度
    public int getSize(){
        return data.length;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }
}
