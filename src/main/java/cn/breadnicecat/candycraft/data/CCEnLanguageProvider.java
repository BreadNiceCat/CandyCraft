package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:12
 */
public class CCEnLanguageProvider extends LanguageProvider {


	public CCEnLanguageProvider(DataGenerator generator) {
		super(generator, CandyCraft.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		CCDatagenManager.langEn.forEach(this::add);
	}
}
