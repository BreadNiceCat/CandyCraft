package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import cn.breadnicecat.codeovencore.util.SuBiConsumer;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 19:09
 */
public class CocBlockStateProvider extends BlockStateProvider {

	private final DatagenHelper helper;


	public CocBlockStateProvider(DatagenHelper helper, DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, helper.modid, exFileHelper);
		this.helper = helper;
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void registerStatesAndModels() {
		helper.blockModel.forEach((name, consumer) -> consumer.accept(this.models(), name));
		helper.blockState.forEach((name, consumer) -> consumer.accept(this, name, Registry.BLOCK.get(helper.instance.prefix(name))));
	}

	public interface BlockModelGenerator extends BiConsumer<BlockModelProvider, String> {
		BlockModelGenerator SIMPLE_BLOCK_MODEL = (provider, name) -> {
			provider.cubeAll(name, provider.modLoc(ModelProvider.BLOCK_FOLDER + "/" + name));
		};
		BlockModelGenerator TOP_SIDE_BLOCK_MODEL = (provider, name) -> {
			String blockRes = getBlockTexLoc(name);
			provider.cubeTop(name, provider.modLoc(blockRes + "_side"), provider.modLoc(blockRes + "_top"));
		};
		BlockModelGenerator TOP_DOWN_SIDE_BLOCK_MODEL = (provider, name) -> {
			String blockRes = getBlockTexLoc(name);
			provider.cubeBottomTop(name, provider.modLoc(blockRes + "_side"), provider.modLoc(blockRes + "_bottom"), provider.modLoc(blockRes + "_top"));
		};
		/**
		 * end:上下
		 */
		BlockModelGenerator END_SIDE_BLOCK_MODEL = (provider, name) -> {
			String blockRes = getBlockTexLoc(name);
			provider.cubeColumn(name, provider.modLoc(blockRes + "_side"), provider.modLoc(blockRes + "_end"));
		};

		private static String getBlockTexLoc(String blockName) {
			return ModelProvider.BLOCK_FOLDER + "/" + blockName;
		}

	}

	public interface BlockStateModelGenerator extends SuBiConsumer<CocBlockStateProvider, String, Block> {
		BlockStateModelGenerator SIMPLE_BLOCK_STATE = (provider, name, block) -> {
			provider.simpleBlock(block);
		};
		BlockStateModelGenerator SIMPLE_UNCHECKED_BLOCK_STATE = (provider, name, block) -> {
			provider.simpleBlock(block, new ModelFile.UncheckedModelFile(provider.blockTexture(block)));
		};
	}
}
