package c09_union_find;

public class UnionFind2 implements UF {
    private int[] parent;
    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0 ;i < size;i++){
            parent[i] = i;
        }
    }
    @Override
    public int getSize() {
        return parent.length;
    }
    private int find(int p){
        if (p <0 || p >= parent.length){
            throw new RuntimeException("所给的"+p+"不合法");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot){
            parent[pRoot] = qRoot;
        }
    }
}
