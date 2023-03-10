package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.misc.CCArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 13:30
 */
public class ItemWaterMask extends ArmorItem {

    public ItemWaterMask(Properties prop) {
        super(CCArmorMaterials.WATER_MASK, EquipmentSlot.HEAD, prop);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, @NotNull Player player) {
        if (player.isUnderWater()) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, false, false, false));
        }
    }
}
