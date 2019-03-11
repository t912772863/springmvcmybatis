package temp;

/**
 * Created by tianxiong on 2019/3/8.
 */
public class Demo004 {
    private static String skuPattern = "^(?<qz>%s)?(?<zxqz>%s)?((?<yksSku>%s)(?<joomHz>%s)?((\\s+)?\\*(\\s+)?(?<yksSkuCount>\\d+))?)((?<zxhz>%s))?(?<hz>%s)?$";

    public static void main(String[] args) {
        String s = String.format(skuPattern, "[A-Za-z]*[~!@#$%&*<>?]*",
                "zxqz",
                "[A-Za-z]+\\d+",
                "(([~!@#$%^&*<>?]+)|(#[\\s\\S]+)){0,2}",
                "zxhz",
                "[^\\dA-Za-z].*");
        System.out.println(skuPattern);
        System.out.println(s);
    }
}
