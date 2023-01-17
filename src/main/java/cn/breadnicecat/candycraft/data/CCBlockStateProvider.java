package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.utils.SuBiConsumer;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cn.breadnicecat.candycraft.data.CCItemModelProvider.GENERATED;
import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 19:09
 */
public class CCBlockStateProvider extends BlockStateProvider {

	public final ExistingFileHelper exFileHelper;


	public CCBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, CandyCraft.MODID, exFileHelper);
		this.exFileHelper = exFileHelper;
	}

	/**
	 * @return modelLoc
	 */
	public ModelFile.ExistingModelFile existModelFile(ResourceLocation modelLoc) {
		return new ModelFile.ExistingModelFile(modelLoc, exFileHelper);
	}

	/**
	 * @return modid:block/block.regName
	 */
	public ModelFile.ExistingModelFile existModelFile(Block block) {
		assert block.getRegistryName() != null;
		return existModelFile(modLoc(BLOCK_FOLDER + "/" + block.getRegistryName().getPath()));
	}

	/**
	 * @return modid:block/block.regName_ext
	 */
	public ModelFile.ExistingModelFile existModelFile(Block block, String ext) {
		assert block.getRegistryName() != null;
		return existModelFile(modLoc(BLOCK_FOLDER + "/" + block.getRegistryName().getPath() + ext));
	}

	@Override
	protected void registerStatesAndModels() {
		CCDatagenManager.blockGenerator.forEach((block, consumer) -> {
			Block b = block.get();
			assert b.getRegistryName() != null;
			consumer.accept(this, b.getRegistryName().getPath(), b);
		});
	}

	@FunctionalInterface
	public interface BlockStateModelGenerator extends SuBiConsumer<CCBlockStateProvider, String, Block> {
		/**
		 * 六面同贴图
		 */
		BlockStateModelGenerator SIMPLE_GENERATOR = (provider, name, block) -> {
			provider.simpleBlock(block);
			provider.simpleBlockItem(block, provider.existModelFile(block));
		};
		/**
		 * 上下(_end)同贴图，侧面(_side)同贴图,不可旋转
		 */
		BlockStateModelGenerator COLUMN_GENERATOR = (provider, name, block) -> {
			String blockLoc = ModelProvider.BLOCK_FOLDER + "/" + name;
			BlockModelBuilder blockModel = provider.models().cubeColumn(name, provider.modLoc(blockLoc + "_side"), provider.modLoc(blockLoc + "_end"));
			provider.simpleBlock(block, blockModel);
			provider.simpleBlockItem(block, blockModel);
		};
		/**
		 * 上下(_top)同贴图，侧面(直接是物品名)同贴图,可自由旋转的方块(类似于原木)
		 */
		BlockStateModelGenerator LOG_GENERATOR = (provider, name, block) -> {
			provider.logBlock((RotatedPillarBlock) block);
			provider.simpleBlockItem(block, provider.existModelFile(block));
		};
		/**
		 * 类似树苗的方块
		 */
		BlockStateModelGenerator CROSS_GENERATOR = (provider, name, block) -> {
			ResourceLocation tex = provider.blockTexture(block);
			BlockModelBuilder model = provider.models().cross(name, tex);
			provider.simpleBlock(block, model);
			provider.itemModels().withExistingParent(name, "item/generated").texture("layer0", tex);
		};

		/**
		 * 类似地狱传送门的方块
		 */
		BlockStateModelGenerator PORTAL_GENERATOR = (provider, name, block) -> {
			String blockLoc = BLOCK_FOLDER + "/" + name;
			ResourceLocation blockTexture = provider.modLoc(blockLoc);
			BlockModelBuilder ns = provider.models().withExistingParent(blockLoc + "_ns", BLOCK_FOLDER + "/nether_portal_ns")
					.texture("portal", blockTexture)
					.texture("particle", blockTexture);
			BlockModelBuilder ew = provider.models().withExistingParent(blockLoc + "_ew", BLOCK_FOLDER + "/nether_portal_ew")
					.texture("portal", blockTexture)
					.texture("particle", blockTexture);
			provider.getVariantBuilder(block)
					.partialState().with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X).modelForState().modelFile(ns).addModel()
					.partialState().with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z).modelForState().modelFile(ew).addModel();
		};
		/**
		 * 类似门(继承DoorBlock)的方块,顶部(_top),底部(_bottom),方块物品(定向为item文件夹中文件名为方块名的贴图)
		 */
		BlockStateModelGenerator DOOR_GENERATOR = (provider, name, block) -> {
			provider.doorBlock((DoorBlock) block, provider.modLoc("block/" + name + "_bottom"), provider.modLoc("block/" + name + "_top"));
			provider.itemModels().withExistingParent(name, GENERATED)
					.texture("layer0", "item/" + name);
		};
		/**
		 * 火把
		 * 注意：同时生成在墙上的火把(wall_)无需提供额外贴图,但是会额外生成模型。
		 * 所以请保证注册了WallTorch且在注册WallTorch时model传入了null
		 */
		@SuppressWarnings("deprecation")
		BlockStateModelGenerator TORCH_GENERATOR = (provider, name, block) -> {
			ResourceLocation tex = provider.blockTexture(block);
			BlockModelBuilder torch = provider.models().withExistingParent(name, "block/template_torch")
					.texture("torch", tex);
			BlockModelBuilder wallModel = provider.models().withExistingParent("wall_" + name, "block/template_torch_wall")
					.texture("torch", tex);
			WallTorchBlock wallTorch = (WallTorchBlock) Registry.BLOCK.get(provider.modLoc("wall_" + name));
			provider.simpleBlock(block, torch);
			provider.horizontalBlock(wallTorch, wallModel, 90);
			provider.itemModels().withExistingParent(name, GENERATED)
					.texture("layer0", tex);
		};
	}
}
