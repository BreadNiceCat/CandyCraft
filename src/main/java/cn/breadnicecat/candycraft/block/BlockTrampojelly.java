package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 9:17
 */
public class BlockTrampojelly extends Block {

	private final float immuneFallDamageMultiplier;
	private final float jumpFactor;
	private final boolean jumpImmediately;

	/**
	 * @param jumpImmediately            走在上面是否需要跳跃才被弹飞
	 * @param immuneFallDamageMultiplier 摔落减伤,0=无伤
	 */
	public BlockTrampojelly(Properties pProperties, float jumpFactor, float immuneFallDamageMultiplier, boolean jumpImmediately) {
		super(pProperties);
		this.jumpFactor = jumpFactor;
		this.jumpImmediately = jumpImmediately;
		this.immuneFallDamageMultiplier = immuneFallDamageMultiplier;
	}

	@Override
	public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
		if (jumpImmediately) {
			jump(pEntity);
		}
		super.stepOn(pLevel, pPos, pState, pEntity);
	}

	private void jump(Entity entity) {
		entity.setDeltaMovement(0, jumpFactor, 0);
		entity.hasImpulse = true;
		if (entity instanceof LivingEntity livE) net.minecraftforge.common.ForgeHooks.onLivingJump(livE);
	}

	@Override
	public void fallOn(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull Entity pEntity, float pFallDistance) {
		pEntity.causeFallDamage(pFallDistance, immuneFallDamageMultiplier, DamageSource.FALL);
		if (jumpImmediately) {
			jump(pEntity);
		}
	}
}
