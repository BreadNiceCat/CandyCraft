package cn.breadnicecat.candycraft.item;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.data.CCItemModelProvider;
import cn.breadnicecat.candycraft.utils.CommonUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 8:34
 */
public class CCItemRegisterHelper {
	public static final DeferredRegister<Item> REGISTER = CandyCraft.createRegister(Item.class);

	/**
	 * @param name           物品名
	 * @param enLangName     本地化英文名 NOTE:只在runData下可用
	 * @param item           Item
	 * @param modelGenerator 模型生成器 NOTE:只在runData下可用
	 */
	public static <I extends Item> RegistryObject<I> registerItem(@NotNull String name, @Nullable String enLangName, @NotNull Supplier<I> item, @Nullable CCItemModelProvider.ItemModelGenerator modelGenerator) {
		RegistryObject<I> object = REGISTER.register(name, item);
		if (enLangName != null) CCDatagenManager.langEn.put("item." + CandyCraft.MODID + "." + name, enLangName);
		if (modelGenerator != null) CCDatagenManager.itemModelGenerators.put(name, modelGenerator);
		return object;
	}

	public static <I extends Item> Builder<I> getBuilder(String name, Supplier<I> item) {
		return new Builder<>(name, item);
	}

	public static Builder<Item> getBuilder(String name, Item.Properties prop) {
		return getBuilder(name, () -> new Item(prop));
	}

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2023/1/10 9:20
	 */
	public static class Builder<I extends Item> {

		@NotNull
		private final String name;
		@NotNull
		private final Lazy<I> item;
		private final Set<TagKey<Item>> itemTags = new HashSet<>();
		@Nullable
		private String enLang = "";
		@Nullable
		private CCItemModelProvider.ItemModelGenerator modelGenerator = CCItemModelProvider.ItemModelGenerator.SIMPLE_ITEM_GENERATOR;

		public Builder(@NotNull String name, @NotNull Supplier<I> item) {
			this.name = name;
			this.item = Lazy.of(item);
		}

		public Builder<I> enName(String enName) {
			this.enLang = enName;
			return this;
		}

		@SafeVarargs
		public final Builder<I> tag(TagKey<Item>... tags) {
			Collections.addAll(this.itemTags, tags);
			return this;
		}

		public Builder<I> model(CCItemModelProvider.ItemModelGenerator generator) {
			this.modelGenerator = generator;
			return this;
		}

		public RegistryObject<I> build() {
			itemTags.forEach(tag -> CCDatagenManager.itemTags.computeIfAbsent(tag, a -> new ArrayList<>()).add(item));
			return registerItem(name, (enLang == null) ? null : (enLang.isEmpty() ? CommonUtils.getEnLangByName(name) : enLang), item, modelGenerator);
		}
	}
}
