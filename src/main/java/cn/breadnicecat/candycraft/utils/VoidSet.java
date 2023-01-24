package cn.breadnicecat.candycraft.utils;

import java.util.Comparator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.UnaryOperator;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 12:13
 */
public class VoidSet extends VoidList implements Set<Object> {
	public VoidSet() {
	}
	
	public <T> Set<T> castSet() {
		return (Set<T>) cast();
	}
	
	@Override
	public void replaceAll(UnaryOperator<Object> operator) {
	}
	
	@Override
	public void sort(Comparator<? super Object> c) {
	}
	
	@Override
	public Spliterator<Object> spliterator() {
		return Spliterators.emptySpliterator();
	}
}
