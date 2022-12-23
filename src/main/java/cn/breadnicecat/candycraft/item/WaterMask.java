package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.item.material.CCArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 13:30
 */
public class WaterMask extends ArmorItem {

	public WaterMask() {
		super(CCArmorMaterials.WaterMask, EquipmentSlot.HEAD, ItemManager.newDefaultItemProperties());
	}

	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		if (player.isUnderWater()) {
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false, false));
			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, false, false, false));
		}
	}
}
