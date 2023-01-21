package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.utils.LambdaUtils.LazySupplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 8:17
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = CandyCraft.MODID)
public class CCDatagenManager {
    //Lang
    public static HashMap<String, String> langEn = new HashMap<>();
    //Sound
    public static HashMap<LazySupplier<SoundEvent>, SoundDefinition> sounds = new HashMap<>();
    //ItemModel
    public static HashMap<String, CCItemModelProvider.ItemModelGenerator> itemModelGenerators = new HashMap<>();
    //BlockState
    public static HashMap<LazySupplier<? extends Block>, CCBlockStateProvider.BlockStateModelGenerator> blockGenerators = new HashMap<>();
    public static HashSet<LazySupplier<? extends Block>> noItemBlock = new HashSet<>();
    //BlockTag
    public static HashMap<TagKey<Block>, List<LazySupplier<? extends Block>>> blockTags = new HashMap<>();
    //ItemTag
    public static HashMap<TagKey<Item>, List<LazySupplier<? extends Item>>> itemTags = new HashMap<>();
    public static HashMap<TagKey<Block>, TagKey<Item>> similarTags = new HashMap<>();
    //Loot
    public static HashMap<LazySupplier<? extends Block>, CCBlockLoot.CCBlockLootFunction> blockLoot = new HashMap<>();
    //Recipe
    public static List<Consumer<Consumer<FinishedRecipe>>> recipes = new ArrayList<>();


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
