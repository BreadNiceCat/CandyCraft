package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 9:17
 */
public class Trampojelly extends Block {

	private final float immuneFallDamageMultiplier;
	private final float jumpFactor;
	private final boolean jumpImmediately;

	/**
	 * @param jumpFactor      最终结果将乘2.5倍
	 * @param jumpImmediately 若为false则走在上面没有效果，需要跳跃
	 */
	public Trampojelly(Properties pProperties, float jumpFactor, float immuneFallDamageMultiplier, boolean jumpImmediately) {
		super(pProperties);
		this.jumpFactor = jumpFactor * 2.5F;
		this.jumpImmediately = jumpImmediately;
		this.immuneFallDamageMultiplier = immuneFallDamageMultiplier;
	}

	@Override
	public void fallOn(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull Entity pEntity, float pFallDistance) {
		pEntity.causeFallDamage(pFallDistance, immuneFallDamageMultiplier, DamageSource.FALL);
	}

	@Override
	public void updateEntityAfterFallOn(@NotNull BlockGetter pLevel, @NotNull Entity pEntity) {
		if (pEntity instanceof LivingEntity entity) {
			jump(entity);
		} else {
			super.updateEntityAfterFallOn(pLevel, pEntity);
		}
	}

	public void jump(LivingEntity entity) {
		if (jumpImmediately) entity.jumpFromGround();
	}

	@Override
	public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
		if (pEntity instanceof LivingEntity entity) {
			jump(entity);
		} else {
			super.stepOn(pLevel, pPos, pState, pEntity);
		}
	}

	@Override
	public float getJumpFactor() {
		return this.jumpFactor;
	}
}
