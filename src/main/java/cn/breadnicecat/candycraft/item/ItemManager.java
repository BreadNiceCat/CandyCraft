package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.item.material.CCArmorMaterial;
import cn.breadnicecat.codeovencore.datagen.SimpleItemModelProvider;
import cn.breadnicecat.codeovencore.helper.ItemRegisterHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:34
 */
public class ItemManager {
	private static final ItemRegisterHelper register = CandyCraft.CORE_INSTANCE.getItemRegisterHelper();
	public static final RegistryObject<Diary> diary = register.registerItem("diary", Diary::new, SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_stick = register.registerSimpleItem("marshmallow_stick", newDefaultItemProperties());
	public static final RegistryObject<Item> licorice = register.registerSimpleItem("licorice", newDefaultItemProperties());
	public static final RegistryObject<Item> honeycomb = register.registerSimpleItem("honeycomb", newDefaultItemProperties());
	public static final RegistryObject<Item> honey_shard = register.registerSimpleItem("honey_shard", newDefaultItemProperties());
	public static final RegistryObject<Item> pez = register.registerSimpleItem("pez", "PEZ", newDefaultItemProperties());
	//	public static final RegistryObject<Item> pez_sword = register.registerItem("pez_sword", "PEZ Sword", new SwordItem(CCToolTier.PEZ));
	public static final RegistryObject<Item> pez_helmet = register.registerItem("pez_helmet", "PEZ Helmet", () -> new ArmorItem(CCArmorMaterial.PEZ, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_plate = register.registerItem("pez_chestplate", "PEZ Chestplate", () -> new ArmorItem(CCArmorMaterial.PEZ, EquipmentSlot.CHEST, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_leggings = register.registerItem("pez_leggings", "PEZ Leggings", () -> new ArmorItem(CCArmorMaterial.PEZ, EquipmentSlot.LEGS, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_boots = register.registerItem("pez_boots", "PEZ Boots", () -> new ArmorItem(CCArmorMaterial.PEZ, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);


	public static void init() {
	}

	protected static Item.Properties newDefaultItemProperties() {
		return new Item.Properties().tab(CandyCraft.TAB);
	}

	/**
	 * @param nutrition     饱和度
	 * @param saturationMod 饱食度
	 */
	protected static FoodProperties.Builder newFoodProperties(int nutrition, float saturationMod) {
		return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod);
	}

}
