package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.gui.menu.MarshmallowCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/10 15:24
 */
public class BlockMarshmallowCraftingTable extends CraftingTableBlock {
	private static final Component CONTAINER_TITLE = new TranslatableComponent("container.candycraft.crafting");

	public BlockMarshmallowCraftingTable(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MenuProvider getMenuProvider(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos) {
		return new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new MarshmallowCraftingMenu(pContainerId, pPlayerInventory, ContainerLevelAccess.create(pLevel, pPos)), CONTAINER_TITLE);
	}
}
