package cn.breadnicecat.candycraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 15:29
 */
public abstract class CCSingleStackRecipe extends CCRecipeMedium {
    protected CCSingleStackRecipe(ResourceLocation id) {
        super(id);
    }

    /**
     * @param itemIn 只取第一个
     */
    @Deprecated
    @Override
    public @NotNull
    final ItemStack assemble(List<ItemStack> itemIn) {
        return assemble(itemIn.get(0));
    }

    /**
     * @param itemIn 只取第一个
     */
    @Deprecated
    @Override
    public final boolean matches(List<ItemStack> itemIn, Level level) {
        return matches(itemIn.get(0), level);
    }

    public abstract ItemStack assemble(ItemStack itemIn);

    public abstract boolean matches(ItemStack itemIn, Level level);
}
