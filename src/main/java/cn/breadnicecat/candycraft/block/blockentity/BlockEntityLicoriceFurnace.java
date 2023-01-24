package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.recipe.CCRecipeType;
import cn.breadnicecat.candycraft.recipe.LicoriceFurnaceRecipe;
import cn.breadnicecat.candycraft.utils.LambdaAccessor;
import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 9:03
 */
public class BlockEntityLicoriceFurnace extends CCBlockEntityBase implements IHasProgress, IHasFuel, WorldlyContainer {
	public static final HashMap<Item, Integer> fuels = new HashMap<>();
	private final CCItemStackHandler items = new CCItemStackHandler(3, this::setChanged);
	private final int maxProcessedTick = (int) TickUnit.toTick(TimeUnit.SECONDS, 8);
	private int processedTick;
	private boolean isWorking;
	private int currentMaxFuelHeat;
	private int currentFuelHeat;
	public final CCContainerData data = new CCContainerData(
			LambdaAccessor.of(() -> processedTick, (t) -> processedTick = t),
			LambdaAccessor.of(() -> currentFuelHeat, (t) -> currentFuelHeat = t),
			LambdaAccessor.of(() -> currentMaxFuelHeat, (t) -> currentMaxFuelHeat = t)
	);
	private LicoriceFurnaceRecipe cacheRecipe;
	private static final int[] SLOT_FOR_UP = {0};
	private static final int[] SLOT_FOR_SIDE = {1};
	private static final int[] SLOT_FOR_DOWN = {2};
	
	static {
		fuels.put(Items.SUGAR, (int) TickUnit.toTick(TimeUnit.SECONDS, 5));
		fuels.put(CCBlockManager.sugar_block.block().get().asItem(), (int) TickUnit.toTick(TimeUnit.SECONDS, 20));
		fuels.put(CCItemManager.licorice.get(), (int) TickUnit.toTick(TimeUnit.SECONDS, 10));
		fuels.put(CCBlockManager.licorice_block.getItemLike().asItem(), (int) TickUnit.toTick(TimeUnit.SECONDS, 90));
		fuels.put(CCItemManager.pez.get(), (int) TickUnit.toTick(TimeUnit.SECONDS, 18));
		fuels.put(CCBlockManager.pez_block.getItemLike().asItem(), (int) TickUnit.toTick(TimeUnit.SECONDS, 162));
		fuels.put(CCItemManager.jawbreaker.get(), (int) TickUnit.toTick(TimeUnit.SECONDS, 30));
	}
	
	public BlockEntityLicoriceFurnace(BlockPos pPos, BlockState pBlockState) {
		this(CCBlockEntityManager.licorice_furnace.get(), pPos, pBlockState, CCRecipeManager.licorice_furnace_recipe_type.get());
	}
	
	public BlockEntityLicoriceFurnace(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, CCRecipeType<Recipe<Container>> recipeType) {
		super(pType, pPos, pBlockState, recipeType);
	}
	
	public void serverTick() {
		checkWorkingState();
		if (currentFuelHeat > 0) {
			currentFuelHeat--;
		}
		if (!isWorking && processedTick > 0) {//还原不工作进度条倒退而不是清零
			processedTick = Math.max(processedTick - 2, 0);
		} else if (isWorking) {
			if (currentFuelHeat <= 0) {
				Integer fu = fuels.get(items.extractItem(1, 1, false).getItem());
				if (fu != null) {
					currentFuelHeat += fu;
					currentMaxFuelHeat = fu;
				}
			}
			if (++processedTick >= maxProcessedTick) {
				items.insertItem(2, cacheRecipe.assemble(items.getStackInSlot(0)), false);
				processedTick = 0;
			}
		}
		level.setBlock(worldPosition, getBlockState().setValue(FurnaceBlock.LIT, currentFuelHeat > 0), 2);
	}
	
	private void checkWorkingState() {
		if (hasFuelHeat() || !items.getStackInSlot(1).isEmpty()) {
			if (cacheRecipe != null) {
				if (!items.insertItem(2, cacheRecipe.getResultItem(), true).isEmpty()) {//堵塞
					isWorking = false;
					return;
				} else if (cacheRecipe.matches(items.getStackInSlot(0))) {
					isWorking = true;
					return;
				}
			}
			//不匹配
			LicoriceFurnaceRecipe recipe = tryFindRecipe();
			if (recipe != null) {
				cacheRecipe = recipe;
				checkWorkingState();//检测是否堵塞
				return;
			}
		}
		//灭火或找不到配方
		isWorking = false;
	}
	
	private LicoriceFurnaceRecipe tryFindRecipe() {
		if (items.getStackInSlot(0).isEmpty()) {
			return null;
		}
		for (Recipe<Container> r : level.getRecipeManager().getAllRecipesFor(RECIPE_TYPE)) {
			LicoriceFurnaceRecipe r1 = (LicoriceFurnaceRecipe) r;
			if (r1.matches(items.getStackInSlot(0))) {
				return r1;
			}
		}
		return null;
	}
	
	@Override
	protected void saveAdditional(@NotNull CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.put("items", items.serializeNBT());
		pTag.putInt("processed", processedTick);
		pTag.putInt("heat", currentFuelHeat);
		pTag.putInt("maxHeat", currentMaxFuelHeat);
	}
	
	@Override
	public void load(@NotNull CompoundTag pTag) {
		super.load(pTag);
		if (pTag.contains("items")) items.deserializeNBT(pTag.getCompound("items"));
		if (pTag.contains("processed")) processedTick = pTag.getInt("processed");
		if (pTag.contains("heat")) currentFuelHeat = pTag.getInt("heat");
		if (pTag.contains("maxHeat")) currentMaxFuelHeat = pTag.getInt("maxHeat");
	}
//I
	
	@Override
	public int getCurrentFuelHeat() {
		return currentFuelHeat;
	}
	
	@Override
	public int getCurrentFuelMaxHeat() {
		return currentMaxFuelHeat;
	}
	
	@Override
	public int getMaxProgress() {
		return maxProcessedTick;
	}
	
	@Override
	public int getCurrentProgress() {
		return processedTick;
	}
	//Worldly
	//Container
	
	@Override
	public int @NotNull [] getSlotsForFace(@NotNull Direction pSide) {
		return switch (pSide) {
			case UP -> SLOT_FOR_UP;
			case DOWN -> SLOT_FOR_DOWN;
			default -> SLOT_FOR_SIDE;
		};
	}
	
	
	@Override
	public boolean canPlaceItemThroughFace(int pIndex, @NotNull ItemStack pItemStack, @Nullable Direction pDirection) {
		if (pDirection == Direction.DOWN) {
			return false;
		} else if (pDirection == Direction.UP) {
			return true;
		} else {
			return isFuel(pItemStack);
		}
	}
	
	@Override
	public boolean isFuel(ItemStack stack) {
		return fuels.containsKey(stack.getItem());
	}
	
	@Override
	public boolean canTakeItemThroughFace(int pIndex, @NotNull ItemStack pStack, @NotNull Direction pDirection) {
		return pDirection == Direction.DOWN;
	}
	
	@Override
	public int getContainerSize() {
		return items.getSlots();
	}
	
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	@Override
	public @NotNull ItemStack getItem(int pSlot) {
		return items.getStackInSlot(pSlot);
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
	public void setItem(int pSlot, @NotNull ItemStack pStack) {
		items.setStackInSlot(pSlot, pStack);
	}
	
	@Override
	public void clearContent() {
		items.clearAll();
	}
}
