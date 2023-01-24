package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.recipe.CCRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 13:40
 */
public abstract class CCBlockEntityBase extends BlockEntity implements Container {
	
	protected final CCRecipeType<Recipe<Container>> RECIPE_TYPE;
	
	public CCBlockEntityBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, CCRecipeType<Recipe<Container>> RECIPE_TYPE) {
		super(pType, pPos, pBlockState);
		this.RECIPE_TYPE = RECIPE_TYPE;
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return pPlayer.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}
	
}
