package cn.breadnicecat.candycraft.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 18:48
 */
public class Fork extends Item {
	public Fork() {
		super(ItemManager.newDefaultItemProperties());
	}

	@Override
	public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
		return InteractionResult.PASS;
	}

	@Override
	public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, @NotNull Player pPlayer, @NotNull LivingEntity pInteractionTarget, @NotNull InteractionHand pUsedHand) {
		return InteractionResult.PASS;
	}
}
