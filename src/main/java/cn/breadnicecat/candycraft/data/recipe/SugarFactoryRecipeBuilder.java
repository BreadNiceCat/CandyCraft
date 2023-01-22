package cn.breadnicecat.candycraft.data.recipe;

import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.utils.ItemUtils;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 21:21
 */
public class SugarFactoryRecipeBuilder extends CCRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final boolean advanced;

    public SugarFactoryRecipeBuilder(Ingredient ingredient, ItemStack result, boolean advanced) {
        this.ingredient = ingredient;
        this.result = result;
        this.advanced = advanced;
    }

    @Override
    @Deprecated
    public @NotNull Item getResult() {
        return result.getItem();
    }

    public ItemStack getResultStack() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(id));
    }

    public class Result extends CCFinishedRecipe {
        public Result(ResourceLocation id) {
            super(id);
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject pJson) {
            pJson.add("ingredient", ingredient.toJson());
            pJson.add("result", ItemUtils.toJson(result));
            pJson.addProperty("advanced", advanced);
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return CCRecipeManager.sugar_factory_recipe_serializer.get();
        }
    }
}
