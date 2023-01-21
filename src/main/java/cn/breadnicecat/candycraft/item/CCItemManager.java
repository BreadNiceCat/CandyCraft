package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.misc.CCArmorMaterials;
import cn.breadnicecat.candycraft.misc.CCItemTags;
import cn.breadnicecat.candycraft.misc.CCSoundEvents;
import cn.breadnicecat.candycraft.misc.CCTiers;
import net.minecraft.Util;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static cn.breadnicecat.candycraft.data.CCItemModelProvider.ItemModelGenerator.HANDHELD_GENERATOR;
import static cn.breadnicecat.candycraft.item.CCItemRegisterHelper.getBuilder;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:34
 */
@SuppressWarnings("unused")//how stupid tip : 100+ Warnings
public class CCItemManager {
    public static final RegistryObject<ItemDiary> diary = newSugeryItemBuilder("diary", () -> new ItemDiary(newDefaultItemProperties(1))).build();
    public static final RegistryObject<Item> marshmallow_stick = newSugeryItemBuilder("marshmallow_stick", newDefaultItemProperties()).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<SwordItem> marshmallow_sword = newSugeryItemBuilder("marshmallow_sword", () -> new SwordItem(CCTiers.MARSHMALLOW, 3, -2.4F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ShovelItem> marshmallow_shovel = newSugeryItemBuilder("marshmallow_shovel", () -> new ShovelItem(CCTiers.MARSHMALLOW, 1.5F, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<PickaxeItem> marshmallow_pickaxe = newSugeryItemBuilder("marshmallow_pickaxe", () -> new PickaxeItem(CCTiers.MARSHMALLOW, 1, -2.8F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<AxeItem> marshmallow_axe = newSugeryItemBuilder("marshmallow_axe", () -> new AxeItem(CCTiers.MARSHMALLOW, 6.0F, -3.2F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<HoeItem> marshmallow_hoe = newSugeryItemBuilder("marshmallow_hoe", () -> new HoeItem(CCTiers.MARSHMALLOW, 0, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();

    public static final RegistryObject<Item> licorice = newSugeryItemBuilder("licorice", newDefaultItemProperties()).tag(CCItemTags.LICORICE).build();
    public static final RegistryObject<SwordItem> licorice_sword = newSugeryItemBuilder("licorice_sword", () -> new SwordItem(CCTiers.LICORICE, 3, -2.4F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ShovelItem> licorice_shovel = newSugeryItemBuilder("licorice_shovel", () -> new ShovelItem(CCTiers.LICORICE, 1.5F, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<PickaxeItem> licorice_pickaxe = newSugeryItemBuilder("licorice_pickaxe", () -> new PickaxeItem(CCTiers.LICORICE, 1, -2.8F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<AxeItem> licorice_axe = newSugeryItemBuilder("licorice_axe", () -> new AxeItem(CCTiers.LICORICE, 7, -3.2F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<HoeItem> licorice_hoe = newSugeryItemBuilder("licorice_hoe", () -> new HoeItem(CCTiers.LICORICE, -1, -2.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ArmorItem> licorice_helmet = newSugeryItemBuilder("licorice_helmet", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.HEAD, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> licorice_chestplate = newSugeryItemBuilder("licorice_chestplate", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.CHEST, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> licorice_leggings = newSugeryItemBuilder("licorice_leggings", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.LEGS, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> licorice_boots = newSugeryItemBuilder("licorice_boots", () -> new ArmorItem(CCArmorMaterials.LICORICE, EquipmentSlot.FEET, newDefaultItemProperties())).build();

    public static final RegistryObject<Item> honeycomb = newSugeryItemBuilder("honeycomb", newDefaultItemProperties()).tag(CCItemTags.HONEYCOMB).build();
    public static final RegistryObject<Item> honey_shard = newSugeryItemBuilder("honeycomb_shard", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> honeycomb_bolt = newSugeryItemBuilder("honeycomb_bolt", newDefaultItemProperties()).build();
    public static final RegistryObject<ArrowItem> honeycomb_arrow = newSugeryItemBuilder("honeycomb_arrow", () -> new ArrowItem(newDefaultItemProperties())).build();
    public static final RegistryObject<SwordItem> honeycomb_sword = newSugeryItemBuilder("honeycomb_sword", () -> new SwordItem(CCTiers.HONEYCOMB, 3, -2.4F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ShovelItem> honeycomb_shovel = newSugeryItemBuilder("honeycomb_shovel", () -> new ShovelItem(CCTiers.HONEYCOMB, 1.5F, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<PickaxeItem> honeycomb_pickaxe = newSugeryItemBuilder("honeycomb_pickaxe", () -> new PickaxeItem(CCTiers.HONEYCOMB, 1, -2.8F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<AxeItem> honeycomb_axe = newSugeryItemBuilder("honeycomb_axe", () -> new AxeItem(CCTiers.HONEYCOMB, 7, -3.2F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<HoeItem> honeycomb_hoe = newSugeryItemBuilder("honeycomb_hoe", () -> new HoeItem(CCTiers.HONEYCOMB, -1, -2.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ArmorItem> honeycomb_helmet = newSugeryItemBuilder("honeycomb_helmet", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.HEAD, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> honeycomb_chestplate = newSugeryItemBuilder("honeycomb_chestplate", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.CHEST, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> honeycomb_leggings = newSugeryItemBuilder("honeycomb_leggings", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.LEGS, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> honeycomb_boots = newSugeryItemBuilder("honeycomb_boots", () -> new ArmorItem(CCArmorMaterials.HONEYCOMB, EquipmentSlot.FEET, newDefaultItemProperties())).build();

    public static final RegistryObject<Item> pez = newSugeryItemBuilder("pez", newDefaultItemProperties()).enName("PEZ").tag(CCItemTags.PEZ).build();
    public static final RegistryObject<Item> pez_dust = newSugeryItemBuilder("pez_dust", newDefaultItemProperties()).enName("PEZ Dust").build();
    public static final RegistryObject<SwordItem> pez_sword = newSugeryItemBuilder("pez_sword", () -> new SwordItem(CCTiers.PEZ, 3, -2.4F, newDefaultItemProperties())).enName("PEZ Sword").model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ShovelItem> pez_shovel = newSugeryItemBuilder("pez_shovel", () -> new ShovelItem(CCTiers.PEZ, 1.5F, -3.0F, newDefaultItemProperties())).enName("PEZ Shovel").model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<PickaxeItem> pez_pickaxe = newSugeryItemBuilder("pez_pickaxe", () -> new PickaxeItem(CCTiers.PEZ, 1, -2.8F, newDefaultItemProperties())).enName("PEZ Pickaxe").model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<AxeItem> pez_axe = newSugeryItemBuilder("pez_axe", () -> new AxeItem(CCTiers.PEZ, 5.0F, -3.0F, newDefaultItemProperties())).enName("PEZ Axe").model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<HoeItem> pez_hoe = newSugeryItemBuilder("pez_hoe", () -> new HoeItem(CCTiers.PEZ, 5, -3.0F, newDefaultItemProperties())).enName("PEZ Axe").model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ArmorItem> pez_helmet = newSugeryItemBuilder("pez_helmet", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.HEAD, newDefaultItemProperties())).enName("PEZ Helmet").build();
    public static final RegistryObject<ArmorItem> pez_chestplate = newSugeryItemBuilder("pez_chestplate", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.CHEST, newDefaultItemProperties())).enName("PEZ Chestplate").build();
    public static final RegistryObject<ArmorItem> pez_leggings = newSugeryItemBuilder("pez_leggings", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.LEGS, newDefaultItemProperties())).enName("PEZ Leggings").build();
    public static final RegistryObject<ArmorItem> pez_boots = newSugeryItemBuilder("pez_boots", () -> new ArmorItem(CCArmorMaterials.PEZ, EquipmentSlot.FEET, newDefaultItemProperties())).enName("PEZ Boots").build();
    public static final RegistryObject<ItemFork> fork = newSugeryItemBuilder("fork", () -> new ItemFork(newDefaultItemProperties().durability(128))).model(HANDHELD_GENERATOR).build();

    public static final RegistryObject<Item> jawbreaker = newSugeryItemBuilder("jawbreaker", newDefaultItemProperties()).tag(CCItemTags.JAWBREAKER).build();
    public static final RegistryObject<SwordItem> jawbreaker_sword = newSugeryItemBuilder("jawbreaker_sword", () -> new SwordItem(CCTiers.JAWBREAKER, 3, -2.4F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ShovelItem> jawbreaker_shovel = newSugeryItemBuilder("jawbreaker_shovel", () -> new ShovelItem(CCTiers.JAWBREAKER, 1.5F, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<PickaxeItem> jawbreaker_pickaxe = newSugeryItemBuilder("jawbreaker_pickaxe", () -> new PickaxeItem(CCTiers.JAWBREAKER, 1, -2.8F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<AxeItem> jawbreaker_axe = newSugeryItemBuilder("jawbreaker_axe", () -> new AxeItem(CCTiers.JAWBREAKER, 5.0F, -3.0F, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<HoeItem> jawbreaker_hoe = newSugeryItemBuilder("jawbreaker_hoe", () -> new HoeItem(CCTiers.JAWBREAKER, -4, 0, newDefaultItemProperties())).model(HANDHELD_GENERATOR).build();
    public static final RegistryObject<ArmorItem> jawbreaker_helmet = newSugeryItemBuilder("jawbreaker_helmet", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.HEAD, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> jawbreaker_chestplate = newSugeryItemBuilder("jawbreaker_chestplate", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.CHEST, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> jawbreaker_leggings = newSugeryItemBuilder("jawbreaker_leggings", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.LEGS, newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> jawbreaker_boots = newSugeryItemBuilder("jawbreaker_boots", () -> new ArmorItem(CCArmorMaterials.JAWBREAKER, EquipmentSlot.FEET, newDefaultItemProperties())).build();

    public static final RegistryObject<Item> sugar_crystal = newSugeryItemBuilder("sugar_crystal", newDefaultItemProperties()).build();
    public static final RegistryObject<ArmorItem> trampojelly_boots = newSugeryItemBuilder("trampojelly_boots", () -> new ArmorItem(CCArmorMaterials.TRAMPOJELLY_BOOTS, EquipmentSlot.FEET, newDefaultItemProperties())).build();
    public static final RegistryObject<ItemWaterMask> water_mask = newSugeryItemBuilder("water_mask", () -> new ItemWaterMask(newDefaultItemProperties())).build();
    public static final RegistryObject<ArmorItem> jelly_crown = newSugeryItemBuilder("jelly_crown", () -> new ArmorItem(CCArmorMaterials.JELLY_CROWN, EquipmentSlot.HEAD, newDefaultItemProperties())).enName("Jelly King's Crown").tag(CCItemTags.RETURN_TICKET).build();

    public static final RegistryObject<Item> cotton_candy = newSugeryItemBuilder("cotton_candy", newDefaultItemProperties()).build();
    public static final RegistryObject<BlockItem> dragibus = newSugeryItemBuilder("dragibus", () -> new BlockItem(CCBlockManager.dragibus_crops.block().get(), newDefaultItemProperties())).enName(null).build();
    public static final RegistryObject<? extends BlockItem> lollipop_seeds = newSugeryItemBuilder("lollipop_seeds", () -> new BlockItem(CCBlockManager.lollipop_stem.block().get(), newDefaultItemProperties()) {
        private String descriptionId;

        @Override
        public @NotNull String getDescriptionId() {
            return descriptionId == null ? descriptionId = Util.makeDescriptionId("item", getRegistryName()) : descriptionId;
        }
    }).build();
    public static final RegistryObject<Item> lollipop = newSugeryItemBuilder("lollipop", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> gummy = newSugeryItemBuilder("gummy", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> hot_gummy = newSugeryItemBuilder("hot_gummy", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> chocolate_coin = newSugeryItemBuilder("chocolate_coin", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> nougat_powder = newSugeryItemBuilder("nougat_powder", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> waffle = newSugeryItemBuilder("waffle", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> waffle_nugget = newSugeryItemBuilder("waffle_nugget", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> cranberry_fish = newSugeryItemBuilder("cranberry_fish", newDefaultItemProperties()).enName("Cranfish").build();
    public static final RegistryObject<Item> cranberry_fish_cooked = newSugeryItemBuilder("cranberry_fish_cooked", newDefaultItemProperties()).enName("Cooked Cranfish").build();
    public static final RegistryObject<Item> cranberry_scale = newSugeryItemBuilder("cranberry_scale", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> candied_cherry = newSugeryItemBuilder("candied_cherry", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> candy_cane = newSugeryItemBuilder("candy_cane", newDefaultItemProperties()).build();
    public static final RegistryObject<Item> chewing_gum = newSugeryItemBuilder("chewing_gum", newDefaultItemProperties()).build();

    public static final RegistryObject<ItemEmblem> nessie_emblem = newSugeryItemBuilder("nessie_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> suguard_emblem = newSugeryItemBuilder("suguard_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> gingerbread_emblem = newSugeryItemBuilder("gingerbread_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> jelly_emblem = newSugeryItemBuilder("jelly_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> sky_emblem = newSugeryItemBuilder("sky_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> chewing_gum_emblem = newSugeryItemBuilder("chewing_gum_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> honeycomb_emblem = newSugeryItemBuilder("honeycomb_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();
    public static final RegistryObject<ItemEmblem> cranberry_emblem = newSugeryItemBuilder("cranberry_emblem", () -> new ItemEmblem(newDefaultItemProperties(1))).build();

    public static final RegistryObject<Item> jelly_dungeon_key = newSugeryItemBuilder("jelly_dungeon_key", newDefaultItemProperties().rarity(Rarity.RARE)).build();
    public static final RegistryObject<Item> jelly_sentry_key = newSugeryItemBuilder("jelly_sentry_key", newDefaultItemProperties().rarity(Rarity.UNCOMMON)).build();
    public static final RegistryObject<Item> jelly_boss_key = newSugeryItemBuilder("jelly_boss_key", newDefaultItemProperties().rarity(Rarity.UNCOMMON)).build();
    public static final RegistryObject<Item> beetle_dungeon_key = newSugeryItemBuilder("beetle_dungeon_key", newDefaultItemProperties().rarity(Rarity.RARE)).build();
    public static final RegistryObject<Item> sky_dungeon_key = newSugeryItemBuilder("sky_dungeon_key", newDefaultItemProperties().rarity(Rarity.RARE)).build();
    public static final RegistryObject<Item> suguard_dungeon_key = newSugeryItemBuilder("suguard_dungeon_key", newDefaultItemProperties().rarity(Rarity.RARE)).build();

    public static final RegistryObject<RecordItem> record_1 = newSugeryItemBuilder("record_1", () -> new RecordItem(1, CCSoundEvents.cd_1, newDefaultItemProperties(1))).enName("Jelly Queen's Secret Record").tag(ItemTags.MUSIC_DISCS).build();
    public static final RegistryObject<RecordItem> record_2 = newSugeryItemBuilder("record_2", () -> new RecordItem(2, CCSoundEvents.cd_2, newDefaultItemProperties(1))).enName("Suguard's Secret Record").tag(ItemTags.MUSIC_DISCS).build();
    public static final RegistryObject<RecordItem> record_3 = newSugeryItemBuilder("record_3", () -> new RecordItem(3, CCSoundEvents.cd_3, newDefaultItemProperties(1))).enName("Rainbow Record").tag(ItemTags.MUSIC_DISCS).build();
    public static final RegistryObject<RecordItem> record_4 = newSugeryItemBuilder("record_4", () -> new RecordItem(4, CCSoundEvents.cd_4, newDefaultItemProperties(1))).enName("Licorice beetle's Secret Record").tag(ItemTags.MUSIC_DISCS).build();
    public static final RegistryObject<RecordItem> record_5 = newSugeryItemBuilder("record_5", () -> new RecordItem(5, CCSoundEvents.cd_5, newDefaultItemProperties(1))).enName("Strange Record").tag(ItemTags.MUSIC_DISCS).build();

    static {
        CandyCraft.clockIn();
        CCDatagenManager.langEn.put("item.candycraft.emblem.desc", "Get 20 different emblems to begin the end of the world.");
        CCDatagenManager.langEn.put("item.candycraft.record_1.desc", "Caution & Crisis/C418 - Sweden (Caution & Crisis Remix)");
        CCDatagenManager.langEn.put("item.candycraft.record_2.desc", "Jakim - Every");
        CCDatagenManager.langEn.put("item.candycraft.record_3.desc", "Jean Jacques Perrey - Brazilian Flower");
        CCDatagenManager.langEn.put("item.candycraft.record_4.desc", "C418 - einfallslos");
        CCDatagenManager.langEn.put("item.candycraft.record_5.desc", "Rick Astley - Never Gonna Give You Up");
    }

    public static void init() {
    }

    public static <I extends Item> CCItemRegisterHelper.Builder<I> newSugeryItemBuilder(String name, Supplier<I> item) {
        return getBuilder(name, item).tag(CCItemTags.SUGARY);
    }

    public static CCItemRegisterHelper.Builder<Item> newSugeryItemBuilder(String name, Item.Properties prop) {
        return getBuilder(name, prop).tag(CCItemTags.SUGARY);
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
        return newDefaultItemProperties().stacksTo(maxStackSize);
    }

}
