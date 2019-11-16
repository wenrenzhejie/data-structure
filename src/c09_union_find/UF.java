package c09_union_find;

public interface UF {
    public int getSize();
    public boolean isConnected(int p,int q);
    public void unionElements(int p,int q);
}
