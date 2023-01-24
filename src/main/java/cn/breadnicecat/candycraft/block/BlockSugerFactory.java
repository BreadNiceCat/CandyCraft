package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntitySugarFactory;
import cn.breadnicecat.candycraft.gui.menu.SugarFactoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 14:06
 */
public class BlockSugerFactory extends Block implements EntityBlock {
	
	private static final TranslatableComponent TITLE = new TranslatableComponent("container.candycraft.sugar_factory");
	
	public BlockSugerFactory(Properties pProperties) {
		super(pProperties);
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
		return new BlockEntitySugarFactory(pPos, pState);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pNewState, boolean pIsMoving) {
		if (!pLevel.isClientSide() && pLevel.getBlockEntity(pPos) instanceof BlockEntitySugarFactory factory) {
			Containers.dropContents(pLevel, pPos, factory);
		}
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
		if (!pLevel.isClientSide()) {
			NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((id, inv, player) -> new SugarFactoryMenu(id, inv, pPos), TITLE), pPos);
		}
		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
		return (level, blockPos, state, t) -> {
			if (!level.isClientSide() && t instanceof BlockEntitySugarFactory factory) {
				factory.serverTick();
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
