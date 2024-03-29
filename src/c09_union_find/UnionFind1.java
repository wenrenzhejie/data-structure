package c09_union_find;

public class UnionFind1 implements UF {
    private int[] id;
    public UnionFind1(int size){
        id = new int[size];
        for (int i = 0 ;i < id.length;i ++){
            id[i] = i;
        }
    }
    @Override
    public int getSize() {
        return id.length;
    }
    private int find(int p){
        if (p < 0 || p >= id.length){
            throw new RuntimeException("所传入的"+p+"不合法");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId){
            return;
        }
        for (int i = 0;i < id.length;i ++){
            if (id[i] == qId){
                id[i] = pId;
            }
        }
    }
}
