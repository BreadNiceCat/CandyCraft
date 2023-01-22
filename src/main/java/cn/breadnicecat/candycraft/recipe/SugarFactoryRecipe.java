package cn.breadnicecat.candycraft.recipe;

import cn.breadnicecat.candycraft.utils.ItemUtils;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 20:53
 */
public class SugarFactoryRecipe extends CCSingleStackRecipe {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final boolean advanced;

    protected SugarFactoryRecipe(Ingredient ingredient, ItemStack result, boolean advanced, ResourceLocation id) {
        super(id);
        this.ingredient = ingredient;
        this.result = result;
        this.advanced = advanced;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return CCRecipeManager.caramel_portal_recipe_serializer.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return CCRecipeManager.sugar_factory_recipe_type.get();
    }

    @Override
    public ItemStack assemble(@NotNull ItemStack itemIn) {
        itemIn.shrink(1);
        return result.copy();
    }

    /**
     * @deprecated 不够详细，use matches(ItemStack, boolean, Level) instead
     */
    @Override
    @Deprecated
    public boolean matches(ItemStack itemIn, Level level) {
        return false;
    }

    public boolean matches(ItemStack itemIn, boolean isAdvanced, Level level) {
        return (isAdvanced || !advanced) && ingredient.test(itemIn);
    }

    public static class Serializer extends CCRecipeSerializerBase<SugarFactoryRecipe> {
        @Override
        public @NotNull SugarFactoryRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            Ingredient ingredient = Ingredient.fromJson(pSerializedRecipe.get("ingredient"));
            ItemStack result = ItemUtils.fromJson(pSerializedRecipe.get("result"), false);
            boolean advanced = pSerializedRecipe.get("advanced").getAsBoolean();
            return new SugarFactoryRecipe(ingredient, result, advanced, pRecipeId);
        }

        @Nullable
        @Override
        public SugarFactoryRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, @NotNull FriendlyByteBuf pBuffer) {
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack result = pBuffer.readItem();
            boolean advanced = pBuffer.readBoolean();
            return new SugarFactoryRecipe(ingredient, result, advanced, pRecipeId);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf pBuffer, @NotNull SugarFactoryRecipe pRecipe) {
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItemStack(pRecipe.result, false);
            pBuffer.writeBoolean(pRecipe.advanced);
        }
    }
}
