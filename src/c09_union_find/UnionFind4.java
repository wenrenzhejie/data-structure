package c09_union_find;

//基于rank的优化
public class UnionFind4 implements UF {
    private int[] parent;
    private int[] rank;
    public UnionFind4(int size){
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
