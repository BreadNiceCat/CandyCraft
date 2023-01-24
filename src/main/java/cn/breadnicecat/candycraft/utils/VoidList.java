package cn.breadnicecat.candycraft.utils;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 11:26
 */
public class VoidList extends AbstractList<Object> {
	public VoidList() {
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> cast() {
		return (List<T>) this;
	}
	
	public <T> List<T> castList() {
		return (List<T>) cast();
	}
	
	@Override
	public Object get(int index) {
		return null;
	}
	
	@Override
	public int size() {
		return 0;
	}
	
	@Override
	public Object remove(int index) {
		return null;
	}
	
	@Override
	public void add(int index, Object element) {
	}
	
	@Override
	public Object set(int index, Object element) {
		return null;
	}
}
