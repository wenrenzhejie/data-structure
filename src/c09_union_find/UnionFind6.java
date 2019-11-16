package c09_union_find;

//优化为高度为2的并查集树
public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank;
    public UnionFind6(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0 ;i < parent.length;i++){
            parent[i] = i;
            rank[i] = 1;
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
        if (p == parent[p]){
            return p;
        }else {
            parent[p] = find(parent[p]);
            return parent[p];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }
}
