package cn.breadnicecat.candycraft.gui.menu;

import cn.breadnicecat.candycraft.block.CCBlockManager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/10 15:31
 */
public class MarshmallowCraftingMenu extends CraftingMenu {

	private ContainerLevelAccess access;

	public MarshmallowCraftingMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
	}

	public MarshmallowCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
		super(pContainerId, pPlayerInventory, pAccess);
		this.access = pAccess;
	}

	@Override
	public boolean stillValid(@NotNull Player pPlayer) {
		return stillValid(access, pPlayer, CCBlockManager.marshmallow_crafting_table.block().get());
	}
}
