package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 13:11
 */
public class DatagenHelper extends Helper {

	public HashMap<String, String> langEn = new HashMap<>();
	public HashMap<Supplier<SoundEvent>, SoundDefinition> sounds = new HashMap<>();
	public HashMap<String, CocItemModelProvider.ItemModelGenerator> itemModel = new HashMap<>();
	public HashMap<String, CocBlockStateProvider.BlockModelGenerator> blockModel = new HashMap<>();
	public HashMap<String, CocBlockStateProvider.BlockStateModelGenerator> blockState = new HashMap<>();
	public HashMap<TagKey<Block>, List<Supplier<Block>>> blockTags = new HashMap<>();

	public DatagenHelper(CodeOvenCoreInstance instance) {
		super(instance);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void register(@NotNull final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper efHelper = event.getExistingFileHelper();
		generator.addProvider(new EnLanguageProvider(this, generator));
		generator.addProvider(new CocBlockStateProvider(this, generator, efHelper));
		generator.addProvider(new CocItemModelProvider(this, generator, efHelper));
		generator.addProvider(new CocSoundProvider(this, generator, efHelper));
		generator.addProvider(new CocBlockTagsProvider(this, generator, efHelper));
	}

}
