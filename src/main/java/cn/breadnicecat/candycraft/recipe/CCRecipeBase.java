package cn.breadnicecat.candycraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 15:06
 */
public abstract class CCRecipeBase<T extends Container> implements Recipe<T> {
    protected final ResourceLocation id;

    public CCRecipeBase(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    @Deprecated
    public final boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return ItemStack.EMPTY;
    }
}
