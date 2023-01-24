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
 * @date 2023/1/24 17:24
 */
public class LicoriceFurnaceRecipeBuilder extends CCRecipeBuilder {
	private final ItemStack result;
	private final Ingredient ingredient;
	
	public LicoriceFurnaceRecipeBuilder(ItemStack result, Ingredient ingredient) {
		this.result = result;
		this.ingredient = ingredient;
	}
	
	@Override
	public @NotNull Item getResult() {
		return result.getItem();
	}
	
	@Override
	public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
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
		}
		
		@Override
		public @NotNull RecipeSerializer<?> getType() {
			return CCRecipeManager.licorice_furnace_recipe_serializer.get();
		}
	}
}
