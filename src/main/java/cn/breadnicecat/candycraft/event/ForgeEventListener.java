package cn.breadnicecat.candycraft.event;

import cn.breadnicecat.candycraft.item.ItemManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 13:50
 */
@Mod.EventBusSubscriber
public class ForgeEventListener {
	@SubscribeEvent
	public static void onLivingFall(LivingFallEvent event) {
		if (event.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).is(ItemManager.trampojelly_boots.get())) {
			event.setCanceled(true);
		}
	}
}
