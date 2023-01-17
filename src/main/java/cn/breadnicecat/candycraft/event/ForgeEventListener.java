package cn.breadnicecat.candycraft.event;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.item.CCItemManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 13:50
 */
@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class ForgeEventListener {
	@SubscribeEvent
	public static void onLivingFall(LivingFallEvent event) {
		if (event.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).is(CCItemManager.trampojelly_boots.get())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onTick(TickEvent event) {
		//debug时使用
	}

}
