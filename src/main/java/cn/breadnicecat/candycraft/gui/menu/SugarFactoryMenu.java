package cn.breadnicecat.candycraft.gui.menu;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntitySugarFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:16
 */
public class SugarFactoryMenu extends CCBaseMenu<BlockEntitySugarFactory> {

    public SugarFactoryMenu(int pContainerId, Inventory playerInv, BlockPos pos) {
        super(CCMenuManager.sugar_factory_menu.get(), pContainerId, playerInv, pos);
    }

    public SugarFactoryMenu(int containerId, Inventory inv, FriendlyByteBuf data) {
        this(containerId, inv, data.readBlockPos());
    }
}
