package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:12
 */
public class EnLanguageProvider extends LanguageProvider {


	private final DatagenHelper datagenHelper;

	public EnLanguageProvider(@NotNull DatagenHelper datagenHelper, DataGenerator generator) {
		super(generator, datagenHelper.modid, "en_us");
		this.datagenHelper = datagenHelper;
	}

	@Override
	protected void addTranslations() {
		datagenHelper.langEn.forEach(this::add);
	}
}
