package cn.breadnicecat.codeovencore.util;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 15:32
 */
public class CommonUtils {
	/**
	 * 从注册名中获取名称
	 *
	 * @return 将name中的下划线替换为空格，并且每个空格后第一个字母大写
	 */
	public static String getEnLangByName(String name) {
		StringBuilder sb = new StringBuilder();
		String[] s = name.split("_");
		for (String s1 : s) {
			sb.append(s1.substring(0, 1).toUpperCase()).append(s1.substring(1)).append(" ");
		}
		return sb.substring(0, sb.length() - 1);
	}
}
