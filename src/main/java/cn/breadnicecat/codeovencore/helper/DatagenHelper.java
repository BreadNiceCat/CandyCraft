package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.*;
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
public class DatagenHelper extends Helper {

	public HashMap<String, String> langEn = new HashMap<>();
	public HashMap<Supplier<SoundEvent>, SoundDefinition> sounds = new HashMap<>();
	public HashMap<String, BiConsumer<CocItemModelProvider, String>> itemModel = new HashMap<>();
	public HashMap<String, BiConsumer<CocBlockModelProvider, String>> blockModel = new HashMap<>();
	public HashMap<String, BiConsumer<CocBlockStateProvider, String>> blockState = new HashMap<>();

	public DatagenHelper(CodeOvenCoreInstance instance) {
		super(instance);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void register(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper efHelper = event.getExistingFileHelper();
		if (event.includeClient()) {
			//block/item models, blockstates, language files...
			generator.addProvider(new EnLanguageProvider(this, generator));
			generator.addProvider(new CocItemModelProvider(this, generator, efHelper));
			generator.addProvider(new CocSoundProvider(this, generator, efHelper));
			generator.addProvider(new CocBlockModelProvider(this, generator, efHelper));
			generator.addProvider(new CocBlockStateProvider(this, generator, efHelper));
		}
		if (event.includeServer()) {
			//recipes,advancements,tags...
		}
		if (event.includeReports()) {
			//world
		}
	}

}
