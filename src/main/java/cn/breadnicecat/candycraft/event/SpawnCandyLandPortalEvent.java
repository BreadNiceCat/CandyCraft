package cn.breadnicecat.candycraft.event;

import cn.breadnicecat.candycraft.utils.NetherLikePortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 11:38
 */
@Cancelable
public class SpawnCandyLandPortalEvent extends BlockEvent {

	private final NetherLikePortalShape shape;
	private final boolean upgrade;

	public SpawnCandyLandPortalEvent(LevelAccessor world, BlockPos pos, BlockState state, NetherLikePortalShape shape, boolean upgrade) {
		super(world, pos, state);
		this.shape = shape;
		this.upgrade = upgrade;
	}

	public NetherLikePortalShape getPortalShape() {
		return shape;
	}

	public boolean isUpgrade() {
		return upgrade;
	}
}
