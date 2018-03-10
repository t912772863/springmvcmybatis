package temp;


import java.util.HashMap;

/**
 * 动态规划
 */
public class Demo003 {

    public static void main(String[] args) throws Exception{
        JavaMailUtil javaMail = JavaMailUtil.getInstance();
        javaMail.sendMail("TianXiong",
                "15016709568@139.com","我的139","测试网络图片的邮件","这是一张图片<br/><img src='//www.baidu.com/img/bd_logo1.png'/>",
                "C:\\Users\\Administrator\\Desktop\\临时文件夹\\虫虫大作战-视频.MP4");
        /*
        有一座高度是10级的台阶, 从下往上走, 每跨一步只能向上1级或者两级, 要求算出 一共有多少种走法.

        通过动态规划的思想,把问题归结为: f1 = 1; f2 = 2 ; fn = f(n-1) + f(n-2); n>=3;
        求f10 的值
         */
        System.out.println(System.currentTimeMillis());
        System.out.println("方法一: 共有"+method1(40)+"种走法");
        System.out.println(System.currentTimeMillis());


        System.out.println(System.currentTimeMillis());
        System.out.println("方法二: 共有"+method2(40, new HashMap<Integer, Integer>())+"种走法");
        System.out.println(System.currentTimeMillis());


        System.out.println(System.currentTimeMillis());
        System.out.println("方法三: 共有"+method3(40)+"种走法");
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 方法一: 递归求解
     * @param n
     * @return
     */
    public static int method1(int n){
        if(n<1){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return method1(n-1) + method1(n-2);
    }

    /**
     * 备忘录算法, 相对于方法一, 把一些已经算过的值存下来, 防止重复运算
     * @param n
     * @param map
     * @return
     */
    public static int method2(int n, HashMap<Integer, Integer> map){
        if(n<1){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }else{
            int value =  method1(n-1) + method1(n-2);
            map.put(n,value);
            return value;
        }
    }

    /**
     * 动态规划求解
     * @param n
     * @return
     */
    public static int method3(int n){
        if(n<1){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for(int i = 3;i<=n;i++){
            temp = a+b;
            a = b;
            b = temp;
        }
        return temp;
    }




}
