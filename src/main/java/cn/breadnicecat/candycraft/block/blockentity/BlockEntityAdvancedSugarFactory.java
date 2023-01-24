package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/23 21:46
 */
public class BlockEntityAdvancedSugarFactory extends BlockEntitySugarFactory {
	
	public BlockEntityAdvancedSugarFactory(BlockPos pPos, BlockState pBlockState) {
		super(CCBlockEntityManager.advanced_sugar_factory.get(), pPos, pBlockState, true, (int) TickUnit.toTick(TimeUnit.SECONDS, 6), CCRecipeManager.licorice_furnace_recipe_type.get());
	}
	
}
