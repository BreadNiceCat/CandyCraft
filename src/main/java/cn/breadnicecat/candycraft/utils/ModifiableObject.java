package cn.breadnicecat.candycraft.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 13:17
 */
public class ModifiableObject<O> implements Accessor<O> {
    private O o;

    public ModifiableObject() {
    }

    public static <O> @NotNull ModifiableObject<O> of(O o) {
        return new ModifiableObject<>(o);
    }

    public ModifiableObject(O o) {
    }

    public void set(O o) {
        this.o = o;
    }

    public O get() {
        return o;
    }
}
