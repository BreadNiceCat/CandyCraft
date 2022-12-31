package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.CocItemModelProvider;
import cn.breadnicecat.codeovencore.util.CommonUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/21 14:45
 */
public class ItemRegisterHelper extends Helper {
	public final DeferredRegister<Item> register;


	public ItemRegisterHelper(CodeOvenCoreInstance instance) {
		super(instance);
		register = getRegister(Item.class);
	}

	/**
	 * @param name           物品名
	 * @param enLocName      经本地化过的en_us名 NOTE:runDataModeOnly
	 * @param item           Item
	 * @param modelGenerator 模型生成器 NOTE:runDataModeOnly
	 */
	public <I extends Item> RegistryObject<I> registerItem(@NotNull String name, @NotNull String enLocName, @NotNull Supplier<I> item, @Nullable CocItemModelProvider.ItemModelGenerator modelGenerator) {
		RegistryObject<I> object = register.register(name, item);
		DatagenHelper datagenHelper = instance.getDatagenHelper();
		datagenHelper.langEn.put("item." + instance.modid + "." + name, enLocName);
		if (modelGenerator != null) datagenHelper.itemModel.put(name, modelGenerator);
		return object;

	}

	public <I extends Item> RegistryObject<I> registerItem(@NotNull String name, @NotNull Supplier<I> item, @Nullable CocItemModelProvider.ItemModelGenerator modelGenerator) {
		return registerItem(name, CommonUtils.getEnLangByName(name), item, modelGenerator);
	}

	public <I extends Item> RegistryObject<I> registerItem(@NotNull String name, @NotNull String enLocName, @NotNull Supplier<I> item) {
		return registerItem(name, item, CocItemModelProvider.ItemModelGenerator.SIMPLE_ITEM_GENERATOR);
	}

	public <I extends Item> RegistryObject<I> registerItem(@NotNull String name, @NotNull Supplier<I> item) {
		return registerItem(name, CommonUtils.getEnLangByName(name), item);
	}

	public RegistryObject<Item> registerSimpleItem(@NotNull String name, @NotNull Item.Properties prop) {
		return registerItem(name, () -> new Item(prop), CocItemModelProvider.ItemModelGenerator.SIMPLE_ITEM_GENERATOR);
	}

	public RegistryObject<Item> registerSimpleItem(@NotNull String name, @NotNull String enLocName, @NotNull Item.Properties prop) {
		return registerItem(name, enLocName, () -> new Item(prop), CocItemModelProvider.ItemModelGenerator.SIMPLE_ITEM_GENERATOR);
	}


}
