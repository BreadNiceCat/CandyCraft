package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.block.blockentity.BlockEntityAdvancedSugarFactory;
import cn.breadnicecat.candycraft.gui.menu.AdvancedSugarFactoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 20:09
 */
public class BlockAdvancedSugarFactory extends BlockSugerFactory {
	public static final TranslatableComponent TITLE = new TranslatableComponent("container.candycraft.advanced_sugar_factory");
	
	public BlockAdvancedSugarFactory(Properties properties) {
		super(properties);
	}
	
	@Override
	public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
		if (!pLevel.isClientSide()) {
			NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((id, inv, player) -> new AdvancedSugarFactoryMenu(id, inv, pPos), TITLE), pPos);
		}
		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}
	
	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
		return new BlockEntityAdvancedSugarFactory(pPos, pState);
	}
}
