package algorithm;

import java.util.Random;

/**
 * 加权树, 快速解决连通性问题
 * Created by Administrator on 2018/7/9 0009.
 */
public class WeightQuickUnionUF {
    /**
     * 父链接数组
     */
    private int[] id;
    /**
     * 各个节点所对应的分量的大小.
     */
    private int[] sz;
    /**
     * 连通分量的数量.
     */
    private int count;

    public WeightQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        // 跟随链接找到根节点
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        // 将小树的根节点连接到大树的根节点.
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;

    }

    public static void main(String[] args) {
//        String[] sArr = new String[]{"4,3", "3,8", "6,5", "9,4", "2,1", "8,9", "5,0", "7,2", "6,1", "1,0", "6,7"};
//        int n = sArr.length;

        int n = 128000*4;
        String[] sArr = new String[n];
        String[] sArr2 = new String[n];
        String[] sArr3 = new String[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(random.nextInt(n)+"");
            int q = Integer.parseInt(random.nextInt(n)+"");
            sArr[i] = p+","+q;
            sArr2[i] = p+","+q;
            sArr3[i] = p+","+q;
        }


        WeightQuickUnionUF uf = new WeightQuickUnionUF(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0;i<n;i++) {
            String[] subArr = sArr[i].split(",");
            int p = Integer.parseInt(subArr[0]);
            int q = Integer.parseInt(subArr[1]);
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        System.out.println("use time: "+(System.currentTimeMillis() - startTime));


        long s2 = System.currentTimeMillis();
        UF uf2 = new UF(n);
        for (int i = 0;i<n;i++) {
            String[] subArr = sArr2[i].split(",");
            int p = Integer.parseInt(subArr[0]);
            int q = Integer.parseInt(subArr[1]);
            if (uf2.connected(p, q)) {
                continue;
            }
            uf2.union(p, q);
        }
        System.out.println("use time: "+(System.currentTimeMillis() - s2));


        UF uf3 = new UF(n);
        long s3 = System.currentTimeMillis();
        for (int i = 0;i<n;i++) {
            String[] subArr = sArr3[i].split(",");
            int p = Integer.parseInt(subArr[0]);
            int q = Integer.parseInt(subArr[1]);
            if (uf3.connected(p, q)) {
                continue;
            }
            uf3.union2(p, q);
        }
        System.out.println("use time: "+(System.currentTimeMillis() - s3));

    }

}
