package cn.breadnicecat.candycraft.utils;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/25 9:17
 */
@FunctionalInterface
public interface SuBiConsumer<A, B, C> {
	void accept(A a, B b, C c);
}
