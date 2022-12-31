package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.codeovencore.helper.BlockRegisterHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import static cn.breadnicecat.candycraft.item.ItemManager.newDefaultItemProperties;
import static cn.breadnicecat.codeovencore.datagen.CocBlockStateProvider.BlockModelGenerator.END_SIDE_BLOCK_MODEL;
import static cn.breadnicecat.codeovencore.datagen.CocBlockStateProvider.BlockStateModelGenerator.SIMPLE_UNCHECKED_BLOCK_STATE;
import static cn.breadnicecat.codeovencore.datagen.CocItemModelProvider.ItemModelGenerator.SIMPLE_BLOCK_ITEM_GENERATOR;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 14:00
 */
@SuppressWarnings("unused")//Stupid Warning
public class BlockManager {
	private static BlockRegisterHelper helper = CandyCraft.CORE_INSTANCE.getBlockRegisterHelper();

	public static BlockRegisterHelper.BlockRegisryObject<Block> sugar_factory = helper.registerBlockWithoutBlockItemWithDefaultGenerator("sugar_factory", SugerFactory::new, newDefaultItemProperties());
	public static BlockRegisterHelper.BlockRegisryObject<Block> advanced_sugar_factory = helper.registerBlockWithoutBlockItemWithDefaultGenerator("advanced_sugar_factory", AdvancedSugarFactory::new, newDefaultItemProperties());
	public static BlockRegisterHelper.BlockRegisryObject<Block> candy_cane_block = helper.registerBlockWithoutBlockItem("candy_cane_block", () -> new Block(newBlockProp().requiresCorrectToolForDrops()), newDefaultItemProperties(), SIMPLE_BLOCK_ITEM_GENERATOR, END_SIDE_BLOCK_MODEL, SIMPLE_UNCHECKED_BLOCK_STATE);
	public static BlockRegisterHelper.BlockRegisryObject<Block> sugar_block = helper.registerSimpleBlock("sugar_block", newBlockProp(Material.SAND).sound(SoundType.SAND), newDefaultItemProperties());
	public static RegistryObject<Block> candyland_portal_block = helper.registerBlockWithNOBlockItemWithDefaultGenerator("candyland_portal_block", CandylandPortalBlock::new);


	static {
//		helper.mineableByPickaxeBRO(List.of(sugar_factory, advanced_sugar_factory));
	}

	public static BlockBehaviour.Properties newBlockProp(Material material) {
		return BlockBehaviour.Properties.of(material);
	}

	public static BlockBehaviour.Properties newBlockProp() {
		return BlockBehaviour.Properties.of(Material.METAL);
	}

	public static void init() {
	}
}
