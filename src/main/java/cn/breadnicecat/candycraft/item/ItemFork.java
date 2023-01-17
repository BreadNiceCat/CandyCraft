package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.enchantment.CCEnchantmentManager;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 18:48
 */
public class ItemFork extends Item {

	private static final String TARGET_BLOCK = CandyCraft.prefixUnderline("target_block");

	public ItemFork(Properties prop) {
		super(prop);
	}

	public int[] getTargetData(ItemStack itemStack) {
		int[] posIntArray = itemStack.getOrCreateTag().getIntArray(TARGET_BLOCK);
		if (posIntArray.length != 4) {
			CandyCraft.LOGGER.error("invalid data (key:'{}',data:'{}') in {}", TARGET_BLOCK, Arrays.toString(posIntArray), itemStack);
			return new int[]{0, 256, 0, -1};
		}
		return posIntArray;
	}


	@Override
	public @NotNull InteractionResult useOn(UseOnContext pContext) {
		BlockPos pos = pContext.getClickedPos();
		Level level = pContext.getLevel();
		BlockState blockState = level.getBlockState(pos);
		Player player = pContext.getPlayer();
		ItemStack fork = pContext.getItemInHand();
		//吃方块
		if (player != null && player.canEat(false)
				&& EnchantmentHelper.getItemEnchantmentLevel(CCEnchantmentManager.devourer.get(), fork) > 0
				&& blockState.is(CCBlockTags.SUGARY_BLOCK)) {
			float destroyTime = blockState.getBlock().defaultDestroyTime() * 20;
			fork.addTagElement(TARGET_BLOCK, new IntArrayTag(new int[]{pos.getX(), pos.getY(), pos.getZ(), (int) Math.floor(((destroyTime == 0) ? 10 : destroyTime))}));
			player.startUsingItem(pContext.getHand());
			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		//耕布丁
		if (blockState.is(CCBlockManager.flour.block().get()) || blockState.is(CCBlockManager.pudding.block().get())) {
			level.setBlock(pos, CCBlockManager.flour_farm.block().get().defaultBlockState(), 2);
			return InteractionResult.sidedSuccess(level.isClientSide());
		}

		return super.useOn(pContext);
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
		return UseAnim.EAT;
	}

	public int getUseDuration(@NotNull ItemStack pStack) {
		return (int) Math.floor(getTargetData(pStack)[3]);
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pLivingEntity) {
		int[] targetData = getTargetData(pStack);
		BlockPos blockPos = new BlockPos(targetData[0], targetData[1], targetData[2]);
		pLevel.destroyBlock(blockPos, false, pLivingEntity);
		if (pLivingEntity instanceof Player player) {
			player.getFoodData().eat(1, 1.0F);
			//这里以后可以用来添加识别食用后效果和食用饱食度标签

			player.eat(pLevel, pStack);//注意:这行并不会加饱食度,因为Fork没有食物标签,这行是为了播放anim和声音
		}
		pStack.hurtAndBreak(Math.round(getUseDuration(pStack) / 20F), pLivingEntity, (entity) -> entity.broadcastBreakEvent(pLivingEntity.getUsedItemHand()));
		pStack.removeTagKey(TARGET_BLOCK);
		return super.finishUsingItem(pStack, pLevel, pLivingEntity);
	}

	@Override
	public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, @NotNull Player pPlayer, @NotNull LivingEntity pInteractionTarget, @NotNull InteractionHand pUsedHand) {
		return InteractionResult.PASS;
	}
}
