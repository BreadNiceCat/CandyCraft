package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.CocBlockStateProvider;
import cn.breadnicecat.codeovencore.datagen.CocItemModelProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static cn.breadnicecat.codeovencore.util.CommonUtils.getEnLangByName;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 13:39
 */
public class BlockRegisterHelper extends Helper {
	public final DeferredRegister<Block> register;

	public BlockRegisterHelper(CodeOvenCoreInstance instance) {
		super(instance);
		register = getRegister(Block.class);
	}

	/**
	 * 最原始的注册方法
	 *
	 * @param name                名字(最好后缀_block以免与物品名称发生碰撞)
	 * @param enLoc               英文名
	 * @param block               方块,NOTE:方块Supplier请使用Lazy包装，防止重复创建方块
	 * @param blockitem           方块对应的物品，NOTE:物品构造函数中请传入Lazy包装过的Block，否则可能导致
	 *                            {@code Caused by: java.lang.IllegalStateException: Some intrusive holders were not added to registry: [Reference{null=Block{null}}]}
	 * @param itemModelGenerator  物品模型生成器
	 * @param blockModelGenerator 方块模型生成器
	 * @param blockStateGenerator 方块状态生成器
	 */
	public <B extends Block> BlockRegisryObject<B> registerBlock0(
			@NotNull String name, @NotNull String enLoc, @NotNull Lazy<B> block, @NotNull Supplier<BlockItem> blockitem,
			@Nullable CocItemModelProvider.ItemModelGenerator itemModelGenerator,
			@Nullable CocBlockStateProvider.BlockModelGenerator blockModelGenerator,
			@Nullable CocBlockStateProvider.BlockStateModelGenerator blockStateGenerator
	) {
		DatagenHelper datagenHelper = instance.getDatagenHelper();
		instance.getDatagenHelper().langEn.put("block." + modid + "." + name, enLoc);
		if (blockModelGenerator != null) datagenHelper.blockModel.put(name, blockModelGenerator);
		if (blockStateGenerator != null) datagenHelper.blockState.put(name, blockStateGenerator);
		return new BlockRegisryObject<>(register.register(name, block), instance.getItemRegisterHelper().registerItem(name, blockitem, itemModelGenerator));
	}

	/**
	 * 不生成BlockItem,并覆写语言文件
	 */
	public <B extends Block> RegistryObject<Block> registerBlockWithNOBlockItem0(
			@NotNull String name, @NotNull String enLoc, @NotNull Supplier<B> block,
			@Nullable CocBlockStateProvider.BlockModelGenerator blockModelGenerator,
			@Nullable CocBlockStateProvider.BlockStateModelGenerator blockStateGenerator
	) {
		DatagenHelper datagenHelper = instance.getDatagenHelper();
		instance.getDatagenHelper().langEn.put("block." + modid + "." + name, enLoc);
		if (blockModelGenerator != null) datagenHelper.blockModel.put(name, blockModelGenerator);
		if (blockStateGenerator != null) datagenHelper.blockState.put(name, blockStateGenerator);
		return register.register(name, block);
	}

	public <B extends Block> RegistryObject<Block> registerBlockWithNOBlockItem(
			@NotNull String name, @NotNull Supplier<B> block,
			@Nullable CocBlockStateProvider.BlockModelGenerator blockModelGenerator,
			@Nullable CocBlockStateProvider.BlockStateModelGenerator blockStateGenerator
	) {
		return registerBlockWithNOBlockItem0(name, getEnLangByName(name), block, blockModelGenerator, blockStateGenerator);
	}


	/**
	 * 不生成BlockItem,且使用默认生成器
	 */
	public <B extends Block> RegistryObject<Block> registerBlockWithNOBlockItemWithDefaultGenerator(
			@NotNull String name, @NotNull Supplier<B> block
	) {
		return registerBlockWithNOBlockItem(name, block, CocBlockStateProvider.BlockModelGenerator.SIMPLE_BLOCK_MODEL, CocBlockStateProvider.BlockStateModelGenerator.SIMPLE_BLOCK_STATE);
	}

	/**
	 * 自动生成BlockItem，并且覆写语言文件
	 */
	public <B extends Block> BlockRegisryObject<B> registerBlockWithoutBlockItem(
			@NotNull String name, @NotNull String enLoc, @NotNull Supplier<B> block, @NotNull Item.Properties blockItemProp,
			@Nullable CocItemModelProvider.ItemModelGenerator itemModelGenerator,
			@Nullable CocBlockStateProvider.BlockModelGenerator blockModelGenerator,
			@Nullable CocBlockStateProvider.BlockStateModelGenerator blockStateGenerator
	) {
		Lazy<B> blockCache = Lazy.of(block);
		return registerBlock0(name, enLoc, blockCache, () -> new BlockItem(blockCache.get(), blockItemProp), itemModelGenerator, blockModelGenerator, blockStateGenerator);
	}

	/**
	 * 自动生成BlockItem
	 */
	public <B extends Block> BlockRegisryObject<B> registerBlockWithoutBlockItem(
			@NotNull String name, @NotNull Supplier<B> block, @NotNull Item.Properties blockItemProp,
			@Nullable CocItemModelProvider.ItemModelGenerator itemModelGenerator,
			@Nullable CocBlockStateProvider.BlockModelGenerator blockModelGenerator,
			@Nullable CocBlockStateProvider.BlockStateModelGenerator blockStateGenerator
	) {
		return registerBlockWithoutBlockItem(name, getEnLangByName(name), block, blockItemProp, itemModelGenerator, blockModelGenerator, blockStateGenerator);
	}

	/**
	 * 自动生成BlockItem，并使用默认的生成器，但是覆写语言文件
	 */
	public <B extends Block> BlockRegisryObject<B> registerBlockWithoutBlockItemWithDefaultGenerator(
			@NotNull String name, @NotNull String enLoc, @NotNull Supplier<B> block, @NotNull Item.Properties blockItemProp
	) {
		return registerBlockWithoutBlockItem(name, enLoc, block, blockItemProp, CocItemModelProvider.ItemModelGenerator.SIMPLE_BLOCK_ITEM_GENERATOR, CocBlockStateProvider.BlockModelGenerator.SIMPLE_BLOCK_MODEL, CocBlockStateProvider.BlockStateModelGenerator.SIMPLE_BLOCK_STATE);
	}


	/**
	 * 自动生成BlockItem，并使用默认的生成器
	 */
	public <B extends Block> BlockRegisryObject<B> registerBlockWithoutBlockItemWithDefaultGenerator(
			@NotNull String name, @NotNull Supplier<B> block, @NotNull Item.Properties blockItemProp
	) {
		return registerBlockWithoutBlockItemWithDefaultGenerator(name, getEnLangByName(name), block, blockItemProp);
	}

	/**
	 * 注册简单的，没有其他作用的方块，并且覆写语言文件
	 */
	public BlockRegisryObject<Block> registerSimpleBlock(@NotNull String name, @NotNull String enLoc, @NotNull BlockBehaviour.Properties blockProp, @NotNull Item.Properties blockItemProp) {
		Lazy<Block> block = Lazy.of(() -> new Block(blockProp));
		return registerBlock0(name, enLoc, block, () -> new BlockItem(block.get(), blockItemProp), CocItemModelProvider.ItemModelGenerator.SIMPLE_BLOCK_ITEM_GENERATOR, CocBlockStateProvider.BlockModelGenerator.SIMPLE_BLOCK_MODEL, CocBlockStateProvider.BlockStateModelGenerator.SIMPLE_BLOCK_STATE);
	}

	/**
	 * 注册简单的，没有其他作用的方块
	 */
	public BlockRegisryObject<Block> registerSimpleBlock(@NotNull String name, @NotNull BlockBehaviour.Properties blockProp, @NotNull Item.Properties blockItemProp) {
		return registerSimpleBlock(name, getEnLangByName(name), blockProp, blockItemProp);
	}

	public void mineableByPickaxe(List<RegistryObject<Block>> block) {
		instance.getDatagenHelper().blockTags.computeIfAbsent(BlockTags.MINEABLE_WITH_PICKAXE, a -> new ArrayList<>())
				.addAll(block);
	}

	/**
	 * NOTE:BRO只是名字标识
	 */
	public void mineableByPickaxeBRO(List<BlockRegisryObject<Block>> block) {
		mineableByPickaxe(block.stream().map(BlockRegisryObject::block).toList());
	}

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/24 20:37
	 * 用于包装方块注册对象
	 */
	public record BlockRegisryObject<B extends Block>(@NotNull RegistryObject<B> block,
	                                                  @NotNull RegistryObject<BlockItem> blockItem) {
	}
}
