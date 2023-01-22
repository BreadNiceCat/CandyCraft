package cn.breadnicecat.candycraft.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 14:15
 */
public abstract class CCRecipeSerializerBase<T extends Recipe<?>> implements RecipeSerializer<T> {

    private ResourceLocation registryName;

    @Override
    public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
        registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<RecipeSerializer<?>> getRegistryType() {
        Class<?> clazz = getClass();
        return (Class<RecipeSerializer<?>>) clazz;
    }
}

