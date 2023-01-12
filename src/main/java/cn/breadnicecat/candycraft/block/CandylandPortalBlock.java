package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 22:32
 */
public class CandylandPortalBlock extends NetherPortalBlock {
	public CandylandPortalBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
		//无需randomTick
	}

	@Override
	public void entityInside(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Entity pEntity) {
		if (!pEntity.isPassenger() && !pEntity.isVehicle() && pEntity.canChangeDimensions()) {

			if (pEntity instanceof ServerPlayer player) {
				if (pEntity.addTag("tipped")) {
					player.sendMessage(new TextComponent("Your transfer request has been received, but the server will not be processed for the time being because of WIP"), UUID.randomUUID());
				}
			}
		}
	}

	@Override
	public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Random pRand) {
		//复制于PortalBlock
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
