package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 19:02
 */
public class CocBlockModelProvider extends BlockModelProvider {

	private final DatagenHelper helper;

	public CocBlockModelProvider(@NotNull DatagenHelper helper, DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, helper.modid, existingFileHelper);
		this.helper = helper;
	}

	@Override
	protected void registerModels() {
		helper.blockModel.forEach((name, consumer) -> consumer.accept(this, name));
	}
}
