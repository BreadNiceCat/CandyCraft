package cn.breadnicecat.candycraft.block.blockentity;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Range;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 9:10
 */
public interface IHasFuel {
	@Range(from = 0, to = 1)
	default float getFuelHeatLeftPercent() {
		return hasFuelHeat() ? ((getCurrentFuelHeat() < getCurrentFuelMaxHeat()) ? ((float) getCurrentFuelHeat() / getCurrentFuelMaxHeat()) : 1f) : 0f;
	}
	
	default boolean hasFuelHeat() {
		return getCurrentFuelHeat() > 0;
	}
	
	int getCurrentFuelHeat();
	
	int getCurrentFuelMaxHeat();
	
	boolean isFuel(ItemStack stack);
}
