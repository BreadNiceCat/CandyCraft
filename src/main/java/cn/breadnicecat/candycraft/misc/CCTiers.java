package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.item.ItemManager;
import cn.breadnicecat.codeovencore.item.TierImpl;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeHooks;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:30
 */
public class CCTiers {
	//TODO: 维修物品：Tag=棉花糖木头
	public static final Tier MARSHMALLOW = new TierImpl(0, 59, 2.0F, 0.0F, 15, ForgeHooks.getTagFromVanillaTier(Tiers.WOOD), () -> Ingredient.EMPTY);
	public static final Tier LICORICE = new TierImpl(1, 131, 4.0F, 1.0F, 5, ForgeHooks.getTagFromVanillaTier(Tiers.STONE), () -> Ingredient.of(ItemManager.licorice.get()));
	public static final Tier HONEYCOMB = new TierImpl(2, 250, 6.0F, 2.0F, 14, ForgeHooks.getTagFromVanillaTier(Tiers.IRON), () -> Ingredient.of(ItemManager.honeycomb.get()));
	public static final Tier PEZ = new TierImpl(3, 1561, 8.0F, 3.0F, 10, ForgeHooks.getTagFromVanillaTier(Tiers.DIAMOND), () -> Ingredient.of(ItemManager.pez.get()));
	public static final Tier JAWBREAKER = new TierImpl(4, 2031, 9.0F, 4.0F, 15, ForgeHooks.getTagFromVanillaTier(Tiers.NETHERITE), () -> Ingredient.of(ItemManager.jawbreaker.get()));
}
