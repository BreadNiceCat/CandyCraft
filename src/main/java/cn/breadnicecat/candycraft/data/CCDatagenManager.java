package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.recipe.CCRecipes;
import cn.breadnicecat.candycraft.utils.LambdaUtils.LazySupplier;
import cn.breadnicecat.candycraft.utils.VoidMap;
import cn.breadnicecat.candycraft.utils.VoidSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 8:17
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = CandyCraft.MODID)
public class CCDatagenManager {
	private final static boolean runningDataGen = DatagenModLoader.isRunningDataGen();
	//Lang
	public static Map<String, String> langEn = newMap();
	//Sound
	public static Map<LazySupplier<SoundEvent>, SoundDefinition> sounds = newMap();
	//ItemModel
	public static Map<String, CCItemModelProvider.ItemModelGenerator> itemModelGenerators = newMap();
	//BlockState
	public static Map<LazySupplier<? extends Block>, CCBlockStateProvider.BlockStateModelGenerator> blockGenerators = newMap();
	public static Set<LazySupplier<? extends Block>> noItemBlock = newSet();
	//BlockTag
	public static Map<TagKey<Block>, List<LazySupplier<? extends Block>>> blockTags = newMap();
	//ItemTag
	public static Map<TagKey<Item>, List<LazySupplier<? extends Item>>> itemTags = newMap();
	public static Map<TagKey<Block>, TagKey<Item>> similarTags = newMap();
	//Loot
	public static Map<LazySupplier<? extends Block>, CCBlockLoot.CCBlockLootFunction> blockLoot = newMap();
	//Recipe
	public static Set<Consumer<Consumer<FinishedRecipe>>> recipes = newSet();
	
	static {
		if (runningDataGen) {
			CandyCraft.clockIn();
			CCRecipes.init();
			CandyCraft.getLogger().warn("RUNNING IN DATAGEN MODE.");
		} else {
			CandyCraft.getLogger().info("Isn't running in datagen mode,classes in `data` package won't working.");
		}
	}
	
	private static <T> Set<T> newSet() {
		if (runningDataGen) {
			return new HashSet<>();
		} else {
			return new VoidSet().castSet();
		}
	}
	
	private static <K, V> Map<K, V> newMap() {
		if (runningDataGen) {
			return new HashMap<>();
		} else {
			return new VoidMap().castMap();
		}
	}
	
	@SubscribeEvent
	public static void register(@NotNull final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper efHelper = event.getExistingFileHelper();
		generator.addProvider(new CCEnLanguageProvider(generator));
		generator.addProvider(new CCSoundProvider(generator, efHelper));
		generator.addProvider(new CCBlockStateProvider(generator, efHelper));
		generator.addProvider(new CCItemModelProvider(generator, efHelper));
		CCBlockTagsProvider blockTagsProvider = new CCBlockTagsProvider(generator, efHelper);
		generator.addProvider(blockTagsProvider);
		generator.addProvider(new CCItemTagsProvider(generator, blockTagsProvider, efHelper));
		generator.addProvider(new CCLootTableProvider(generator));
		generator.addProvider(new CCCraftingTableRecipeProvider(generator));
	}
}
