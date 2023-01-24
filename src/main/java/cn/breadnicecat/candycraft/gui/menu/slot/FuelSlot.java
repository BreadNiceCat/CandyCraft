package cn.breadnicecat.candycraft.gui.menu.slot;

import cn.breadnicecat.candycraft.block.blockentity.IHasFuel;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 12:52
 */
public class FuelSlot<T extends Container & IHasFuel> extends Slot {
	
	private final IHasFuel fuelContainer;
	
	public FuelSlot(T pContainer, int pSlot, int pX, int pY) {
		super(pContainer, pSlot, pX, pY);
		this.fuelContainer = pContainer;
	}
	
	@Override
	public boolean mayPlace(@NotNull ItemStack pStack) {
		return fuelContainer.isFuel(pStack);
	}
}
