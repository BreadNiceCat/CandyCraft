package cn.breadnicecat.candycraft.data.recipe;

import cn.breadnicecat.candycraft.misc.CCDIMs;
import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.utils.ItemUtils;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 16:41
 */
public class CaramelPortalRecipeBuilder extends CCRecipeBuilder {
    private ItemStack result;
    private Ingredient in;
    private InDimension inDimension = InDimension.BOTH;

    public CaramelPortalRecipeBuilder(ItemStack result, Ingredient in) {
        this.result = result;
        this.in = in;
    }

    public CaramelPortalRecipeBuilder inDim(InDimension dim) {
        this.inDimension = dim;
        return this;
    }


    /**
     * @deprecated use getResultStack() instead
     */
    @Override
    @Deprecated
    public @NotNull Item getResult() {
        return result.getItem();
    }

    public @NotNull ItemStack getResultStack() {
        return result;
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(id));
    }

    public enum InDimension {
        CANDYLAND(CCDIMs.CANDYLAND),
        OVERWORLD(Level.OVERWORLD),
        BOTH(null);

        public final ResourceKey<Level> level;

        InDimension(ResourceKey<Level> level) {
            this.level = level;
        }
    }

    public class Result extends CCFinishedRecipe {
        public Result(@NotNull ResourceLocation id) {
            super(id);
        }


        @Override
        public void serializeRecipeData(@NotNull JsonObject pJson) {
            pJson.add("ingredient", in.toJson());
            pJson.add("result", ItemUtils.toJson(result));
            if (inDimension.level != null) pJson.addProperty("dimension", inDimension.level.location().toString());
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return CCRecipeManager.caramel_portal_recipe_serializer.get();
        }
    }
}