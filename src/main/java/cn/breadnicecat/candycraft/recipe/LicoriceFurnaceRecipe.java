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
 * @date 2023/1/24 13:10
 */
public class LicoriceFurnaceRecipe extends CCSingleStackRecipe {
	private final ItemStack result;
	private final Ingredient ingredient;
	
	public LicoriceFurnaceRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result) {
		super(id);
		this.ingredient = ingredient;
		this.result = result;
	}
	
	@Override
	public @NotNull ItemStack getResultItem() {
		return result.copy();
	}
	
	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return CCRecipeManager.licorice_furnace_recipe_serializer.get();
	}
	
	@Override
	public @NotNull RecipeType<?> getType() {
		return CCRecipeManager.licorice_furnace_recipe_type.get();
	}
	
	@Override
	public ItemStack assemble(ItemStack itemIn) {
		itemIn.shrink(1);
		return result.copy();
	}
	
	@Override
	@Deprecated
	public boolean matches(ItemStack itemIn, Level level) {
		return matches(itemIn);
	}
	
	public boolean matches(ItemStack itemIn) {
		return ingredient.test(itemIn);
	}
	
	public static class Serializer extends CCRecipeSerializerBase<LicoriceFurnaceRecipe> {
		
		@Override
		public @NotNull LicoriceFurnaceRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject recipe) {
			Ingredient ingredient = Ingredient.fromJson(recipe.get("ingredient"));
			ItemStack result = ItemUtils.fromJson(recipe.get("result"), false);
			return new LicoriceFurnaceRecipe(pRecipeId, ingredient, result);
		}
		
		@Nullable
		@Override
		public LicoriceFurnaceRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, @NotNull FriendlyByteBuf pBuffer) {
			Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
			ItemStack result = pBuffer.readItem();
			return new LicoriceFurnaceRecipe(pRecipeId, ingredient, result);
		}
		
		@Override
		public void toNetwork(@NotNull FriendlyByteBuf pBuffer, @NotNull LicoriceFurnaceRecipe recipe) {
			recipe.ingredient.toNetwork(pBuffer);
			pBuffer.writeItemStack(recipe.result, false);
		}
	}
}
