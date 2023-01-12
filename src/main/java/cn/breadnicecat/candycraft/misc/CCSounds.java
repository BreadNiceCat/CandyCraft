package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

import static cn.breadnicecat.candycraft.CandyCraft.prefix;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 10:51
 */
public class CCSounds {


	public static final RegistryObject<SoundEvent> cd_1 = CCSoundHelper.registerSoundEvent("cd-1", "records/cd-1");
	public static final RegistryObject<SoundEvent> cd_2 = CCSoundHelper.registerSoundEvent("cd-2", "records/cd-2");
	public static final RegistryObject<SoundEvent> cd_3 = CCSoundHelper.registerSoundEvent("cd-3", "records/cd-3");
	public static final RegistryObject<SoundEvent> cd_4 = CCSoundHelper.registerSoundEvent("cd-4", "records/cd-4");
	public static final RegistryObject<SoundEvent> jelly_block = CCSoundHelper.getBuilder("jelly_block").sound("blocks/jelly1").sound("blocks/jelly2").build();

	static {
		CandyCraft.clockIn();
	}

	public static void init() {
	}

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/23 10:54
	 */
	public static class CCSoundHelper {
		public static final DeferredRegister<SoundEvent> REGISTER = CandyCraft.registerRegister(DeferredRegister.create(SoundEvent.class, CandyCraft.MODID));

		/**
		 * 原始注册方法
		 *
		 * @param replace  是否会覆盖原有的声音
		 * @param subtitle 字幕显示的键(可空)
		 * @param sound    存在多个sound时，每次播放随机一个
		 */
		public static RegistryObject<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> soundEvent, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sound) {
			Lazy<SoundEvent> supplier = Lazy.of(soundEvent);
			CCDatagenManager.sounds.put(supplier, SoundDefinition.definition().with(sound).replace(replace).subtitle(subtitle));
			return REGISTER.register(name, supplier);
		}

		public static RegistryObject<SoundEvent> registerSoundEvent(String name, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sounds) {
			return registerSoundEvent(name, () -> new SoundEvent(prefix(name)), replace, subtitle, sounds);
		}


		public static RegistryObject<SoundEvent> registerSoundEvent(String name, String soundPath) {
			return getBuilder(name).sound(soundPath).build();
		}

		public static Builder getBuilder(String name) {
			return new Builder(name);
		}

		public static class Builder {

			private final String name;
			private ArrayList<SoundDefinition.Sound> sounds = new ArrayList<>();
			@Nullable
			private String subtitle = null;
			private boolean replace = false;

			public Builder(String name) {
				this.name = name;
			}

			public Builder sound(String soundPath) {
				this.sound(CandyCraft.prefix(soundPath));
				return this;
			}

			public Builder sound(ResourceLocation soundPath) {
				this.sound(SoundDefinition.Sound.sound(soundPath, SoundDefinition.SoundType.SOUND));
				return this;
			}

			public Builder sound(SoundDefinition.Sound sound) {
				sounds.add(sound);
				return this;
			}

			/**
			 * 显示字幕时字幕内容的键
			 * 默认null
			 */
			public Builder subtitle(String key) {
				subtitle = key;
				return this;
			}

			/**
			 * 播放时替换其他声音
			 * 默认false
			 */
			public Builder replace(boolean replace) {
				this.replace = replace;
				return this;
			}

			public RegistryObject<SoundEvent> build() {
				return registerSoundEvent(name, replace, subtitle, sounds.toArray(new SoundDefinition.Sound[0]));
			}

		}


	}

}
