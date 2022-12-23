package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:54
 */
public class SoundHelper {
	private final CodeOvenCoreInstance instance;
	public final DeferredRegister<SoundEvent> register;


	public SoundHelper(CodeOvenCoreInstance instance) {
		this.instance = instance;
		register = DeferredRegister.create(SoundEvent.class, instance.modid);
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

	}

	public RegistryObject<SoundEvent> registerSound(String name, Supplier<SoundEvent> soundEvent, SoundDefinition definition) {
		instance.getDatagenHelper().sounds.put(soundEvent, definition);
		return register.register(name, soundEvent);
	}


	/**
	 * @param sounds 存在多个sound时，每次播放随机一个
	 */
	public RegistryObject<SoundEvent> registerSound(String name, SoundDefinition.Sound... sounds) {
		return registerSound(name, () -> new SoundEvent(instance.prefix(name)), SoundDefinition.definition().with(sounds));
	}

	public RegistryObject<SoundEvent> registerSound(String name, ResourceLocation path, SoundDefinition.SoundType type) {
		return registerSound(name, SoundDefinition.Sound.sound(path, type));
	}

	public RegistryObject<SoundEvent> registerSound(String name, String path, SoundDefinition.SoundType type) {
		return registerSound(name, instance.prefix(path), type);
	}
}
