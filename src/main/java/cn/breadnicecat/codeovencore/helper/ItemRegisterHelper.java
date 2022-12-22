package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import cn.breadnicecat.codeovencore.datagen.SimpleItemModelProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/21 14:45
 */
public class ItemRegisterHelper {
	public final DeferredRegister<Item> REGISTER;
	private final CodeOvenCoreInstance instance;

	public ItemRegisterHelper(CodeOvenCoreInstance instance) {
		this.instance = instance;
		REGISTER = DeferredRegister.create(Item.class, instance.modid);
		REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static String getEnLangByName(String name) {
		StringBuilder sb = new StringBuilder();
		String[] s = name.split("_");
		for (String s1 : s) {
			sb.append(s1.substring(0, 1).toUpperCase()).append(s1.substring(1)).append(" ");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public <I extends Item> RegistryObject<I> registerItem(String name, String enLocName, Supplier<I> item, @Nullable BiConsumer<SimpleItemModelProvider, String> modelGenerator) {
		RegistryObject<I> object = REGISTER.register(name, item);
		DatagenHelper datagenHelper = instance.getDatagenHelper();
		datagenHelper.langEn.put("item." + instance.modid + "." + name, enLocName);
		if (modelGenerator != null) datagenHelper.itemModel.put(name, modelGenerator);
		return object;

	}

	public <I extends Item> RegistryObject<I> registerItem(String name, Supplier<I> item, BiConsumer<SimpleItemModelProvider, String> modelGenerator) {
		return registerItem(name, getEnLangByName(name), item, modelGenerator);
	}

	public RegistryObject<Item> registerSimpleItem(String name, Item.Properties prop) {
		return registerItem(name, () -> new Item(prop), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	}

	public RegistryObject<Item> registerSimpleItem(String name, String enLocName, Item.Properties prop) {
		return registerItem(name, enLocName, () -> new Item(prop), SimpleItemModelProvider.DEFAULT_ITEM_GENERATOR);
	}


}
