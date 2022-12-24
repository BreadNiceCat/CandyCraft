package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:42
 */
public class CocSoundProvider extends SoundDefinitionsProvider {

	private final DatagenHelper datagenHelper;

	public CocSoundProvider(DatagenHelper datagenHelper, DataGenerator generator, ExistingFileHelper helper) {
		super(generator, datagenHelper.modid, helper);
		this.datagenHelper = datagenHelper;
	}

	@Override
	public void registerSounds() {
		datagenHelper.sounds.forEach(this::add);
	}
}
