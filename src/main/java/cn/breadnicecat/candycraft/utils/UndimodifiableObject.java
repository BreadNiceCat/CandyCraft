package cn.breadnicecat.candycraft.utils;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/17 13:19
 * 无法被二次修改的对象
 */
public class UndimodifiableObject<O> implements Accessor<O> {
    private O o;

    public UndimodifiableObject() {
    }

    public static <O> @NotNull UndimodifiableObject<O> of(O o) {
        return new UndimodifiableObject<>(o);
    }

    public UndimodifiableObject(O o) {
    }

    public void set(O o) {
        if (!this.canReset()) {
            throw new UnsupportedOperationException("Variable has been assigned a value");
        } else {
            this.o = Objects.requireNonNull(o);
        }
    }

    @Nonnull
    public O get() {
        return Objects.requireNonNull(o);
    }

    public boolean canReset() {
        return o == null;
    }
}
