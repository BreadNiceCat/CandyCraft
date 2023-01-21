package cn.breadnicecat.candycraft.utils;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/19 17:31
 */
public record Pair<F, S>(F first, S second) {
	public static <F, S> Pair<F, S> of(F first, S second) {
		return new Pair<>(first, second);
	}
}
