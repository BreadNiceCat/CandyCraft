package cn.breadnicecat.candycraft.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/23 22:10
 */
public class AdvancedSugarFactoryMenu extends SugarFactoryMenu {
	
	
	public AdvancedSugarFactoryMenu(int pContainerId, Inventory playerInv, BlockPos pos) {
		super(CCMenuManager.advanced_sugar_factory_menu.get(), pContainerId, playerInv, pos);
	}
	
	public AdvancedSugarFactoryMenu(int containerId, Inventory inv, FriendlyByteBuf data) {
		this(containerId, inv, data.readBlockPos());
	}
}
