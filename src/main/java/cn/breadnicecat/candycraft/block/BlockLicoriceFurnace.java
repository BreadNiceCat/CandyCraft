package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntityLicoriceFurnace;
import cn.breadnicecat.candycraft.gui.menu.LicoriceFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/10 16:45
 */

public class BlockLicoriceFurnace extends AbstractFurnaceBlock {
	public static final TranslatableComponent TITLE = new TranslatableComponent("container.candycraft.licorice_furnace");
	
	public BlockLicoriceFurnace(Properties properties) {
		super(properties);
	}
	
	@Override
	protected void openContainer(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {
		NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((id, inv, pp) -> new LicoriceFurnaceMenu(id, inv, pPos), TITLE), pPos);
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
		return new BlockEntityLicoriceFurnace(pPos, pState);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
		return (lvl, pPos, pState1, be) -> {
			if (!lvl.isClientSide() && be instanceof BlockEntityLicoriceFurnace t) {
				t.serverTick();
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	public boolean hasAnalogOutputSignal(@NotNull BlockState pState) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public int getAnalogOutputSignal(@NotNull BlockState pBlockState, Level pLevel, @NotNull BlockPos pPos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(pLevel.getBlockEntity(pPos));
	}
}
