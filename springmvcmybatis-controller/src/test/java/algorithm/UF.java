package algorithm;

/**
 * 连通性分析算法
 * Created by Administrator on 2018/7/9 0009.
 */
public class UF {
    private int[] id;
    private int count;
    public UF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int n){
        return id[n];
    }

    public int find2(int n){
        while (n != id[n]){
            n = id[n];
        }
        return n;
    }

    /**
     * 算法一, 如果p和q是连通的,则把数组下标为p和数组下标为q的位置的值都改成p位置上的值.
     * 注意, 默认初始化数组的时候,arr[i] = i; 当进行n次连接后, 值会改变. 其原理就是保证了
     * 如果有n个节点是连通的, 在遍历的过程中, 会把这对应n个点的值都赋值成相同的. 这样在判断
     * 是否连通的时候,用时就是一个常量值.
     * @param p
     * @param q
     */
    public void union(int p, int q){
        // 将p和q合并到相同的分量中.
        int pID = find(p);
        int qID = find(q);
        // 如果p和q已经在相同的分量之中则不用做操作
        if(pID == qID){
            return;
        }
        // 将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pID){
                id[i] = qID;
            }
        }
        count --;
    }

    /**
     * 算法二.
     * 比算法一好一点, 是相当于所相关连节点建立链接关系,这样就不用每次都把整个数组遍历一遍了.
     * 当然数据是随机的, 最差的情况和算法一差不多.
     * @param p
     * @param q
     */
    public void union2(int p, int q){
        //将p和q的根节点统一
        int pRoot = find2(p);
        int qRoot = find2(q);
        if(pRoot == qRoot){
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        String[] sArr = new String[]{"4,3", "3,8", "6,5", "9,4", "2,1","8,9", "5,0", "7,2", "6,1","1,0","6,7"};
        int n = sArr.length;
        UF uf = new UF(n);
        for (String s:sArr) {
            String[] subArr = s.split(",");
            int p = Integer.parseInt(subArr[0]);
            int q = Integer.parseInt(subArr[1]);
            if(uf.connected(p,q)){
                continue;
            }
            uf.union2(p,q);
            System.out.println("connect: "+p+", "+q);

        }

    }


}
