package algorithm;

/**
 * 欧几里德算法, 计算两个非负整数p和q的最大公约数: 若q是0, 则最大公约数为p,
 *               否则, 将p除以q, 得到余数r, p和q的最大公约数即为q和r的最大公约数.
 * Created by Administrator on 2018/7/4 0004.
 */
public class GetZuiDaGongYueShu {
    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(10340034,34536));
    }

    /**
     * 获取p和q的最大公约数
     * 要求p和q不小于0
     * @param p
     * @param q
     * @return
     */
    public static int getGreatestCommonDivisor(int p, int q){
        if(q <0 || p<0){
            throw new RuntimeException("传入的参数有误");
        }
        if(p==0){
            return q;
        }else if(q ==0){
            return p;
        }

        int r = p%q;
        return getGreatestCommonDivisor(q,r);
    }
}
