package temp;

/**
 * Created by Administrator on 2018/8/9 0009.
 */
public class ShareLong {
    /**
     * cpu缓存行一般为64字节, Sl对象通过添加属性刚好一个对象大小为64字节, 这样就占一个完整的
     * 缓存行, 在多线程处理的时候就不会因为, 为了实现volatile的多线程间可见性, 而出现写竞争,
     * 导致性能下降. 如果去掉p123456,这几个属性, 会发现用时长一些.
     *
     * 如果去掉volatile关键字, 则不再保证多线程间的可见性, 则更快
     *
     *
     *
     */
  static class Sl{
//       long v;
      volatile long v;
//      long p1, p2, p3, p4, p5, p6;
  }

  static class LightThread extends Thread{
      Sl[] shares;
      int index;

      LightThread(Sl[] shares, int index){
          this.shares = shares;
          this.index = index;
      }

      public void run(){
          for (int i = 0; i <100000000 ; i++) {
              shares[index].v++;
          }
      }



  }


    public static class FalseSharingTest{
        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 10; i++) {
                Benchmark();
            }


        }

        public static void Benchmark() throws InterruptedException {
            int size = Runtime.getRuntime().availableProcessors();
            Sl[] shares = new Sl[size];
            for (int i = 0; i < size; i++) {
                shares[i] = new Sl();
            }

            Thread[] threads = new Thread[size];
            for (int i =0; i<size;i++){
                threads[i] = new LightThread(shares, i);
            }
            for (Thread t:threads
                 ) {
                t.start();
            }

            long startTime = System.currentTimeMillis();
            for (Thread t:threads) {
                t.join();
            }

            long end = System.currentTimeMillis();
            System.out.println("total use time: "+ (end - startTime));
        }


    }




}
