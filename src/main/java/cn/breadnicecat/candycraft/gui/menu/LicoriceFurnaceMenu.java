package cn.breadnicecat.candycraft.gui.menu;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntityLicoriceFurnace;
import cn.breadnicecat.candycraft.gui.menu.slot.FuelSlot;
import cn.breadnicecat.candycraft.gui.menu.slot.ResultSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 10:26
 */
public class LicoriceFurnaceMenu extends CCBaseMenu<BlockEntityLicoriceFurnace> {
	private static final int INPUT_SLOT = 36;
	private static final int FUEL_SLOT = 37;
	private static final int OUTPUT_SLOT = 38;
	
	public LicoriceFurnaceMenu(int containerId, Inventory inv, FriendlyByteBuf data) {
		this(containerId, inv, data.readBlockPos());
	}
	
	public LicoriceFurnaceMenu(int pContainerId, Inventory playerInv, BlockPos pos) {
		super(CCMenuManager.licorice_furnace_menu.get(), pContainerId, playerInv, pos);
	}
	
	@Override
	protected void init() {
		super.init();
		addSlot(new Slot(blockEntity, 0, 55, 17));
		addSlot(new FuelSlot<>(blockEntity, 1, 55, 53));
		addSlot(new ResultSlot(blockEntity, 2, 115, 35));
		addDataSlots(blockEntity.data);
	}
	
	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
		Slot slot = slots.get(pIndex);
		ItemStack item = slot.getItem();
		if (pIndex >= INV_SLOT_START && pIndex <= INV_SLOT_END) {
			moveItemStackTo(item, INPUT_SLOT, FUEL_SLOT + 1, true);//燃料优先
		} else {
			moveItemStackTo(item, INV_SLOT_START, INV_SLOT_END, false);
		}
		return ItemStack.EMPTY;
	}
}
