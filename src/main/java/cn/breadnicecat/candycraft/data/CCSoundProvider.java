package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:42
 */
public class CCSoundProvider extends SoundDefinitionsProvider {


	public CCSoundProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, CandyCraft.MODID, helper);
	}

	@Override
	public void registerSounds() {
		CCDatagenManager.sounds.forEach(this::add);
	}
}
