package cn.breadnicecat.candycraft.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 15:32
 * 非mc特有
 */
public class CommonUtils {
    /**
     * 从注册名中获取名称
     *
     * @return 将name中的下划线替换为空格，并且每个空格后第一个字母大写
     * <p>
     * 例如{@code 输入this_is_a_example 返回This Is A Example}
     */
    public static String getEnLangByName(String name) {
        StringBuilder sb = new StringBuilder();
        String[] s = name.split("_");
        for (String s1 : s) {
            sb.append(s1.substring(0, 1).toUpperCase()).append(s1.substring(1)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 填充指定长度字符
     */
    public static String fill(String raw, int len, char fill) {
        StringBuilder builder = new StringBuilder(raw);
        builder.append(String.valueOf(fill).repeat(Math.max(0, len)));
        return builder.toString();
    }

    /**
     * 填充字符直到达到指定长度
     */
    public static String fillUntil(String raw, int toLength, char fill) {
        if (raw.length() > toLength) {
            throw new IllegalArgumentException("raw.length()>toLength");
        } else if (raw.length() == toLength) {
            return raw;
        } else {
            return fill(raw, toLength - raw.length(), fill);
        }

    }

    /**
     * @return a∩b
     */
    public static <T> HashSet<T> setIntersection(Set<T> a, Set<T> b) {
        HashSet<T> res = new HashSet<>();
        Set<T> it, ited;
        if (a.size() <= b.size()) {//挑少的遍历
            it = a;
            ited = b;
        } else {
            it = b;
            ited = a;
        }
        it.forEach((ele) -> {
            if (ited.contains(ele)) res.add(ele);
        });
        return res;
    }

    /**
     * @param timesOfOnce (概率上)大约多少次才返回一次true
     */
    public static boolean probability(Random random, int timesOfOnce) {
        return random.nextInt(timesOfOnce) == 0;
    }
}
