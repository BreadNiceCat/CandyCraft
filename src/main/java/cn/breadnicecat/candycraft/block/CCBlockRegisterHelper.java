package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCBlockLoot;
import cn.breadnicecat.candycraft.data.CCBlockStateProvider;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.item.CCItemRegisterHelper;
import cn.breadnicecat.candycraft.utils.CommonUtils;
import cn.breadnicecat.candycraft.utils.LambdaUtils.LazySupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

import static cn.breadnicecat.candycraft.CandyCraft.MODID;
import static cn.breadnicecat.candycraft.data.CCBlockLoot.CCBlockLootFunction.DROP_SELF_1;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/1/12 8:30
 */

public class CCBlockRegisterHelper {
	public static final DeferredRegister<Block> REGISTER = CandyCraft.createRegister(Block.class);
	
	/**
	 * 最原始的注册方法
	 *
	 * @param name      方块名
	 * @param enLang    本地化英文名 NOTE:只在runData下可用
	 * @param block     方块
	 * @param blockitem 方块对应的物品
	 * @param generator 方块模型,状态,物品模型生成器,NOTE:只在runData下可用
	 */
	public static <B extends Block> BlockRegistryObject<B> registerBlock(
			@NotNull String name, @Nullable String enLang, @NotNull LazySupplier<B> block, @Nullable LazySupplier<BlockItem> blockitem,
			@Nullable CCBlockStateProvider.BlockStateModelGenerator generator
	) {
		if (enLang != null) CCDatagenManager.langEn.put("block." + MODID + "." + name, enLang);//简单粗暴
		if (generator != null) CCDatagenManager.blockGenerators.put(block, generator);
		if (blockitem == null) CCDatagenManager.noItemBlock.add(block);
		return new BlockRegistryObject<>(REGISTER.register(name, block),
				blockitem == null ? null : CCItemRegisterHelper.registerItem(name, null, blockitem, null));
	}
	
	public static Builder<Block> getBuilder(String name, BlockBehaviour.Properties blockProp) {
		return getBuilder(name, () -> new Block(blockProp));
	}
	
	public static <B extends Block> Builder<B> getBuilder(String name, Supplier<B> block) {
		return new Builder<>(name, block);
	}
	
	public static class Builder<B extends Block> {
		@NotNull
		private final LazySupplier<B> block;
		@NotNull
		private final String name;
		private final Set<TagKey<Block>> blockTags = new HashSet<>();
		private final Set<TagKey<Item>> itemTags = new HashSet<>();
		@Nullable
		private String enLang = "";
		private LazySupplier<BlockItem> blockItem;
		@Nullable
		private CCBlockStateProvider.BlockStateModelGenerator generator = CCBlockStateProvider.BlockStateModelGenerator.SIMPLE_GENERATOR;
		@Nullable
		private CCBlockLoot.CCBlockLootFunction loot = DROP_SELF_1;
		
		private Builder(@NotNull String name, @NotNull Supplier<B> blockSupplier) {
			this.name = name;
			this.block = LazySupplier.of(blockSupplier);
		}
		
		public Builder<B> enLang(@Nullable String enLang) {
			this.enLang = enLang;
			return this;
		}
		
		public Builder<B> blockItem(@NotNull Item.Properties itemProp) {
			return blockItem(() -> new BlockItem(block.get(), itemProp));
		}
		
		public Builder<B> blockItem(@Nullable Supplier<BlockItem> blockItem) {
			this.blockItem = blockItem == null ? null : LazySupplier.of(blockItem);
			return this;
		}
		
		public Builder<B> noBlockItem() {
			return blockItem((Supplier<BlockItem>) null);
		}
		
		public Builder<B> noLoot() {
			return loot(null);
		}
		
		public Builder<B> loot(@Nullable CCBlockLoot.CCBlockLootFunction loot) {
			this.loot = loot;
			return this;
		}
		
		public Builder<B> noModel() {
			return model(null);
		}
		
		public Builder<B> model(@Nullable CCBlockStateProvider.BlockStateModelGenerator generator) {
			this.generator = generator;
			return this;
		}
		
		@SafeVarargs
		public final Builder<B> blockTag(TagKey<Block>... tag) {
			Collections.addAll(blockTags, tag);
			return this;
		}
		
		@SafeVarargs
		public final Builder<B> itemBlockTag(TagKey<Item>... tag) {
			Collections.addAll(itemTags, tag);
			return this;
		}
		
		public BlockRegistryObject<B> build() {
			if (!itemTags.isEmpty()) {
				Objects.requireNonNull(blockItem);
				itemTags.forEach(tag -> CCDatagenManager.itemTags.computeIfAbsent(tag, a -> new ArrayList<>()).add(blockItem));
			}
			blockTags.forEach(tag -> CCDatagenManager.blockTags.computeIfAbsent(tag, a -> new ArrayList<>()).add(block));
			if (loot != null) {
				CCDatagenManager.blockLoot.put(block, loot);
			}
			return registerBlock(name, (enLang == null) ? null : (enLang.isEmpty() ? CommonUtils.getEnLangByName(name) : enLang), block, blockItem, generator);
		}
	}
	
	
	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/24 20:37
	 * 用于包装方块注册对象
	 */
	public record BlockRegistryObject<B extends Block>(@NotNull RegistryObject<B> block,
													   @Nullable RegistryObject<BlockItem> blockItem) {
		@Override
		public RegistryObject<BlockItem> blockItem() {
			return blockItem;
		}
		
		public ItemLike getItemLike() {
			return getBlock();
		}
		
		public B getBlock() {
			return block.get();
		}
	}
}
