package cn.breadnicecat.candycraft.recipe;

import cn.breadnicecat.candycraft.utils.ItemUtils;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
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
 * @date 2023/1/22 14:32
 */
public class CaramelPortalRecipe extends CCSingleStackRecipe {

    private final Ingredient ingredient;
    private final ItemStack output;
    @Nullable
    private final ResourceKey<Level> inDimension;

    public CaramelPortalRecipe(ResourceLocation id, Ingredient ingredient, ItemStack output, @Nullable ResourceKey<Level> inDimension) {
        super(id);
        this.ingredient = ingredient;
        this.output = output;
        this.inDimension = inDimension;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return CCRecipeManager.caramel_portal_recipe_serializer.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return CCRecipeManager.caramel_portal_type.get();
    }

    /**
     * @apiNote 不会处理itemIn
     */
    @Override
    public ItemStack assemble(ItemStack itemIn) {
        int i = itemIn.getCount();
        return ItemUtils.setCountByChain(output.copy(), output.getCount() * i);
    }

    public boolean matches(ItemStack item, Level level) {
        return (inDimension == null || inDimension.equals(level.dimension())) && ingredient.test(item);
    }

    public static class Serializer extends CCRecipeSerializerBase<CaramelPortalRecipe> {
        @Override
        public @NotNull CaramelPortalRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            Ingredient ingredient = Ingredient.fromJson(pSerializedRecipe.get("ingredient"));
            ItemStack output = ItemUtils.fromJson(pSerializedRecipe.get("result").getAsJsonObject(), true);
            ResourceKey<Level> inDimension = null;
            if (pSerializedRecipe.has("dimension")) {
                inDimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(pSerializedRecipe.get("dimension").getAsString()));
            }
            return new CaramelPortalRecipe(pRecipeId, ingredient, output, inDimension);
        }

        @Nullable
        @Override
        public CaramelPortalRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, @NotNull FriendlyByteBuf pBuffer) {
            Ingredient in = Ingredient.fromNetwork(pBuffer);
            ItemStack out = pBuffer.readItem();
            ResourceKey<Level> inDimension = null;
            if (pBuffer.isReadable()) {
                inDimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, pBuffer.readResourceLocation());
            }
            return new CaramelPortalRecipe(pRecipeId, in, out, inDimension);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull CaramelPortalRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItemStack(recipe.output, false);
            if (recipe.inDimension != null) buffer.writeResourceLocation(recipe.inDimension.location());
        }
    }
}