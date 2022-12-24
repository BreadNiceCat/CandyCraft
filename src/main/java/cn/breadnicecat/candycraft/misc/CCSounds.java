package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.codeovencore.helper.SoundHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:51
 */
public class CCSounds {

	private static final SoundHelper helper = CandyCraft.CORE_INSTANCE.getSoundHelper();

	public static final RegistryObject<SoundEvent> cd_1 = helper.registerMusicSoundEvent("cd-1", "records/cd-1");
	public static final RegistryObject<SoundEvent> cd_2 = helper.registerMusicSoundEvent("cd-2", "records/cd-2");
	public static final RegistryObject<SoundEvent> cd_3 = helper.registerMusicSoundEvent("cd-3", "records/cd-3");
	public static final RegistryObject<SoundEvent> cd_4 = helper.registerMusicSoundEvent("cd-4", "records/cd-4");
}
