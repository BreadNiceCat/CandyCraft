package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.EnLanguageProvider;
import cn.breadnicecat.codeovencore.datagen.SimpleItemModelProvider;
import cn.breadnicecat.codeovencore.datagen.SoundProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:11
 */
public class DatagenHelper {

	public final String modid;
	public HashMap<String, String> langEn = new HashMap<>();
	public HashMap<Supplier<SoundEvent>, SoundDefinition> sounds = new HashMap<>();
	public HashMap<String, BiConsumer<SimpleItemModelProvider, String>> itemModel = new HashMap<>();

	public DatagenHelper(CodeOvenCoreInstance instance) {
		modid = instance.modid;
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void register(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper efHelper = event.getExistingFileHelper();
		if (event.includeClient()) {
			//block/item models, blockstates, language files...
			generator.addProvider(new EnLanguageProvider(this, generator));
			generator.addProvider(new SimpleItemModelProvider(this, generator, efHelper));
			generator.addProvider(new SoundProvider(this, generator, efHelper));
		}
		if (event.includeServer()) {
			//recipes,advancements,tags...
		}
		if (event.includeReports()) {
			//world
		}
	}

}
