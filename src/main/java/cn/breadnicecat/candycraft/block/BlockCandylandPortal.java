package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.misc.CCConfig;
import cn.breadnicecat.candycraft.misc.CCDIMs;
import cn.breadnicecat.candycraft.misc.CandylandTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 22:32
 */
public class BlockCandylandPortal extends NetherPortalBlock {
	public BlockCandylandPortal(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
		//无需randomTick
	}

	@Override
	public void entityInside(@NotNull BlockState pState, @NotNull Level level, @NotNull BlockPos pPos, @NotNull Entity entity) {
		if (entity instanceof ItemEntity itemE) {
			//TODO 传送门配方
		} else if (entity.isAlive() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			ResourceKey<Level> destination = teleportDestination(pState, level, pPos);
			if (destination != null) {
				MinecraftServer server = level.getServer();
				if (server != null) {
					ServerLevel cl = server.getLevel(destination);
					if (cl != null) {
						entity.changeDimension(cl, new CandylandTeleporter());
					}
				}
			}
		}
	}

	@Override
	public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Random pRand) {
		if (teleportDestination(pState, pLevel, pPos) != null) {
			//[VanillaCopy]复制于PortalBlock
			for (int i = 0; i < 4; ++i) {
				double d0 = (double) pPos.getX() + pRand.nextDouble();
				double d1 = (double) pPos.getY() + pRand.nextDouble();
				double d2 = (double) pPos.getZ() + pRand.nextDouble();
				double d3 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
				double d4 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
				double d5 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
				int j = pRand.nextInt(2) * 2 - 1;
				if (!pLevel.getBlockState(pPos.west()).is(this) && !pLevel.getBlockState(pPos.east()).is(this)) {
					d0 = (double) pPos.getX() + 0.5D + 0.25D * (double) j;
					d3 = pRand.nextFloat() * 2.0F * (float) j;
				} else {
					d2 = (double) pPos.getZ() + 0.5D + 0.25D * (double) j;
					d5 = pRand.nextFloat() * 2.0F * (float) j;
				}
				pLevel.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			}
		}
	}


	/**
	 * @return null如果无法传送
	 */
	private @Nullable ResourceKey<Level> teleportDestination(BlockState pState, Level level, BlockPos pPos) {
		boolean canTo = level.dimension() == Level.OVERWORLD && CCConfig.canJoinCandyland.get();
		if (!canTo) {
			return null;
		} else {
			return CCDIMs.CANDYLAND;
		}
	}
}