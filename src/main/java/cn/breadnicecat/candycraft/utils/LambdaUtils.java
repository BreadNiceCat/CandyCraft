package cn.breadnicecat.candycraft.utils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 22:11
 */
public class LambdaUtils {
    public static boolean never(Object... o) {
        return false;
    }

    public static boolean always(Object... o) {
        return true;
    }

    /**
     * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
     * @date 2023/1/18 17:31
     **/
    public static class LazyFunction<T, R> implements Function<T, R> {
        private final HashMap<T, R> cache = new HashMap<>();
        private final Function<T, R> func;

        private LazyFunction(Function<T, R> func) {
            this.func = Objects.requireNonNull(func);
        }

        public static <T, R> LazyFunction<T, R> of(Function<T, R> function) {
            return new LazyFunction<>(function);
        }

        @Override
        public R apply(T t) {
            return cache.computeIfAbsent(t, func);
        }
    }

    /**
     * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
     * @date 2022/12/25 9:17
     */
    @FunctionalInterface
    public interface SuBiConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    /**
     * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
     * @date 2023/1/19 19:30
     */
    public static class LazySupplier<T> implements Supplier<T> {
        private final UndimodifiableObject<T> cache = new UndimodifiableObject<>();
        private final Supplier<T> supplier;

        public LazySupplier(@NotNull Supplier<T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        public static <T> LazySupplier<T> of(Supplier<T> t) {
            return new LazySupplier<>(t);
        }

        @Override
        public T get() {
            if (cache.canReset()) {
                T t = supplier.get();
                cache.set(t);
                return t;
            } else {
                return cache.get();
            }
        }
    }
}
