package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.item.material.CCArmorMaterials;
import cn.breadnicecat.candycraft.item.material.CCTiers;
import cn.breadnicecat.candycraft.sound.CCSounds;
import cn.breadnicecat.codeovencore.datagen.SimpleItemModelProvider;
import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import cn.breadnicecat.codeovencore.helper.ItemRegisterHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:34
 */
@SuppressWarnings("unused")//how stupid tip -- 100+ Warnings
public class ItemManager {
	private static final ItemRegisterHelper register = CandyCraft.CORE_INSTANCE.getItemRegisterHelper();
	public static final RegistryObject<Diary> diary = register.registerItem("diary", Diary::new, SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_stick = register.registerSimpleItem("marshmallow_stick", newDefaultItemProperties());
	public static final RegistryObject<Item> marshmallow_sword = register.registerItem("marshmallow_sword", () -> new SwordItem(CCTiers.MARSHMALLOW, 3, -2.4F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_shovel = register.registerItem("marshmallow_shovel", () -> new ShovelItem(CCTiers.MARSHMALLOW, 1.5F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_pickaxe = register.registerItem("marshmallow_pickaxe", () -> new PickaxeItem(CCTiers.MARSHMALLOW, 1, -2.8F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_axe = register.registerItem("marshmallow_axe", () -> new AxeItem(CCTiers.MARSHMALLOW, 6.0F, -3.2F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> marshmallow_hoe = register.registerItem("marshmallow_hoe", () -> new HoeItem(CCTiers.MARSHMALLOW, 0, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> licorice = register.registerSimpleItem("licorice", newDefaultItemProperties());
	public static final RegistryObject<Item> licorice_sword = register.registerItem("licorice_sword", () -> new SwordItem(CCTiers.LICORICE, 3, -2.4F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_shovel = register.registerItem("licorice_shovel", () -> new ShovelItem(CCTiers.LICORICE, 1.5F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_pickaxe = register.registerItem("licorice_pickaxe", () -> new PickaxeItem(CCTiers.LICORICE, 1, -2.8F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_axe = register.registerItem("licorice_axe", () -> new AxeItem(CCTiers.LICORICE, 7, -3.2F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_hoe = register.registerItem("licorice_hoe", () -> new HoeItem(CCTiers.LICORICE, -1, -2.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_helmet = register.registerItem("licorice_helmet", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_plate = register.registerItem("licorice_chestplate", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.CHEST, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_leggings = register.registerItem("licorice_leggings", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.LEGS, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> licorice_boots = register.registerItem("licorice_boots", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> honeycomb = register.registerSimpleItem("honeycomb", newDefaultItemProperties());
	public static final RegistryObject<Item> honey_shard = register.registerSimpleItem("honeycomb_shard", newDefaultItemProperties());
	public static final RegistryObject<Item> honeycomb_sword = register.registerItem("honeycomb_sword", () -> new SwordItem(CCTiers.HONEYCOMB, 3, -2.4F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_shovel = register.registerItem("honeycomb_shovel", () -> new ShovelItem(CCTiers.HONEYCOMB, 1.5F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_pickaxe = register.registerItem("honeycomb_pickaxe", () -> new PickaxeItem(CCTiers.HONEYCOMB, 1, -2.8F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_axe = register.registerItem("honeycomb_axe", () -> new AxeItem(CCTiers.HONEYCOMB, 7, -3.2F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_hoe = register.registerItem("honeycomb_hoe", () -> new HoeItem(CCTiers.HONEYCOMB, -1, -2.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_helmet = register.registerItem("honeycomb_helmet", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_plate = register.registerItem("honeycomb_chestplate", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.CHEST, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_leggings = register.registerItem("honeycomb_leggings", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.LEGS, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> honeycomb_boots = register.registerItem("honeycomb_boots", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> pez = register.registerSimpleItem("pez", "PEZ", newDefaultItemProperties());
	public static final RegistryObject<Item> pez_dust = register.registerSimpleItem("pez_dust", "PEZ Dust", newDefaultItemProperties());
	public static final RegistryObject<Item> pez_sword = register.registerItem("pez_sword", "PEZ Sword", () -> new SwordItem(CCTiers.PEZ, 3, -2.4F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_shovel = register.registerItem("pez_shovel", "PEZ Shovel", () -> new ShovelItem(CCTiers.PEZ, 1.5F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_pickaxe = register.registerItem("pez_pickaxe", "PEZ Pickaxe", () -> new PickaxeItem(CCTiers.PEZ, 1, -2.8F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_axe = register.registerItem("pez_axe", "PEZ Axe", () -> new AxeItem(CCTiers.PEZ, 5.0F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_hoe = register.registerItem("pez_hoe", "PEZ Hoe", () -> new HoeItem(CCTiers.PEZ, 5, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_helmet = register.registerItem("pez_helmet", "PEZ Helmet", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_plate = register.registerItem("pez_chestplate", "PEZ Chestplate", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.CHEST, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_leggings = register.registerItem("pez_leggings", "PEZ Leggings", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.LEGS, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> pez_boots = register.registerItem("pez_boots", "PEZ Boots", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> jawbreaker = register.registerSimpleItem("jawbreaker", newDefaultItemProperties());
	public static final RegistryObject<Item> jawbreaker_sword = register.registerItem("jawbreaker_sword", () -> new SwordItem(CCTiers.JAWBREAKER, 3, -2.4F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_shovel = register.registerItem("jawbreaker_shovel", () -> new ShovelItem(CCTiers.JAWBREAKER, 1.5F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_pickaxe = register.registerItem("jawbreaker_pickaxe", () -> new PickaxeItem(CCTiers.JAWBREAKER, 1, -2.8F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_axe = register.registerItem("jawbreaker_axe", () -> new AxeItem(CCTiers.JAWBREAKER, 5.0F, -3.0F, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_hoe = register.registerItem("jawbreaker_hoe", () -> new HoeItem(CCTiers.JAWBREAKER, -4, 0, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_helmet = register.registerItem("jawbreaker_helmet", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_plate = register.registerItem("jawbreaker_chestplate", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.CHEST, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_leggings = register.registerItem("jawbreaker_leggings", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.LEGS, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jawbreaker_boots = register.registerItem("jawbreaker_boots", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> waffle = register.registerSimpleItem("waffle", newDefaultItemProperties());
	public static final RegistryObject<Item> waffle_nugget = register.registerSimpleItem("waffle_nugget", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_fish = register.registerSimpleItem("cranberry_fish", "Cranfish", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_fish_cooked = register.registerSimpleItem("cranberry_fish_cooked", "Cooked Cranfish", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_scale = register.registerSimpleItem("cranberry_scale", newDefaultItemProperties());
	public static final RegistryObject<Item> sugar_crystal = register.registerSimpleItem("sugar_crystal", newDefaultItemProperties());
	public static final RegistryObject<Item> trampojelly_boots = register.registerItem("trampojelly_boots", () -> new ArmorItem(CCArmorMaterials.TrampoJellyBoots, EquipmentSlot.FEET, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> water_mask = register.registerItem("water_mask", WaterMask::new, SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> jelly_crown = register.registerItem("jelly_crown", "Jelly King's Crown", () -> new ArmorItem(CCArmorMaterials.JellyCrown, EquipmentSlot.HEAD, newDefaultItemProperties()), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	public static final RegistryObject<Item> record_1 = register.registerItem("record_1", "Jelly Queen's Secret Record", () -> new RecordItem(1, CCSounds.cd_1, newDefaultItemProperties().stacksTo(1)), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> record_2 = register.registerItem("record_2", "Suguard's Secret Record", () -> new RecordItem(2, CCSounds.cd_2, newDefaultItemProperties().stacksTo(1)), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> record_3 = register.registerItem("record_3", "Rainbow Record", () -> new RecordItem(3, CCSounds.cd_3, newDefaultItemProperties().stacksTo(1)), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	public static final RegistryObject<Item> record_4 = register.registerItem("record_4", "Licorice beetle's Secret Record", () -> new RecordItem(4, CCSounds.cd_4, newDefaultItemProperties().stacksTo(1)), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);

	static {
		DatagenHelper helper = CandyCraft.CORE_INSTANCE.getDatagenHelper();
		helper.langEn.put("item.candycraft.record_1.desc", "Caution & Crisis/C418 - Sweden (Caution & Crisis Remix)");
		helper.langEn.put("item.candycraft.record_2.desc", "Jakim - Every");
		helper.langEn.put("item.candycraft.record_3.desc", "Jean Jacques Perrey - Brazilian Flower");
		helper.langEn.put("item.candycraft.record_4.desc", "C418 - einfallslos");
	}

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
