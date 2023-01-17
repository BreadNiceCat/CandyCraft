package cn.breadnicecat.candycraft.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/17 13:19
 * 无法二次修改的对象
 */
public class UnmodifiableObject<O> {
	private O o;

	public UnmodifiableObject() {
	}

	public static <O> @NotNull UnmodifiableObject<O> of(O o) {
		return new UnmodifiableObject<>(o);
	}

	public UnmodifiableObject(O o) {
		set(Objects.requireNonNull(o));
	}

	public void set(O o) {
		if (this.o != null) {
			throw new UnsupportedOperationException("Variable has been assigned a value");
		} else {
			this.o = o;
		}
	}

	public O get() {
		return o;
	}
}
