package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:52
 */
public class CCItemModelProvider extends ItemModelProvider {

	public static final String GENERATED = "item/generated";
	public static final String HANDHELD = "item/handheld";

	public CCItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, CandyCraft.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		CCDatagenManager.itemModel.forEach((name, consumer) -> consumer.accept(this, name));
	}

	public interface ItemModelGenerator extends BiConsumer<CCItemModelProvider, String> {
		ItemModelGenerator SIMPLE_ITEM_GENERATOR = (provider, name) -> provider.withExistingParent(name, GENERATED).texture("layer0", provider.modLoc(ITEM_FOLDER + "/" + name));
		ItemModelGenerator HANDHELD_GENERATOR = (provider, name) -> provider.withExistingParent(name, HANDHELD).texture("layer0", provider.modLoc(ITEM_FOLDER + "/" + name));
	}
}
