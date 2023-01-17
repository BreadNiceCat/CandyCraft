package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCBlockLoot;
import cn.breadnicecat.candycraft.data.CCBlockStateProvider;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import cn.breadnicecat.candycraft.misc.CCSoundTypes;
import cn.breadnicecat.candycraft.utils.LambdaUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static cn.breadnicecat.candycraft.block.CCBlockRegisterHelper.getBuilder;
import static cn.breadnicecat.candycraft.data.CCBlockLoot.CCBlockLootFunction.DROP_SELF_1_NEED_SILK_TOUCH;
import static cn.breadnicecat.candycraft.data.CCBlockStateProvider.BlockStateModelGenerator.*;
import static cn.breadnicecat.candycraft.item.CCItemManager.newDefaultItemProperties;
import static cn.breadnicecat.candycraft.misc.CCBlockTags.*;
import static cn.breadnicecat.candycraft.misc.CCItemTags.CANDY_PLANK;
import static net.minecraft.tags.BlockTags.*;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 14:00
 * 变量名不遵守大驼峰：我也不想啊,但是大驼峰太麻烦了,后面的name不能用CV大法了
 */
@SuppressWarnings("unused")//Stupid Warning
public class CCBlockManager {
	@SuppressWarnings("deprecation")
	private static final CCBlockStateProvider.BlockStateModelGenerator CARAMEL_GLASS_AND_GLASS_PANE_GENERATOR = (provider, glassName, glassBlock) -> {
		//注册玻璃
		provider.simpleBlock(glassBlock);
		provider.simpleBlockItem(glassBlock, provider.existModelFile(glassBlock));
		//注册玻璃板
		String glassPaneName = glassName + "_pane";
		IronBarsBlock glassPaneBlock = (IronBarsBlock) Registry.BLOCK.get(provider.modLoc(glassPaneName));
		provider.paneBlock(glassPaneBlock, provider.blockTexture(glassBlock), provider.modLoc("block/caramel_glass_pane_edge"));
		provider.itemModels().withExistingParent(glassPaneName, "item/generated")
				.texture("layer0", provider.blockTexture(glassBlock));
	};
	//============================
	//机器
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockMarshmallowCraftingTable> marshmallow_crafting_table = newSugaryBuilder("marshmallow_crafting_table", () -> new BlockMarshmallowCraftingTable(newBlockProp(Material.WOOD).strength(2.5F))).model(COLUMN_GENERATOR).blockTag(MINEABLE_WITH_AXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockLicoriceFurnace> licorice_furnace = newSugaryBuilder("licorice_furnace", () -> new BlockLicoriceFurnace(newBlockProp(Material.STONE).requiresCorrectToolForDrops().strength(3.5f).lightLevel(litBlockEmission(13)))).model((provider, name, block) -> {
		ResourceLocation top = provider.modLoc("block/licorice_block");
		ResourceLocation side = provider.modLoc("block/licorice_furnace_side");
		BlockModelBuilder off = provider.models().orientable(name + "_off", side, provider.modLoc("block/licorice_furnace_front_off"), top);
		BlockModelBuilder on = provider.models().orientable(name + "_on", side, provider.modLoc("block/licorice_furnace_front_on"), top);
		provider.horizontalBlock(block, (state) -> state.getValue(AbstractFurnaceBlock.LIT) ? on : off);
		provider.simpleBlockItem(block, off);
	}).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockSugerFactory> sugar_factory = newSugaryBuilder("sugar_factory", () -> new BlockSugerFactory(CCBlockManager.newBlockProp().requiresCorrectToolForDrops().strength(1.5F, 6F))).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockAdvancedSugarFactory> advanced_sugar_factory = newSugaryBuilder("advanced_sugar_factory", () -> new BlockAdvancedSugarFactory(CCBlockManager.newBlockProp().requiresCorrectToolForDrops().strength(1.5F, 6F))).blockTag(MINEABLE_WITH_PICKAXE).build();
	//玻璃
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> honeycomb_lamp = newSugaryBuilder("honeycomb_lamp", BlockBehaviour.Properties.of(Material.GLASS).strength(1F).sound(SoundType.GLASS).lightLevel($ -> 14)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<GlassBlock> caramel_glass = newSugaryBuilder("caramel_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(LambdaUtils::never).isRedstoneConductor(LambdaUtils::never).isSuffocating(LambdaUtils::never).isViewBlocking(LambdaUtils::never))).model(CARAMEL_GLASS_AND_GLASS_PANE_GENERATOR).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<GlassBlock> round_caramel_glass = newSugaryBuilder("round_caramel_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(LambdaUtils::never).isRedstoneConductor(LambdaUtils::never).isSuffocating(LambdaUtils::never).isViewBlocking(LambdaUtils::never))).model(CARAMEL_GLASS_AND_GLASS_PANE_GENERATOR).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<GlassBlock> diamond_caramel_glass = newSugaryBuilder("diamond_caramel_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(LambdaUtils::never).isRedstoneConductor(LambdaUtils::never).isSuffocating(LambdaUtils::never).isViewBlocking(LambdaUtils::never))).model(CARAMEL_GLASS_AND_GLASS_PANE_GENERATOR).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<IronBarsBlock> caramel_glass_pane = newSugaryBuilder("caramel_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion())).model(null).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<IronBarsBlock> round_caramel_glass_pane = newSugaryBuilder("round_caramel_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion())).model(null).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<IronBarsBlock> diamond_caramel_glass_pane = newSugaryBuilder("diamond_caramel_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion())).model(null).loot(DROP_SELF_1_NEED_SILK_TOUCH).build();
	//树相关
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockCandySapling> chocolate_sapling = simpleCandySaplingBlock("chocolate_sapling", new OakTreeGrower()).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockCandySapling> caramel_sapling = simpleCandySaplingBlock("caramel_sapling", new OakTreeGrower()).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockCandySapling> white_chocolate_sapling = simpleCandySaplingBlock("white_chocolate_sapling", new OakTreeGrower()).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockCandySapling> candied_cherry_sapling = simpleCandySaplingBlock("candied_cherry_sapling", new OakTreeGrower()).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<RotatedPillarBlock> marshmallow_log = simpleCandyLogBlock("marshmallow_log").build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<TorchBlock> honeycomb_torch = newSugaryBuilder("honeycomb_torch", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noCollission().sound(SoundType.WOOD).lightLevel($ -> 14), ParticleTypes.FLAME)).blockItem((torch) -> new StandingAndWallBlockItem(torch, CCBlockManager.wall_honeycomb_torch.block().get(), newDefaultItemProperties())).model(TORCH_GENERATOR).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<RotatedPillarBlock> light_marshmallow_log = simpleCandyLogBlock("light_marshmallow_log").build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<RotatedPillarBlock> dark_marshmallow_log = simpleCandyLogBlock("dark_marshmallow_log").build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> marshmallow_planks = newSugaryBuilder("marshmallow_planks", newBlockProp(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).itemBlockTag(CANDY_PLANK).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> light_marshmallow_planks = newSugaryBuilder("light_marshmallow_planks", newBlockProp(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).itemBlockTag(CANDY_PLANK).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> dark_marshmallow_planks = newSugaryBuilder("dark_marshmallow_planks", newBlockProp(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).itemBlockTag(CANDY_PLANK).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<LeavesBlock> caramel_leaves = simpleCandyLeavesBlock("caramel_leaves").loot((b, l) -> l.tableLeavesDrops(b, caramel_sapling.block().get(), CCItemManager.marshmallow_stick.get(), CCBlockLoot.NORMAL_LEAVES_SAPLING_CHANCES)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<LeavesBlock> white_chocolate_leaves = simpleCandyLeavesBlock("white_chocolate_leaves").loot((b, l) -> l.tableLeavesDrops(b, white_chocolate_sapling.block().get(), CCItemManager.marshmallow_stick.get(), CCBlockLoot.NORMAL_LEAVES_SAPLING_CHANCES)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<LeavesBlock> candied_cherry_leaves = simpleCandyLeavesBlock("candied_cherry_leaves").loot((b, l) -> l.tableLeavesDrops(b, caramel_sapling.block().get(), CCItemManager.marshmallow_stick.get(), CCBlockLoot.NORMAL_LEAVES_SAPLING_CHANCES)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<LeavesBlock> magic_leaves = simpleCandyLeavesBlock("magic_leaves").loot((b, l) -> l.tableSilkTouchOrShearsDispatch(b, LootItem.lootTableItem(Items.SUGAR))).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<DoorBlock> marshmallow_door = newSugaryBuilder("marshmallow_door", () -> new DoorBlock(newBlockProp(Material.WOOD).sound(SoundType.WOOD).strength(3.0F).noOcclusion())).model(DOOR_GENERATOR).blockTag(MINEABLE_WITH_AXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<LeavesBlock> chocolate_leaves = simpleCandyLeavesBlock("chocolate_leaves").build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<WallTorchBlock> wall_honeycomb_torch = newSugaryBuilder("wall_honeycomb_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).lightLevel($ -> 14).lootFrom(honeycomb_torch.block()), ParticleTypes.FLAME)).blockItem((Function<WallTorchBlock, BlockItem>) null).enLang(null).model(null).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockCandylandPortal> candyland_portal = getBuilder("candyland_portal", () -> new BlockCandylandPortal(CCBlockManager.newBlockProp(Material.PORTAL).sound(SoundType.GLASS).noCollission().strength(-1.0f).lightLevel((s) -> 11))).enLang("Caramel portal").model(PORTAL_GENERATOR).blockTag(BlockTags.PORTALS).build();
	//矿物方块
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> chocolate_stone = newSugaryBuilder("chocolate_stone", newBlockProp(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6F)).blockTag(MINEABLE_WITH_PICKAXE).loot((b, l) -> l.tableSilkTouchDispatch(b, CCBlockManager.chocolate_cobblestone.block().get())).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> chocolate_cobblestone = newSugaryBuilder("chocolate_cobblestone", newBlockProp(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6F)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockSugerBlock> sugar_block = newSugaryBuilder("sugar_block", () -> new BlockSugerBlock(newBlockProp(Material.SAND).strength(0.5F).sound(SoundType.SAND))).blockTag(CANDYLAND_PORTAL_FRAME, MINEABLE_WITH_SHOVEL).loot((b, l) -> l.tableSingle(Items.SUGAR, ConstantValue.exactly(4))).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockSugerBlock> caramel_block = newSugaryBuilder("caramel_block", () -> new BlockSugerBlock(newBlockProp().strength(1F).requiresCorrectToolForDrops())).blockTag(CANDYLAND_PORTAL_FRAME, MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> candy_cane_block = newSugaryBuilder("candy_cane_block", newBlockProp().requiresCorrectToolForDrops().strength(1.5F, 6F)).model(COLUMN_GENERATOR).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<OreBlock> jelly_ore = newSugaryBuilder("jelly_ore", () -> new OreBlock(newBlockProp().strength(4F, 3F).requiresCorrectToolForDrops())).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockTrampojelly> trampojelly = newSugaryBuilder("trampojelly", () -> new BlockTrampojelly(newBlockProp(Material.SAND).sound(CCSoundTypes.JELLY).strength(8F, 3F).noOcclusion(), 1.5F, 0.2F, true)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockTrampojelly> red_trampojelly = newSugaryBuilder("red_trampojelly", () -> new BlockTrampojelly(newBlockProp(Material.SAND).sound(CCSoundTypes.JELLY).strength(8F, 3F).noOcclusion(), 2.5F, 0.1F, true)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockTrampojelly> purple_trampojelly = newSugaryBuilder("purple_trampojelly", () -> new BlockTrampojelly(newBlockProp(Material.SAND).sound(CCSoundTypes.JELLY).strength(8F, 3F).noOcclusion(), 1.5F, 0F, false)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockTrampojelly> jelly_shock_absorber = newSugaryBuilder("jelly_shock_absorber", () -> new BlockTrampojelly(newBlockProp(Material.SAND).sound(CCSoundTypes.JELLY).strength(8F, 3F).noOcclusion(), 0F, 0F, false)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<OreBlock> licorice_ore = newSugaryBuilder("licorice_ore", () -> new OreBlock(newBlockProp().strength(2F).requiresCorrectToolForDrops())).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> licorice_block = newSugaryBuilder("licorice_block", newBlockProp().requiresCorrectToolForDrops().strength(2F)).blockTag(MINEABLE_WITH_PICKAXE).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<OreBlock> nougat_ore = newSugaryBuilder("nougat_ore", () -> new OreBlock(newBlockProp().strength(2.8F, 2F).requiresCorrectToolForDrops())).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL).loot((b, l) -> l.tableSingle(b, UniformGenerator.between(2f, 5f))).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> nougat_block = newSugaryBuilder("nougat_block", newBlockProp().strength(2F).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> nougat_head = newSugaryBuilder("nougat_head", newBlockProp().strength(2.5F).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_DIAMOND_TOOL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<OreBlock> honeycomb_ore = newSugaryBuilder("honeycomb_ore", () -> new OreBlock(newBlockProp().strength(3F, 2F).requiresCorrectToolForDrops(), UniformInt.of(0, 2))).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL).loot((b, l) -> l.tableSingle(b, UniformGenerator.between(1f, 3f))).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> honeycomb_block = newSugaryBuilder("honeycomb_block", newBlockProp().requiresCorrectToolForDrops().strength(4F, 2.5F)).blockTag(MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<OreBlock> pez_ore = newSugaryBuilder("pez_ore", () -> new OreBlock(newBlockProp(Material.STONE).strength(3F, 2F).requiresCorrectToolForDrops())).enLang("PEZ Ore").blockTag(MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> pez_block = newSugaryBuilder("pez_block", newBlockProp().strength(5F, 6F).requiresCorrectToolForDrops()).enLang("PEZ Block").blockTag(MINEABLE_WITH_PICKAXE, NEEDS_DIAMOND_TOOL).build();
	//植物
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> flour = newSugaryBuilder("flour", newBlockProp(Material.DIRT).strength(0.6F).sound(CCSoundTypes.JELLY)).blockTag(CANDY_SAPLING_CAN_PLACE_ON).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockFlourFarmland> flour_farm = newSugaryBuilder("flour_farmland", () -> new BlockFlourFarmland(newBlockProp(Material.DIRT).strength(0.6F).sound(CCSoundTypes.JELLY).randomTicks().lootFrom(flour.block()))).blockItem((Function<BlockFlourFarmland, BlockItem>) null).model((provider, name, block) -> {
		BlockModelBuilder land = provider.models().withExistingParent("flour_farmland", "block/template_farmland")
				.texture("dirt", provider.modLoc("block/flour"))
				.texture("top", provider.modLoc("block/flour_farmland_top"));
		BlockModelBuilder land_moist = provider.models().withExistingParent("flour_farmland_moist", "block/template_farmland")
				.texture("dirt", provider.modLoc("block/flour"))
				.texture("top", provider.modLoc("block/flour_farmland_top_moist"));
		provider.getVariantBuilder(block).forAllStates((state) -> {
			int moisture = state.getValue(BlockFlourFarmland.MOISTURE);
			return ConfiguredModel.builder().modelFile(moisture == BlockFlourFarmland.MAX_MOISTURE ? land_moist : land).build();
		});
	}).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<BlockPudding> pudding = newSugaryBuilder("pudding", () -> new BlockPudding(newBlockProp(Material.GRASS).strength(0.6F).sound(CCSoundTypes.JELLY).randomTicks())).blockTag(CANDY_SAPLING_CAN_PLACE_ON).loot((b, l) -> l.tableSilkTouchDispatch(b, LootItem.lootTableItem(flour.block().get()))).model((provider, name, block) -> {
		BlockModelBuilder common = provider.models().cubeBottomTop("pudding", provider.modLoc("block/pudding_side"), provider.modLoc("block/flour"), provider.modLoc("block/pudding_top"));
		BlockModelBuilder ice = provider.models().withExistingParent("pudding_ice_flavor", "block/grass_block")
				.texture("particle", provider.modLoc("block/flour"))
				.texture("bottom", provider.modLoc("block/flour"))
				.texture("top", provider.modLoc("block/pudding_ice_flavor_top"))
				.texture("side", provider.modLoc("block/pudding_side"))
				.texture("overlay", provider.modLoc("block/pudding_ice_flavor_overlay"));
		provider.getVariantBuilder(block)
				.partialState().with(BlockPudding.IS_ICE_FLAVOR, true).modelForState().modelFile(ice).addModel()
				.partialState().with(BlockPudding.IS_ICE_FLAVOR, false).modelForState().modelFile(common).addModel();
		provider.simpleBlockItem(block, common);
	}).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> banana_seaweeds_block = newSugaryBuilder("banana_seaweeds_block", newBlockProp(Material.GRASS).strength(0.5F).sound(SoundType.GRASS)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> candied_cherry_block = newSugaryBuilder("candied_cherry_block", newBlockProp(Material.GRASS).strength(0.5F).sound(SoundType.GRASS)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> raspberry_block = newSugaryBuilder("raspberry_block", newBlockProp(Material.GRASS).strength(0.5F).sound(SoundType.GRASS)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> mint_block = newSugaryBuilder("mint_block", newBlockProp(Material.GRASS).strength(0.5F).sound(SoundType.GRASS)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> cotton_candy_block = newSugaryBuilder("cotton_candy_block", newBlockProp(Material.GRASS).strength(0.5F).sound(SoundType.GRASS)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> chewing_gum_block = newSugaryBuilder("chewing_gum_block", newBlockProp(Material.CLAY).strength(2.5F).sound(SoundType.SLIME_BLOCK).friction(0.7F)).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<WebBlock> cotton_candy_web = newSugaryBuilder("cotton_candy_web", () -> new WebBlock(newBlockProp(Material.WEB).strength(4F).noOcclusion().noCollission().requiresCorrectToolForDrops())).model(CROSS_GENERATOR).loot((b, l) -> l.tableSilkTouchOrShearsDispatch(b, LootItem.lootTableItem(CCItemManager.cotton_candy.get()))).build();
	//
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> ice_cream = newSugaryBuilder("ice_cream", newBlockProp(Material.SNOW).strength(0.5F).sound(SoundType.SNOW).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_SHOVEL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> mint_ice_cream = newSugaryBuilder("mint_ice_cream", newBlockProp(Material.SNOW).strength(0.5F).sound(SoundType.SNOW).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_SHOVEL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> strawberry_ice_cream = newSugaryBuilder("strawberry_ice_cream", newBlockProp(Material.SNOW).strength(0.5F).sound(SoundType.SNOW).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_SHOVEL).build();
	public static final CCBlockRegisterHelper.BlockRegistryObject<Block> blueberry_ice_cream = newSugaryBuilder("blueberry_ice_cream", newBlockProp(Material.SNOW).strength(0.5F).sound(SoundType.SNOW).requiresCorrectToolForDrops()).blockTag(MINEABLE_WITH_SHOVEL).build();

	static {
		CandyCraft.clockIn(CCBlockManager.class);
		CCDatagenManager.langEn.put("container.candycraft.crafting", "Marshmallow Crafting");
	}

	public static BlockBehaviour.Properties newBlockProp(Material material) {
		return BlockBehaviour.Properties.of(material);
	}

	public static BlockBehaviour.Properties newBlockProp() {
		return BlockBehaviour.Properties.of(Material.METAL);
	}

	public static void init() {
	}

	/**
	 * 方块点亮时发出光
	 */
	private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
		return (state) -> state.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
	}

	private static <B extends Block> CCBlockRegisterHelper.Builder<B> newSugaryBuilder(String name, Supplier<B> block) {
		return getBuilder(name, block).blockTag(SUGARY_BLOCK).blockItem(newDefaultItemProperties());
	}

	private static CCBlockRegisterHelper.Builder<Block> newSugaryBuilder(String name, BlockBehaviour.Properties blockProp) {
		return getBuilder(name, blockProp).blockTag(SUGARY_BLOCK).blockItem(newDefaultItemProperties());
	}

	private static CCBlockRegisterHelper.Builder<LeavesBlock> simpleCandyLeavesBlock(String name) {
		return newSugaryBuilder(name, () -> new LeavesBlock(newBlockProp(Material.LEAVES).randomTicks().strength(0.2F).sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, blockGetter, pos, entityType) -> false).isSuffocating(LambdaUtils::never).isViewBlocking(LambdaUtils::never)));
	}

	private static CCBlockRegisterHelper.Builder<RotatedPillarBlock> simpleCandyLogBlock(String name) {
		return newSugaryBuilder(name, () -> new RotatedPillarBlock(newBlockProp(Material.WOOD).sound(SoundType.WOOD).strength(2f)))
				.blockTag(MINEABLE_WITH_AXE)
				.model(LOG_GENERATOR);
	}

	private static CCBlockRegisterHelper.Builder<BlockCandySapling> simpleCandySaplingBlock(String name, AbstractTreeGrower grower) {
		return newSugaryBuilder(name, () -> new BlockCandySapling(grower, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)))
				.model(CROSS_GENERATOR);
	}


}
