package cn.breadnicecat.candycraft.gui.menu;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntitySugarFactory;
import cn.breadnicecat.candycraft.gui.menu.slot.ResultSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:16
 */
public class SugarFactoryMenu extends CCBaseMenu<BlockEntitySugarFactory> {
	
	
	private static int INPUT_SLOT = 36;
	private static int OUTPUT_SLOT = 37;
	
	public SugarFactoryMenu(int containerId, Inventory inv, FriendlyByteBuf data) {
		this(containerId, inv, data.readBlockPos());
	}
	
	public SugarFactoryMenu(int pContainerId, Inventory playerInv, BlockPos pos) {
		this(CCMenuManager.sugar_factory_menu.get(), pContainerId, playerInv, pos);
	}
	
	public SugarFactoryMenu(MenuType<?> pMenuType, int pContainerId, Inventory playerInv, BlockPos pos) {
		super(pMenuType, pContainerId, playerInv, pos);
	}
	
	@Override
	protected void init() {
		super.init();
		addSlot(new Slot(blockEntity, 0, 7, 58));
		addSlot(new ResultSlot(blockEntity, 1, 151, 58));
		addDataSlots(blockEntity.data);
	}
	
	/*tmd这个返回值到底什么个意思啊，如果不返回Empty就for循环直接卡死*/
	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
		Slot slot = slots.get(pIndex);
		if (slot.hasItem()) {
			ItemStack item = slot.getItem();
			if (pIndex >= INV_SLOT_START && pIndex <= INV_SLOT_END) {
				moveItemStackTo(item, INPUT_SLOT, INPUT_SLOT + 1, false);
			} else {
				moveItemStackTo(item, INV_SLOT_START, INV_SLOT_END + 1, false);
			}
		}
		return ItemStack.EMPTY;
	}
}
