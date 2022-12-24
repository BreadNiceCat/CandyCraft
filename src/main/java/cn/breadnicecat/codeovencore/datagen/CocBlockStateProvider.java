package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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

	@Override
	protected void registerStatesAndModels() {
		helper.blockState.forEach((name, consumer) -> consumer.accept(this, name));
	}
}
