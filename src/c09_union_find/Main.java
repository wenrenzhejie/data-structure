package c09_union_find;

import java.util.Random;
public class Main {
    public static double test(UF uf,int m){
        int size = uf.getSize();
        long beginTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0;i < m;i++){
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.unionElements(p,q);
        }
        for (int i = 0 ;i < m;i++){
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p,q);
        }
        long endTime = System.nanoTime();
        return (endTime - beginTime)/1000000000.0;
    }
    public static void main(String[] args){
        int size = 10000000;
        int m = 10000000;
     /*   UnionFind1 unionFind1 = new UnionFind1(size);
        System.out.println("unionFind1执行了"+test(unionFind1,m)+"秒");
       UnionFind2 unionFind2 = new UnionFind2(size);
        System.out.println("unionFind2执行了"+test(unionFind2,m)+"秒");*/
         UnionFind3 unionFind3 = new UnionFind3(size);
        System.out.println("unionFind3执行了"+test(unionFind3,m)+"秒");
        UnionFind4 unionFind4 = new UnionFind4(size);
        System.out.println("unionFind4执行了"+test(unionFind4,m)+"秒");
        UnionFind5 unionFind5 = new UnionFind5(size);
        System.out.println("unionFind5执行了"+test(unionFind5,m)+"秒");
    }
}
