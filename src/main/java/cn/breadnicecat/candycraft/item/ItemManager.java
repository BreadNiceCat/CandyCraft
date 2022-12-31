package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.misc.CCArmorMaterials;
import cn.breadnicecat.candycraft.misc.CCSounds;
import cn.breadnicecat.candycraft.misc.CCTiers;
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
@SuppressWarnings("unused")//how stupid tip : 100+ Warnings
public class ItemManager {
	private static final ItemRegisterHelper helper = CandyCraft.CORE_INSTANCE.getItemRegisterHelper();
	public static final RegistryObject<Item> diary = helper.registerItem("diary", Diary::new);
	public static final RegistryObject<Item> marshmallow_stick = helper.registerSimpleItem("marshmallow_stick", newDefaultItemProperties());
	public static final RegistryObject<Item> marshmallow_sword = helper.registerItem("marshmallow_sword", () -> new SwordItem(CCTiers.MARSHMALLOW, 3, -2.4F, newDefaultItemProperties()));
	public static final RegistryObject<Item> marshmallow_shovel = helper.registerItem("marshmallow_shovel", () -> new ShovelItem(CCTiers.MARSHMALLOW, 1.5F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> marshmallow_pickaxe = helper.registerItem("marshmallow_pickaxe", () -> new PickaxeItem(CCTiers.MARSHMALLOW, 1, -2.8F, newDefaultItemProperties()));
	public static final RegistryObject<Item> marshmallow_axe = helper.registerItem("marshmallow_axe", () -> new AxeItem(CCTiers.MARSHMALLOW, 6.0F, -3.2F, newDefaultItemProperties()));
	public static final RegistryObject<Item> marshmallow_hoe = helper.registerItem("marshmallow_hoe", () -> new HoeItem(CCTiers.MARSHMALLOW, 0, -3.0F, newDefaultItemProperties()));

	public static final RegistryObject<Item> licorice = helper.registerSimpleItem("licorice", newDefaultItemProperties());
	public static final RegistryObject<Item> licorice_sword = helper.registerItem("licorice_sword", () -> new SwordItem(CCTiers.LICORICE, 3, -2.4F, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_shovel = helper.registerItem("licorice_shovel", () -> new ShovelItem(CCTiers.LICORICE, 1.5F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_pickaxe = helper.registerItem("licorice_pickaxe", () -> new PickaxeItem(CCTiers.LICORICE, 1, -2.8F, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_axe = helper.registerItem("licorice_axe", () -> new AxeItem(CCTiers.LICORICE, 7, -3.2F, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_hoe = helper.registerItem("licorice_hoe", () -> new HoeItem(CCTiers.LICORICE, -1, -2.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_helmet = helper.registerItem("licorice_helmet", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.HEAD, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_plate = helper.registerItem("licorice_chestplate", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.CHEST, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_leggings = helper.registerItem("licorice_leggings", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.LEGS, newDefaultItemProperties()));
	public static final RegistryObject<Item> licorice_boots = helper.registerItem("licorice_boots", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.FEET, newDefaultItemProperties()));

	public static final RegistryObject<Item> honeycomb = helper.registerSimpleItem("honeycomb", newDefaultItemProperties());
	public static final RegistryObject<Item> honey_shard = helper.registerSimpleItem("honeycomb_shard", newDefaultItemProperties());
	public static final RegistryObject<Item> honeycomb_bolt = helper.registerSimpleItem("honeycomb_bolt", newDefaultItemProperties());
	public static final RegistryObject<Item> honeycomb_arrow = helper.registerSimpleItem("honeycomb_arrow", newDefaultItemProperties());
	public static final RegistryObject<Item> honeycomb_sword = helper.registerItem("honeycomb_sword", () -> new SwordItem(CCTiers.HONEYCOMB, 3, -2.4F, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_shovel = helper.registerItem("honeycomb_shovel", () -> new ShovelItem(CCTiers.HONEYCOMB, 1.5F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_pickaxe = helper.registerItem("honeycomb_pickaxe", () -> new PickaxeItem(CCTiers.HONEYCOMB, 1, -2.8F, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_axe = helper.registerItem("honeycomb_axe", () -> new AxeItem(CCTiers.HONEYCOMB, 7, -3.2F, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_hoe = helper.registerItem("honeycomb_hoe", () -> new HoeItem(CCTiers.HONEYCOMB, -1, -2.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_helmet = helper.registerItem("honeycomb_helmet", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.HEAD, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_plate = helper.registerItem("honeycomb_chestplate", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.CHEST, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_leggings = helper.registerItem("honeycomb_leggings", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.LEGS, newDefaultItemProperties()));
	public static final RegistryObject<Item> honeycomb_boots = helper.registerItem("honeycomb_boots", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.FEET, newDefaultItemProperties()));

	public static final RegistryObject<Item> pez = helper.registerSimpleItem("pez", "PEZ", newDefaultItemProperties());
	public static final RegistryObject<Item> pez_dust = helper.registerSimpleItem("pez_dust", "PEZ Dust", newDefaultItemProperties());
	public static final RegistryObject<Item> pez_sword = helper.registerItem("pez_sword", "PEZ Sword", () -> new SwordItem(CCTiers.PEZ, 3, -2.4F, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_shovel = helper.registerItem("pez_shovel", "PEZ Shovel", () -> new ShovelItem(CCTiers.PEZ, 1.5F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_pickaxe = helper.registerItem("pez_pickaxe", "PEZ Pickaxe", () -> new PickaxeItem(CCTiers.PEZ, 1, -2.8F, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_axe = helper.registerItem("pez_axe", "PEZ Axe", () -> new AxeItem(CCTiers.PEZ, 5.0F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_hoe = helper.registerItem("pez_hoe", "PEZ Hoe", () -> new HoeItem(CCTiers.PEZ, 5, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_helmet = helper.registerItem("pez_helmet", "PEZ Helmet", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.HEAD, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_plate = helper.registerItem("pez_chestplate", "PEZ Chestplate", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.CHEST, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_leggings = helper.registerItem("pez_leggings", "PEZ Leggings", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.LEGS, newDefaultItemProperties()));
	public static final RegistryObject<Item> pez_boots = helper.registerItem("pez_boots", "PEZ Boots", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.FEET, newDefaultItemProperties()));
	public static final RegistryObject<Item> fork = helper.registerItem("fork", Fork::new);

	public static final RegistryObject<Item> jawbreaker = helper.registerSimpleItem("jawbreaker", newDefaultItemProperties());
	public static final RegistryObject<Item> jawbreaker_sword = helper.registerItem("jawbreaker_sword", () -> new SwordItem(CCTiers.JAWBREAKER, 3, -2.4F, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_shovel = helper.registerItem("jawbreaker_shovel", () -> new ShovelItem(CCTiers.JAWBREAKER, 1.5F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_pickaxe = helper.registerItem("jawbreaker_pickaxe", () -> new PickaxeItem(CCTiers.JAWBREAKER, 1, -2.8F, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_axe = helper.registerItem("jawbreaker_axe", () -> new AxeItem(CCTiers.JAWBREAKER, 5.0F, -3.0F, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_hoe = helper.registerItem("jawbreaker_hoe", () -> new HoeItem(CCTiers.JAWBREAKER, -4, 0, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_helmet = helper.registerItem("jawbreaker_helmet", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.HEAD, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_plate = helper.registerItem("jawbreaker_chestplate", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.CHEST, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_leggings = helper.registerItem("jawbreaker_leggings", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.LEGS, newDefaultItemProperties()));
	public static final RegistryObject<Item> jawbreaker_boots = helper.registerItem("jawbreaker_boots", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.FEET, newDefaultItemProperties()));

	public static final RegistryObject<Item> sugar_crystal = helper.registerSimpleItem("sugar_crystal", newDefaultItemProperties());
	public static final RegistryObject<Item> trampojelly_boots = helper.registerItem("trampojelly_boots", () -> new ArmorItem(CCArmorMaterials.TrampoJellyBoots, EquipmentSlot.FEET, newDefaultItemProperties()));
	public static final RegistryObject<Item> water_mask = helper.registerItem("water_mask", WaterMask::new);
	public static final RegistryObject<Item> jelly_crown = helper.registerItem("jelly_crown", "Jelly King's Crown", () -> new ArmorItem(CCArmorMaterials.JellyCrown, EquipmentSlot.HEAD, newDefaultItemProperties()));

	public static final RegistryObject<Item> cotton_candy = helper.registerSimpleItem("cotton_candy", newDefaultItemProperties());
	public static final RegistryObject<Item> dragibus = helper.registerSimpleItem("dragibus", newDefaultItemProperties());
	public static final RegistryObject<Item> gummy = helper.registerSimpleItem("gummy", newDefaultItemProperties());
	public static final RegistryObject<Item> hot_gummy = helper.registerSimpleItem("hot_gummy", newDefaultItemProperties());
	public static final RegistryObject<Item> chocolate_coin = helper.registerSimpleItem("chocolate_coin", newDefaultItemProperties());
	public static final RegistryObject<Item> nougat_powder = helper.registerSimpleItem("nougat_powder", newDefaultItemProperties());
	public static final RegistryObject<Item> waffle = helper.registerSimpleItem("waffle", newDefaultItemProperties());
	public static final RegistryObject<Item> waffle_nugget = helper.registerSimpleItem("waffle_nugget", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_fish = helper.registerSimpleItem("cranberry_fish", "Cranfish", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_fish_cooked = helper.registerSimpleItem("cranberry_fish_cooked", "Cooked Cranfish", newDefaultItemProperties());
	public static final RegistryObject<Item> cranberry_scale = helper.registerSimpleItem("cranberry_scale", newDefaultItemProperties());
	public static final RegistryObject<Item> candied_cherry = helper.registerSimpleItem("candied_cherry", newDefaultItemProperties());
	public static final RegistryObject<Item> candy_cane = helper.registerSimpleItem("candy_cane", newDefaultItemProperties());
	public static final RegistryObject<Item> chewing_gum = helper.registerSimpleItem("chewing_gum", newDefaultItemProperties());

	public static final RegistryObject<Item> nessie_emblem = helper.registerItem("nessie_emblem", Emblem::new);
	public static final RegistryObject<Item> suguard_emblem = helper.registerItem("suguard_emblem", Emblem::new);
	public static final RegistryObject<Item> gingerbread_emblem = helper.registerItem("gingerbread_emblem", Emblem::new);
	public static final RegistryObject<Item> jelly_emblem = helper.registerItem("jelly_emblem", Emblem::new);
	public static final RegistryObject<Item> sky_emblem = helper.registerItem("sky_emblem", Emblem::new);
	public static final RegistryObject<Item> chewing_gum_emblem = helper.registerItem("chewing_gum_emblem", Emblem::new);
	public static final RegistryObject<Item> honeycomb_emblem = helper.registerItem("honeycomb_emblem", Emblem::new);
	public static final RegistryObject<Item> cranberry_emblem = helper.registerItem("cranberry_emblem", Emblem::new);

	public static final RegistryObject<Item> jelly_key = helper.registerSimpleItem("jelly_key", newDefaultItemProperties());
	public static final RegistryObject<Item> jelly_sentry_key = helper.registerSimpleItem("jelly_sentry_key", newDefaultItemProperties());
	public static final RegistryObject<Item> jelly_boss_key = helper.registerSimpleItem("jelly_boss_key", newDefaultItemProperties());
	public static final RegistryObject<Item> beetle_key = helper.registerSimpleItem("beetle_key", newDefaultItemProperties());
	public static final RegistryObject<Item> sky_key = helper.registerSimpleItem("sky_key", newDefaultItemProperties());
	public static final RegistryObject<Item> suguard_key = helper.registerSimpleItem("suguard_key", newDefaultItemProperties());

	public static final RegistryObject<Item> record_1 = helper.registerItem("record_1", "Jelly Queen's Secret Record", () -> new RecordItem(1, CCSounds.cd_1, newDefaultItemProperties(1)));
	public static final RegistryObject<Item> record_2 = helper.registerItem("record_2", "Suguard's Secret Record", () -> new RecordItem(2, CCSounds.cd_2, newDefaultItemProperties(1)));
	public static final RegistryObject<Item> record_3 = helper.registerItem("record_3", "Rainbow Record", () -> new RecordItem(3, CCSounds.cd_3, newDefaultItemProperties(1)));
	public static final RegistryObject<Item> record_4 = helper.registerItem("record_4", "Licorice beetle's Secret Record", () -> new RecordItem(4, CCSounds.cd_4, newDefaultItemProperties(1)));

	static {
		DatagenHelper langhelper = CandyCraft.CORE_INSTANCE.getDatagenHelper();
		langhelper.langEn.put("item.candycraft.emblem.desc", "Get 20 different emblems to begin the end of the world. ");
		langhelper.langEn.put("item.candycraft.record_1.desc", "Caution & Crisis/C418 - Sweden (Caution & Crisis Remix)");
		langhelper.langEn.put("item.candycraft.record_2.desc", "Jakim - Every");
		langhelper.langEn.put("item.candycraft.record_3.desc", "Jean Jacques Perrey - Brazilian Flower");
		langhelper.langEn.put("item.candycraft.record_4.desc", "C418 - einfallslos");
	}

	public static void init() {
	}

	/**
	 * @param nutrition     饱和度
	 * @param saturationMod 饱食度
	 */
	public static FoodProperties.Builder newFoodProperties(int nutrition, float saturationMod) {
		return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod);
	}

	public static Item.Properties newDefaultItemProperties() {
		return new Item.Properties().tab(CandyCraft.TAB);
	}

	public static Item.Properties newDefaultItemProperties(int maxStackSize) {
		return new Item.Properties().tab(CandyCraft.TAB).stacksTo(maxStackSize);
	}
}
