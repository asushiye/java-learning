package system.utils;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : zhenyun.su
 * @since  : 2019/1/8
 * @Commit : 本系统要使用的公用工具类
 */

public abstract class GlobalUtils {

    private static final String GLOBAL_PERCENT = "%";

    public static String emptyString(@Nullable String str) {
        return str == null ? "": str.trim();
    }

    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

    public static String globalLine() {
        return "------------------------------------";
    }

    public static String percent(@Nullable String str) {
        return GLOBAL_PERCENT + emptyString(str) + GLOBAL_PERCENT;
    }

    /**@comment : 根据字符串和分隔符，返回String的List对象
     * 例如：asStringList("dog@cat@bear", "@")
     */
    public static List<String> asStringList(String values, String regex){
        String[]  arrayString = values.split(regex);
        return Arrays.asList(arrayString);
    }

    /**@comment : 根据字符串和分隔符，返回Integer的List对象
     * 例如：asIntegerList("1,2,3", ",")
     */
    public static List<Integer> asIntegerList(String values, String regex){
        String[]  arrayString = values.split(regex);
        List<Integer> lists = new ArrayList<>();
        for (String item : arrayString) {
            lists.add(Integer.valueOf(item.trim()));
        }
        return lists;
    }
}