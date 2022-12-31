package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:52
 */
public class CocItemModelProvider extends ItemModelProvider {

	public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
	private final DatagenHelper datagenHelper;

	public CocItemModelProvider(DatagenHelper datagenHelper, DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, datagenHelper.modid, existingFileHelper);
		this.datagenHelper = datagenHelper;
	}

	@Override
	protected void registerModels() {
		datagenHelper.itemModel.forEach((name, consumer) -> consumer.accept(this, name));
	}

	public interface ItemModelGenerator extends BiConsumer<CocItemModelProvider, String> {
		ItemModelGenerator SIMPLE_ITEM_GENERATOR = (provider, name) -> {
			provider.withExistingParent(name, GENERATED).texture("layer0", provider.modLoc(ITEM_FOLDER + "/" + name));
		};
		ItemModelGenerator SIMPLE_BLOCK_ITEM_GENERATOR = (provider, name) -> {
			provider.withExistingParent(name, provider.modLoc(BLOCK_FOLDER + "/" + name));
		};
	}
}
