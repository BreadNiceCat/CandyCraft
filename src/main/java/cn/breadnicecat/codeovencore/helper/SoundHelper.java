package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:54
 */
public class SoundHelper extends Helper {
	public final DeferredRegister<SoundEvent> register;


	public SoundHelper(CodeOvenCoreInstance instance) {
		super(instance);
		register = getRegister(SoundEvent.class);

	}

	/**
	 * @param replace  是否会覆盖原有的声音
	 * @param subtitle 字幕显示的键(可空)
	 * @param sound    存在多个sound时，每次播放随机一个
	 */
	public RegistryObject<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> soundEvent, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sound) {
		instance.getDatagenHelper().sounds.put(soundEvent, SoundDefinition.definition().with(sound).replace(replace).subtitle(subtitle));
		return register.register(name, soundEvent);
	}

	public RegistryObject<SoundEvent> registerSoundEvent(String name, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sounds) {
		return registerSoundEvent(name, () -> new SoundEvent(instance.prefix(name)), replace, subtitle, sounds);
	}

	public RegistryObject<SoundEvent> registerMusicSoundEvent(String name, ResourceLocation soundPath) {
		return registerSoundEvent(name, true, null, SoundDefinition.Sound.sound(soundPath, SoundDefinition.SoundType.SOUND).stream());
	}

	public RegistryObject<SoundEvent> registerMusicSoundEvent(String name, String soundPath) {
		return registerMusicSoundEvent(name, instance.prefix(soundPath));
	}


}
