package cn.breadnicecat.candycraft.sound;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.codeovencore.helper.SoundHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:51
 */
public class CCSounds {

	private static final SoundHelper register = CandyCraft.CORE_INSTANCE.getSoundHelper();

	public static final RegistryObject<SoundEvent> cd_1 = register.registerSound("cd-1", "records/cd-1", SoundDefinition.SoundType.SOUND);
	public static final RegistryObject<SoundEvent> cd_2 = register.registerSound("cd-2", "records/cd-2", SoundDefinition.SoundType.SOUND);
	public static final RegistryObject<SoundEvent> cd_3 = register.registerSound("cd-3", "records/cd-3", SoundDefinition.SoundType.SOUND);
	public static final RegistryObject<SoundEvent> cd_4 = register.registerSound("cd-4", "records/cd-4", SoundDefinition.SoundType.SOUND);
}
