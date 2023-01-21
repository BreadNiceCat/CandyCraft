package cn.breadnicecat.candycraft.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/18 23:21
 */
public class CCCraftingTableRecipeProvider extends RecipeProvider {
    public CCCraftingTableRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        CCDatagenManager.recipes.forEach((entry) -> entry.accept(pFinishedRecipeConsumer));
    }


}
