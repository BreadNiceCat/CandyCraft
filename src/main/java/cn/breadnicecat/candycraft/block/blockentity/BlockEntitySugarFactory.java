package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.misc.CCItemTags;
import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.recipe.CCRecipeType;
import cn.breadnicecat.candycraft.recipe.SugarFactoryRecipe;
import cn.breadnicecat.candycraft.utils.LambdaAccessor;
import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:08
 */
public class BlockEntitySugarFactory extends CCBlockEntityBase implements WorldlyContainer, IHasProgress {
	protected static final SugarFactoryRecipe DEFAULT_RECIPE = new SugarFactoryRecipe(Ingredient.of(CCItemTags.SUGARY), Items.SUGAR.getDefaultInstance(), false, CandyCraft.prefix("sugar"));
	protected static final int[] SLOTS_FOR_OTHERS = {0};
	protected static final int[] SLOTS_FOR_DOWN = {1};
	protected final CCItemStackHandler items = new CCItemStackHandler(2, (h, s) -> setChanged());
	private final CCRecipeType<Recipe<Container>> RECIPE_TYPE = CCRecipeManager.sugar_factory_recipe_type.get();
	protected final boolean isAdvanced;
	protected final int maxProcessedTick;
	protected int processedTick = 0;
	protected boolean isWorking = false;
	public CCContainerData data = new CCContainerData(
			LambdaAccessor.of(() -> processedTick, (t) -> processedTick = t)
	);
	protected SugarFactoryRecipe recipeCache;//缓存不会清
	
	public BlockEntitySugarFactory(BlockPos pPos, BlockState pBlockState) {
		this(CCBlockEntityManager.sugar_factory.get(), pPos, pBlockState, false, (int) TickUnit.toTick(TimeUnit.SECONDS, 10), CCRecipeManager.sugar_factory_recipe_type.get());
	}
	
	protected BlockEntitySugarFactory(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, boolean isAdvanced, int maxProcessedTick, CCRecipeType<Recipe<Container>> recipeType) {
		super(pType, pPos, pBlockState, recipeType);
		this.isAdvanced = isAdvanced;
		this.maxProcessedTick = maxProcessedTick;
	}
	
	@Override
	public void load(@NotNull CompoundTag pTag) {
		super.load(pTag);
		if (pTag.contains("items")) items.deserializeNBT(pTag.getCompound("items"));
		if (pTag.contains("processed")) processedTick = pTag.getInt("processed");
	}
	
	@Override
	protected void saveAdditional(@NotNull CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.put("items", items.serializeNBT());
		pTag.putInt("processed", processedTick);
	}
	
	public void serverTick() {
		checkWorkingState();
		if (!isWorking) {
			processedTick = 0;
		} else if (++processedTick >= maxProcessedTick) {
			items.insertItem(1, recipeCache.assemble(items.getStackInSlot(0)), false);
			isWorking = false;
			if (recipeCache == DEFAULT_RECIPE) recipeCache = null;//因为特殊性,默认配方test也会为true
			processedTick = 0;
		}
	}
	
	protected void checkWorkingState() {
		//判断缓存的配方是否有效
		if (recipeCache != null) {
			if (!items.insertItem(1, recipeCache.getResultItem(), true).isEmpty()) {
				//堵塞
				isWorking = false;
				return;
			} else if (recipeCache.matches(items.getStackInSlot(0), isAdvanced)) {
				isWorking = true;
				//有效
				return;
			}
		}
		//寻找配方
		SugarFactoryRecipe recipe = tryFindRecipe();
		if (recipe != null) {
			recipeCache = recipe;
			checkWorkingState();//用来检测是否堵塞//第二次一定不会走到这里
			return;
		}
		//找不到配方
		isWorking = false;
	}
	
	@Nullable
	protected SugarFactoryRecipe tryFindRecipe() {
		if (items.getStackInSlot(0).isEmpty()) {
			return null;
		}
		for (Recipe<Container> recipe : level.getRecipeManager().getAllRecipesFor(RECIPE_TYPE)) {
			SugarFactoryRecipe r = (SugarFactoryRecipe) recipe;
			if (r.matches(items.getStackInSlot(0), isAdvanced)) {
				return r;
			}
		}
		if (DEFAULT_RECIPE.matches(items.getStackInSlot(0), isAdvanced)) {
			return DEFAULT_RECIPE;
		}
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	@Override
	public @NotNull ItemStack getItem(int pSlot) {
		return items.getStackInSlot(pSlot);
	}
	
	//Container impl
	@Override
	public int getContainerSize() {
		return items.getSlots();
	}
	
	@Override
	public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
		return items.extractItem(pSlot, pAmount, false);
	}
	
	@Override
	public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
		return items.removeItem(pSlot);
	}
	
	@Override
	public void setItem(int slot, @NotNull ItemStack stack) {
		items.setStackInSlot(slot, stack);
	}
	
	
	@Override
	public boolean canPlaceItem(int pIndex, @NotNull ItemStack pStack) {
		return pIndex != 1;
	}
	
	@Override
	public void clearContent() {
		items.clearAll();
	}
	
	@Override
	public int @NotNull [] getSlotsForFace(@NotNull Direction pSide) {
		return pSide == Direction.DOWN ? SLOTS_FOR_DOWN : SLOTS_FOR_OTHERS;
	}
	
	@Override
	public boolean canPlaceItemThroughFace(int pIndex, @NotNull ItemStack pItemStack, @Nullable Direction pDirection) {
		return pDirection != Direction.DOWN;
	}
	
	@Override
	public boolean canTakeItemThroughFace(int pIndex, @NotNull ItemStack pStack, @NotNull Direction pDirection) {
		return pDirection == Direction.DOWN;
	}
	
	//IHasProcess impl
	
	@Override
	public int getMaxProgress() {
		return maxProcessedTick;
	}
	
	@Override
	public int getCurrentProgress() {
		return processedTick;
	}
}
