package cn.breadnicecat.candycraft.item.material;

import cn.breadnicecat.candycraft.item.ItemManager;
import cn.breadnicecat.codeovencore.item.ArmorMaterialImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:15
 */
public class CCArmorMaterials {
	public static final ArmorMaterial JellyCrown = new ArmorMaterialImpl("jelly_crown", 15, new int[]{4, 0, 0, 0}, 20, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.EMPTY);
	public static final ArmorMaterial TrampoJellyBoots = new ArmorMaterialImpl("trampojelly_boots", 10, new int[4], 8, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.of(ItemManager.cranberry_scale.get()));
	//TODO 维修物品：减震果冻
	public static final ArmorMaterial WaterMask = new ArmorMaterialImpl("water_mask", 10, new int[4], 8, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.EMPTY);

	public static final ArmorMaterial LICORICE = new ArmorMaterialImpl("licorice", 13, new int[]{1, 4, 5, 2}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ItemManager.licorice.get()));
	public static final ArmorMaterial HONEYCOMB = new ArmorMaterialImpl("honeycomb", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ItemManager.honeycomb.get()));
	public static final ArmorMaterial PEZ = new ArmorMaterialImpl("pez", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(ItemManager.pez.get()));
	public static final ArmorMaterial JAWBREAKER = new ArmorMaterialImpl("jawbreaker", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ItemManager.jawbreaker.get()));
}