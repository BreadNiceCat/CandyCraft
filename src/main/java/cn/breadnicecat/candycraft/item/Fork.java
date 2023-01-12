package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.misc.CCBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 18:48
 */
public class Fork extends Item {

	private static final String TARGET_BLOCK = "target_block";

	public Fork() {
		super(CCItemManager.newDefaultItemProperties().durability(128));
	}

	public static BlockPos getTargetBlockPos(@NotNull ItemStack itemStack) {
		assert itemStack.getTag() != null;
		int[] posIntArray = itemStack.getTag().getIntArray(TARGET_BLOCK);
		return new BlockPos(posIntArray[0], posIntArray[1], posIntArray[2]);

	}

	public static int getTargetBlockStrength(@NotNull ItemStack itemStack) {
		if (itemStack.getTag() == null) return -1;
		int[] posIntArray = itemStack.getTag().getIntArray(TARGET_BLOCK);
		return posIntArray[3] == 0 ? 1 : posIntArray[3];
	}

	@Override
	public @NotNull InteractionResult useOn(UseOnContext pContext) {
		Player player = pContext.getPlayer();
		if (player != null) {
			BlockPos pos = pContext.getClickedPos();
			BlockState blockState = pContext.getLevel().getBlockState(pos);
			if (blockState.is(CCBlockTags.SUGARY_BLOCK)) {
				if (player.canEat(false)) {
					pContext.getItemInHand().addTagElement(TARGET_BLOCK, new IntArrayTag(new int[]{pos.getX(), pos.getY(), pos.getZ(), (int) Math.floor(blockState.getBlock().defaultDestroyTime() * 20)}));
					player.startUsingItem(pContext.getHand());
					return InteractionResult.sidedSuccess(pContext.getLevel().isClientSide());
				}
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
		return UseAnim.EAT;
	}

	public int getUseDuration(@NotNull ItemStack pStack) {
		return (int) Math.floor(getTargetBlockStrength(pStack) * 0.95);
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pLivingEntity) {
		pLevel.destroyBlock(getTargetBlockPos(pStack), false, pLivingEntity);
		if (pLivingEntity instanceof Player player) {
			player.eat(pLevel, pStack);//这行并不会加饱食度
			//这里以后添加识别食用后效果和食用饱食度标签
			player.getFoodData().eat(1, 1.0F);
		}
		pStack.hurtAndBreak(Math.round(getTargetBlockStrength(pStack) / 20F), pLivingEntity, (entity) -> entity.broadcastBreakEvent(pLivingEntity.getUsedItemHand()));
		pStack.removeTagKey(TARGET_BLOCK);
		return super.finishUsingItem(pStack, pLevel, pLivingEntity);
	}

	@Override
	public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, @NotNull Player pPlayer, @NotNull LivingEntity pInteractionTarget, @NotNull InteractionHand pUsedHand) {
		return InteractionResult.PASS;
	}
}
