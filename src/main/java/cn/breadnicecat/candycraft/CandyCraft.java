package cn.breadnicecat.candycraft;

import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import cn.breadnicecat.candycraft.misc.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:26
 */
@Mod(CandyCraft.MODID)
public class CandyCraft {
	public static final String MODID = "candycraft";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final CreativeModeTab TAB = new CCTab();

	public CandyCraft() {
		//任何包含注册的类都需要初始化
		CCConfig.init();
		CCSounds.init();
		CCPlantTypes.init();
		CCItemTags.init();
		CCBlockTags.init();
		CCItemManager.init();
		CCBlockManager.init();
	}

	public static void clockIn() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		LOGGER.info(stackTrace[stackTrace.length - 2].getClassName() + " Start working...");
	}

	/**
	 * 添加前缀(modid:path)
	 */
	public static ResourceLocation prefix(String path) {
		return new ResourceLocation(MODID, path);
	}

	public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> registerRegister(DeferredRegister<I> register) {
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
		return register;
	}
}
