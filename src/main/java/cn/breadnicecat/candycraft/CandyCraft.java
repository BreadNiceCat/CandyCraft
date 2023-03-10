package cn.breadnicecat.candycraft;

import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.block.blockentity.CCBlockEntityManager;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.enchantment.CCEnchantmentManager;
import cn.breadnicecat.candycraft.gui.menu.CCMenuManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import cn.breadnicecat.candycraft.misc.CCConfig;
import cn.breadnicecat.candycraft.misc.CCSoundEvents;
import cn.breadnicecat.candycraft.misc.CCTab;
import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.utils.LambdaUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.StackLocatorUtil;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:26
 */
@Mod(CandyCraft.MODID)
public class CandyCraft {
	public static final String MODID = "candycraft";
	private static final LambdaUtils.LazyFunction<Class<?>, Logger> LOGGER = LambdaUtils.LazyFunction.of(LogManager::getLogger);
	public static final CCTab TAB = new CCTab();
	
	static {
		clockIn();
		CCDatagenManager.langEn.put("itemGroup.candycrafttab", "CandyCraft");
	}
	
	public CandyCraft() {
		//任何包含注册的类都需要初始化
		CCConfig.init();
		CCSoundEvents.init();
		CCEnchantmentManager.init();
		CCItemManager.init();
		CCBlockManager.init();
		CCBlockEntityManager.init();
		CCMenuManager.init();
		CCRecipeManager.init();
	}
	
	public static void clockIn() {
		getLogger().info("Initializing {} ...", StackLocatorUtil.getCallerClass(2).getName());
	}
	
	public static Logger getLogger() {
		return getLogger(StackLocatorUtil.getCallerClass(2));
	}
	
	private static Logger getLogger(Class<?> clazz) {
		return LOGGER.apply(clazz);
	}
	
	public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> createRegister(Class<I> type) {
		return registerRegister(DeferredRegister.create(type, MODID));
	}
	
	public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> registerRegister(DeferredRegister<I> register) {
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
		return register;
	}
	
	public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> createRegister(IForgeRegistry<I> type) {
		return registerRegister(DeferredRegister.create(type, MODID));
	}
	
	/**
	 * @return modid_name
	 */
	public static String prefixUnderline(String name) {
		return MODID + "_" + name;
	}
	
	/**
	 * @return modid:textures/gui/(name).png
	 */
	public static ResourceLocation prefixGUITex(String name) {
		return prefix("textures/gui/" + name + ".png");
	}
	
	/**
	 * 添加前缀
	 *
	 * @return modid:path
	 */
	public static ResourceLocation prefix(String path) {
		return new ResourceLocation(MODID, path);
	}
}
