package cn.breadnicecat.candycraft.data;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.function.Function;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 17:23
 */
public class CCBlockLoot extends BlockLoot {

    public static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    public static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    public static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    public static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    public static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    public static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    public static final StatePropertiesPredicate.Builder CROP_AGE_EQU_7 = StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_7, 7);
    public static final Function<Block, LootItemBlockStatePropertyCondition.Builder> CROP_AGE_EQU_7_CONDITION = (b) -> LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(CCBlockLoot.CROP_AGE_EQU_7);
    private HashSet<Block> knownBlocks = new HashSet<>();

    protected void add(@NotNull Block block, CCBlockLootFunction func) {
        LootTable.Builder builder = func.gen(block, this);
        if (builder != null) {
            this.add(block, builder);
        }
    }

    @Override
    protected void addTables() {
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }

    @Override
    protected void add(@NotNull Block pBlock, Function<Block, LootTable.Builder> pFactory) {
        this.add(pBlock, pFactory.apply(pBlock));
    }

    @Override
    protected void add(@NotNull Block block, @NotNull LootTable.Builder pLootTableBuilder) {
        knownBlocks.add(block);
        super.add(block, pLootTableBuilder);
    }


    public @NotNull LootTable.Builder tableSingle(ItemLike itemLike, @NotNull NumberProvider amount) {
        return BlockLoot.createSingleItemTable(itemLike, amount);
    }

    /**
     * If {@code dropGrownCropCondition} fails (i.e. crop is not ready), drops 1 {@code seedsItem}.
     * If {@code dropGrownCropCondition} succeeds (i.e. crop is ready), drops 1 {@code grownCropItem}, and 0-3 {@code seedsItem} with fortune applied.
     */
    public @NotNull LootTable.Builder tableCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropCondition) {
        return BlockLoot.createCropDrops(pCropBlock, pGrownCropItem, pSeedsItem, pDropGrownCropCondition);
    }


    /**
     * 当不是精准采集时候使用alt
     */
    public @NotNull LootTable.Builder tableSilkTouchDispatch(Block block, LootPoolEntryContainer.Builder<?> alt) {
        return BlockLoot.createSilkTouchDispatchTable(block, alt);
    }

    public @NotNull LootTable.Builder tableSilkTouchDispatch(Block block, Block alt) {
        return tableSilkTouchDispatch(block, LootItem.lootTableItem(alt));
    }

    public @NotNull LootTable.Builder tableSilkTouchOrShearsDispatch(Block block, LootPoolEntryContainer.Builder<?> alt) {
        return BlockLoot.createSilkTouchOrShearsDispatchTable(block, alt);
    }

    /**
     * @param pLeavesBlock  掉落树叶
     * @param pSaplingBlock 掉落树苗
     * @param extraItem     额外物品,如木棍
     * @param chance        掉落几率(数组：时运对应的效果)
     */
    public @NotNull LootTable.Builder tableLeavesDrops(Block pLeavesBlock, Block pSaplingBlock, @Nullable Item extraItem, float[] chance) {

        LootTable.Builder builder = createSilkTouchOrShearsDispatchTable(pLeavesBlock, applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(pSaplingBlock)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chance)));
        if (extraItem != null) {
            builder.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(extraItem).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chance))));
        }
        return builder;
    }


    public interface CCBlockLootFunction {
        CCBlockLootFunction DROP_SELF_1 = (block, loot1) -> {
            loot1.dropSelf(block);
            return null;
        };
        /**
         * 需要精准采集
         */
        CCBlockLootFunction DROP_SELF_1_NEED_SILK_TOUCH = (block, loot) -> {
            loot.dropWhenSilkTouch(block);
            return null;
        };

        @Nullable
        LootTable.Builder gen(Block block, CCBlockLoot loot);
    }
}
