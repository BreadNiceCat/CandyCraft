package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.utils.LambdaUtils.LazySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
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
public final class CCSoundEvents {


	public static final RegistryObject<SoundEvent> cd_1 = CCSoundEventHelper.registerStreamSoundEvent("cd-1", "records/cd-1");
	public static final RegistryObject<SoundEvent> cd_2 = CCSoundEventHelper.registerStreamSoundEvent("cd-2", "records/cd-2");
	public static final RegistryObject<SoundEvent> cd_3 = CCSoundEventHelper.registerStreamSoundEvent("cd-3", "records/cd-3");
	public static final RegistryObject<SoundEvent> cd_4 = CCSoundEventHelper.registerStreamSoundEvent("cd-4", "records/cd-4");
	public static final RegistryObject<SoundEvent> cd_5 = CCSoundEventHelper.registerStreamSoundEvent("cd-5", "records/cd-5");
	public static final RegistryObject<SoundEvent> jelly_block = CCSoundEventHelper.getBuilder("jelly_block").sound("blocks/jelly1").sound("blocks/jelly2").build();
	public static final RegistryObject<SoundEvent> jelly_step = CCSoundEventHelper.getBuilder("jelly_step").sound("steps/jelly1").sound("steps/jelly2").sound("steps/jelly3").sound("steps/jelly4").build();

	static {
		CandyCraft.clockIn();
	}

	public static void init() {
	}

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/23 10:54
	 */
	public static class CCSoundEventHelper {
		public static final DeferredRegister<SoundEvent> REGISTER = CandyCraft.createRegister(SoundEvent.class);

		/**
		 * ??????????????????
		 *
		 * @param replace  ??????????????????????????????
		 * @param subtitle ??????????????????(??????)
		 * @param sound    ????????????sound??????????????????????????????
		 */
		public static RegistryObject<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> soundEvent, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sound) {
			LazySupplier<SoundEvent> supplier = LazySupplier.of(soundEvent);
			CCDatagenManager.sounds.put(supplier, SoundDefinition.definition().with(sound).replace(replace).subtitle(subtitle));
			return REGISTER.register(name, supplier);
		}

		public static RegistryObject<SoundEvent> registerSoundEvent(String name, boolean replace, @Nullable String subtitle, SoundDefinition.Sound... sounds) {
			return registerSoundEvent(name, () -> new SoundEvent(prefix(name)), replace, subtitle, sounds);
		}


		public static RegistryObject<SoundEvent> registerSoundEvent(String name, String soundPath) {
			return getBuilder(name).sound(soundPath).build();
		}

		public static RegistryObject<SoundEvent> registerStreamSoundEvent(String name, String soundPath) {
			return getBuilder(name).sound(soundPath, true).build();
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
			private String enLangSubtitle;

			public Builder(String name) {
				this.name = name;
			}

			public Builder sound(String soundPath) {
				return sound(soundPath, false);
			}

			public Builder sound(String soundPath, boolean stream) {
				this.sound(CandyCraft.prefix(soundPath), stream);
				return this;
			}

			public Builder sound(ResourceLocation soundPath, boolean stream) {
				this.sound(SoundDefinition.Sound.sound(soundPath, SoundDefinition.SoundType.SOUND).stream(stream));
				return this;
			}

			public Builder sound(SoundDefinition.Sound sound) {
				sounds.add(sound);
				return this;
			}

			/**
			 * ?????????????????????????????????
			 * ??????null
			 */
			public Builder subtitle(String key, String enLang) {
				subtitle = key;
				this.enLangSubtitle = enLang;
				return this;
			}

			/**
			 * ???????????????????????????
			 * ??????false
			 */
			public Builder replace(boolean replace) {
				this.replace = replace;
				return this;
			}

			public RegistryObject<SoundEvent> build() {
				if (subtitle != null) CCDatagenManager.langEn.put(subtitle, enLangSubtitle);
				return registerSoundEvent(name, replace, subtitle, sounds.toArray(new SoundDefinition.Sound[0]));
			}

		}


	}

}
