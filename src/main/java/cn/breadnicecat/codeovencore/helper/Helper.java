package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 13:40
 */
public abstract class Helper {

	protected final CodeOvenCoreInstance instance;
	public final String modid;

	public Helper(@NotNull CodeOvenCoreInstance instance) {
		this.instance = instance;
		modid = instance.modid;
	}

	protected final <T extends IForgeRegistryEntry<T>> DeferredRegister<T> getRegister(Class<T> type) {
		DeferredRegister<T> register = DeferredRegister.create(type, modid);
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
		return register;
	}
}
