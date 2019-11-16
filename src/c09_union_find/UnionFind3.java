package c09_union_find;

import com.sun.glass.ui.Size;
//基于size的优化
public class UnionFind3 implements UF {
    private int[] parent;
    private int[] nums;
    public UnionFind3(int size){
        parent = new int[size];
        nums = new int[size];
        for (int i = 0 ;i < parent.length;i++){
            parent[i] = i;
            nums[i] = 1;
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
        int szPRoot = nums[pRoot];
        int szQRoot = nums[qRoot];
        if (szPRoot > szQRoot){
            parent[qRoot] = pRoot;
            nums[pRoot] = szPRoot + szQRoot;
        }else {
            parent[pRoot] = qRoot;
            nums[qRoot] = szQRoot + szPRoot;
        }

    }
}
