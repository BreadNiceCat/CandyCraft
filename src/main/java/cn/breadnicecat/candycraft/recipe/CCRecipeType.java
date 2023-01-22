package cn.breadnicecat.candycraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 13:59
 */
public record CCRecipeType<T extends Recipe<?>>(ResourceLocation id) implements RecipeType<T> {
    @Override
    public String toString() {
        return id.toString();
    }

    public String getName() {
        return id().getPath();
    }

}
