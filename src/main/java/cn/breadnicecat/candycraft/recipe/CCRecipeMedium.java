package cn.breadnicecat.candycraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * BNC:SB mc,谁说一定得要容器才能合成
 *
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 14:46
 */
public abstract class CCRecipeMedium extends CCRecipeBase<Container> {

    protected CCRecipeMedium(ResourceLocation id) {
        super(id);
    }

    @Deprecated
    @Override
    public final boolean matches(@NotNull Container pContainer, @NotNull Level pLevel) {
        return false;
    }

    @Deprecated
    @Override
    public final @NotNull ItemStack assemble(@NotNull Container pContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    @Deprecated
    public final boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return ItemStack.EMPTY;
    }

    /**
     * 合成返回的结果
     *
     * @param itemIn 原料,需要修改
     */
    public abstract @NotNull ItemStack assemble(List<ItemStack> itemIn);

    /**
     * 作为展示用的结果
     */
    @Override
    public abstract @NotNull ItemStack getResultItem();

    public abstract boolean matches(List<ItemStack> items, Level level);
}
