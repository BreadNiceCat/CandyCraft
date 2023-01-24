package cn.breadnicecat.candycraft.utils;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 12:16
 */
public class VoidMap extends AbstractMap<Object, Object> {
	
	public VoidMap() {
	}
	
	@SuppressWarnings("unchecked")
	public <K, V> Map<K, V> castMap() {
		return (Map<K, V>) this;
	}
	
	@NotNull
	@Override
	public Set<Entry<Object, Object>> entrySet() {
		return new HashSet<>();
	}
	
	@Override
	public Object put(Object key, Object value) {
		return null;
	}
}
